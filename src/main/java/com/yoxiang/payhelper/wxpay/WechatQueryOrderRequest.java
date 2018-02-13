package com.yoxiang.payhelper.wxpay;

import com.yoxiang.payhelper.util.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * 查询订单请求参数组成的对象
 * Author: Rivers
 * Date: 2018/2/13 15:16
 */
public class WechatQueryOrderRequest extends WechatPay {

    private String transactionId;           // 微信的订单号
    private String outTradeNo;              // 商户订单号，与微信订单号二选一

    public WechatQueryOrderRequest() {
        this.wechatPayHeader = new WechatPayHeader();
        this.wechatPayHeader.setPayType(WechatPayTradeTypes.QUERY_ORDER);
        this.wechatPayHeader.setResultType(WechatPayTradeTypes.QUERY_ORDER_RESULT);
    }

    public WechatQueryOrderRequest(WechatPayHeader wechatPayHeader) {
        this.wechatPayHeader = wechatPayHeader;
    }

    /**
     * 请求对象无需读取XML Document对象
     * @param document
     */
    public void read(Document document) {
        // do nothing
    }

    /**
     * 将查询订单对象转换为XML Document对象
     * @param document
     */
    public void write(Document document) {

        Element root = document.createElement(WechatPayXmlElements.ROOT);
        wechatPayHeader.write(root, document);

        if (StringUtils.isEmpty(this.outTradeNo) && StringUtils.isEmpty(this.transactionId)) {
            throw new IllegalStateException("查询订单必须提供商户订单号或者微信订单号之一");
        }

        if (!StringUtils.isEmpty(this.outTradeNo)) {
            Element transEle = document.createElement(WechatPayXmlElements.OUT_TRADE_NO);
            transEle.setTextContent(this.outTradeNo);
            root.appendChild(transEle);
        }

        if (!StringUtils.isEmpty(this.transactionId)) {
            Element transEle = document.createElement(WechatPayXmlElements.TRANSACTION_ID);
            transEle.setTextContent(this.transactionId);
            root.appendChild(transEle);
        }

        document.appendChild(root);
    }

    //========================= GETTER/SETTER方法 =========================//
    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }
}
