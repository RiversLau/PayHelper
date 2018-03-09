package com.yoxiang.payhelper.util;

import com.yoxiang.payhelper.wxpay.*;
import com.yoxiang.payhelper.wxpay.request.WechatCloseOrderRequest;
import com.yoxiang.payhelper.wxpay.request.WechatQueryOrderRequest;
import com.yoxiang.payhelper.wxpay.request.WechatUnifiedOrderRequest;

import java.util.*;

/**
 * 微信支付工具类
 * 用于生成各种微信支付签名；微信签名校验
 * Author: RiversLau
 * Date: 2018/2/6 11:33
 */
public class WechatPayUtils {

    /**
     * 生成随机字符串
     * @return
     */
    public static String genNonceStr() {
        return NonceStrUtils.genRandomCode(false, 8);
    }

    /**
     * 生成签名类
     * @param wechatPayRequest
     * @return
     */
    public static String genSign(WechatPayRequest wechatPayRequest) {

        String payType = wechatPayRequest.getWechatPayHeader().getPayType();
        if (payType.equals(WechatPayTradeTypes.UNIFIED_ORDER)) {
            return genUnifiedOrderSign((WechatUnifiedOrderRequest) wechatPayRequest);
        } else if (payType.equals(WechatPayTradeTypes.QUERY_ORDER)) {
            return genQueryOrderSign((WechatQueryOrderRequest) wechatPayRequest);
        } else if (payType.equals(WechatPayTradeTypes.CLOSE_ORDER)) {
            return genCloseOrderSign((WechatCloseOrderRequest) wechatPayRequest);
        } else {
            return null;
        }
    }

    /**
     * 生成统一下单签名
     * @param unifiedOrder 统一下单API
     * @return 签名字符串
     */
    public static String genUnifiedOrderSign(WechatUnifiedOrderRequest unifiedOrder) {

        SortedMap<String, String> params = new TreeMap<String, String>();

        WechatPayHeader payHeader = unifiedOrder.getWechatPayHeader();
        params.put("appid", payHeader.getAppId());
        params.put("mch_id", payHeader.getMchId());
        params.put("nonce_str", payHeader.getNonceStr());
        params.put("body", unifiedOrder.getBody());
        params.put("attach", unifiedOrder.getAttach());
        params.put("out_trade_no", unifiedOrder.getOutTradeNo());
        params.put("total_fee", unifiedOrder.getTotalFee());
        params.put("spbill_create_ip", unifiedOrder.getSpbillCreateIp());
        params.put("notify_url", unifiedOrder.getNotifyUrl());
        params.put("trade_type", unifiedOrder.getTradeType());

        String mchKey = payHeader.getMchKey();
        return genSign(params, mchKey);
    }

    /**
     * 生成查询订单签名
     * @param queryOrder 查询订单对象
     * @return
     */
    public static String genQueryOrderSign(WechatQueryOrderRequest queryOrder) {

        SortedMap<String, String> params = new TreeMap<String, String>();

        WechatPayHeader payHeader = queryOrder.getWechatPayHeader();
        params.put("appid", payHeader.getAppId());
        params.put("mch_id", payHeader.getMchId());
        params.put("nonce_str", payHeader.getNonceStr());
        if (!StringUtils.isEmpty(queryOrder.getOutTradeNo())) {
            params.put("out_trade_no", queryOrder.getOutTradeNo());
        }
        if (!StringUtils.isEmpty(queryOrder.getTransactionId())) {
            params.put("transaction_id", queryOrder.getTransactionId());
        }

        String mchKey = payHeader.getMchKey();
        return genSign(params, mchKey);
    }

    /**
     * 生成关闭订单签名
     * @param closeOrder 关单对象
     * @return
     */
    public static String genCloseOrderSign(WechatCloseOrderRequest closeOrder) {

        SortedMap<String, String> params = new TreeMap<String, String>();

        WechatPayHeader payHeader = closeOrder.getWechatPayHeader();
        params.put("appid", payHeader.getAppId());
        params.put("mch_id", payHeader.getMchId());
        params.put("nonce_str", payHeader.getNonceStr());
        params.put("out_trade_no", closeOrder.getOutTradeNo());

        String mchKey = payHeader.getMchKey();
        return genSign(params, mchKey);
    }

    /**
     * 生成用于APP端拉起微信支付的签名，
     * 同时返回签名的相关参数
     * @param merchant 商户
     * @param prepayId 预支付订单ID
     * @return
     */
    public static Map genPayPullingSign4App(Merchant merchant, String prepayId) {

        SortedMap sortedMap = new TreeMap<String, String>();
        sortedMap.put("appid", merchant.getAppId());
        sortedMap.put("partnerid", merchant.getMchId());
        sortedMap.put("prepayid", prepayId);
        sortedMap.put("package", "Sign=WXPay");
        sortedMap.put("noncestr", NonceStrUtils.genRandomCode(false, 8));
        sortedMap.put("timestamp", String.valueOf(new Date().getTime() / 1000));

        String sign = genSign(sortedMap, merchant.getMchKey());
        sortedMap.put("sign", sign);
        return sortedMap;
    }

    /**
     * 生成签名最底层类
     * 通过有序Map以及商户API密钥来生成签名
     * @param params 有序Map
     * @param mchKey 商户密钥
     * @return
     */
    public static String genSign(SortedMap<String, String> params, String mchKey) {

        StringBuffer sb = new StringBuffer();
        Set es = params.entrySet();
        Iterator it = es.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String k = (String) entry.getKey();
            String v = (String) entry.getValue();
            if (null != v && !"".equals(v) && !"sign".equals(k)
                    && !"key".equals(k)) {
                sb.append(k + "=" + v + "&");
            }
        }
        sb.append("key=" + mchKey);
        System.out.println("生成Sign时拼接的字符串：：：：====" + sb.toString());
        String sign = EncryptUtils.MD5Encode(sb.toString(), Charsets.DEFAULT_CHARSET_NAME).toUpperCase();
        return sign;
    }
}
