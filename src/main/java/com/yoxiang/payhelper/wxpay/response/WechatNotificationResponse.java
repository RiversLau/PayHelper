package com.yoxiang.payhelper.wxpay.response;

import com.yoxiang.payhelper.wxpay.WechatPayResponse;
import org.w3c.dom.Document;

/**
 * 微信支付回调通知对象
 * Author: Rivers
 * Date: 2018/2/13 21:37
 */
public class WechatNotificationResponse extends WechatPayResponse {

    private String transactionId;           // 微信订单号
    private String outTradeNo;              // 商户订单号
    private String tradeType;               // 交易类型，JSAPI--公众号支付、NATIVE--原生扫码支付、APP--app支付、MICROPAY--刷卡支付
    private String bankType;                // 银行类型
    private Integer totalFee;               // 总金额，以分为单位
    private Integer cashFee;                // 现金支付金额，以分为单位


    /**
     * 读取微信回调内容，将XML文档转换为该对象对应字段内容
     * @param document
     */
    public void read(Document document) {

    }

    /**
     * 读取微信回调内容，无需write方法
     * @param document
     */
    public void write(Document document) {
        // do nothing
    }
}
