package com.yoxiang.payhelper.util;

import com.yoxiang.payhelper.wxpay.WechatPay;
import com.yoxiang.payhelper.wxpay.WechatPayHeader;
import com.yoxiang.payhelper.wxpay.WechatPayTradeTypes;
import com.yoxiang.payhelper.wxpay.WechatUnifiedOrderRequest;

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
     * @param wechatPay
     * @return
     */
    public static String genSign(WechatPay wechatPay) {

        String payType = wechatPay.getWechatPayHeader().getPayType();
        if (payType.equals(WechatPayTradeTypes.UNIFIED_ORDER)) {
            return genUnifiedOrderSign((WechatUnifiedOrderRequest) wechatPay);
        } else if (payType.equals(WechatPayTradeTypes.QUERY_ORDER)) {
            return genQueryOrderSign();
        } else if (payType.equals(WechatPayTradeTypes.CLOSE_ORDER)) {
            return genCloseOrderSign();
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

    public static String genQueryOrderSign() {
        return null;
    }

    public static String genCloseOrderSign() {
        return null;
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

    /**
     * 构建随机数
     * @param length 随机数长度
     *
     * @return
     */
    private static int buildRandom(int length) {
        int num = 1;
        double random = Math.random();
        if (random < 0.1) {
            random = random + 0.1;
        }
        for (int i = 0; i < length; i++) {
            num = num * 10;
        }
        return (int) ((random * num));
    }
}
