package com.yoxiang.payhelper.wxpay.request;

import com.yoxiang.payhelper.wxpay.WechatPayRequest;
import com.yoxiang.payhelper.wxpay.WechatPayHeader;
import com.yoxiang.payhelper.wxpay.WechatPayTradeTypes;
import com.yoxiang.payhelper.wxpay.WechatPayXmlElements;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * 统一下单请求参数组成的对象
 * Author: Rivers
 * Date: 2018/2/12 17:14
 */
public class WechatUnifiedOrderRequest extends WechatPayRequest {

    private String attach;                  // 附加内容，微信会原样返回
    private String body;                    // 商品描述
    private String notifyUrl;               // 通知回调地址
    private String spbillCreateIp;          // 终端IP
    private String totalFee;                // 总金额，以分为单位
    private String tradeType;               // 支付类型，APP、JSAPI、NATIVE
    private String outTradeNo;              // 商户订单号

    public WechatUnifiedOrderRequest(WechatPayHeader payHeader, String notifyUrl) {
        this.wechatPayHeader = payHeader;
        this.wechatPayHeader.setPayType(WechatPayTradeTypes.UNIFIED_ORDER);
        this.wechatPayHeader.setResultType(WechatPayTradeTypes.UNIFIED_ORDER_RESULT);
        this.notifyUrl = notifyUrl;
    }

    // 将对象转化为对应的XML Document对象
    public void write(Document document) {

        Element root = document.createElement(WechatPayXmlElements.ROOT);
        wechatPayHeader.write(root, document);

        Element attachEle = document.createElement(WechatPayXmlElements.ATTACH);
        attachEle.setTextContent(this.attach);
        root.appendChild(attachEle);

        Element bodyEle = document.createElement(WechatPayXmlElements.BODY);
        bodyEle.setTextContent(this.body);
        root.appendChild(bodyEle);

        Element notifyUrlEle = document.createElement(WechatPayXmlElements.NOTIFY_URL);
        notifyUrlEle.setTextContent(this.notifyUrl);
        root.appendChild(notifyUrlEle);

        Element billIpEle = document.createElement(WechatPayXmlElements.SPBILL_CREATE_IP);
        billIpEle.setTextContent(this.spbillCreateIp);
        root.appendChild(billIpEle);

        Element totalFeeEle = document.createElement(WechatPayXmlElements.TOTAL_FEE);
        totalFeeEle.setTextContent(this.totalFee);
        root.appendChild(totalFeeEle);

        Element tradeTypeEle = document.createElement(WechatPayXmlElements.TRADE_TYPE);
        tradeTypeEle.setTextContent(this.tradeType);
        root.appendChild(tradeTypeEle);

        Element tradeNumEle = document.createElement(WechatPayXmlElements.OUT_TRADE_NO);
        tradeNumEle.setTextContent(this.outTradeNo);
        root.appendChild(tradeNumEle);

        document.appendChild(root);
    }

    //========================= GETTER/SETTER方法 =========================//

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public String getSpbillCreateIp() {
        return spbillCreateIp;
    }

    public void setSpbillCreateIp(String spbillCreateIp) {
        this.spbillCreateIp = spbillCreateIp;
    }

    public String getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(String totalFee) {
        this.totalFee = totalFee;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }
}
