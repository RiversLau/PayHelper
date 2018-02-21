package com.yoxiang.payhelper.wxpay.request;

import com.yoxiang.payhelper.wxpay.WechatPayRequest;
import com.yoxiang.payhelper.wxpay.WechatPayHeader;
import com.yoxiang.payhelper.wxpay.WechatPayTradeTypes;
import com.yoxiang.payhelper.wxpay.WechatPayXmlElements;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Author: Rivers
 * Date: 2018/2/13 19:45
 */
public class WechatCloseOrderRequest extends WechatPayRequest {

    private String outTradeNo;          // 商户订单号

    public WechatCloseOrderRequest(String appId, String mchId, String mchKey) {
        this.wechatPayHeader = new WechatPayHeader(appId, mchId, mchKey);
        this.wechatPayHeader.setPayType(WechatPayTradeTypes.CLOSE_ORDER);
        this.wechatPayHeader.setResultType(WechatPayTradeTypes.CLOSE_ORDER_RESULT);
    }

    /**
     * 将关单对象字段写成XML Document对象
     * @param document
     */
    public void write(Document document) {

        Element root = document.createElement(WechatPayXmlElements.ROOT);
        wechatPayHeader.write(root, document);

        Element tradeEle = document.createElement(WechatPayXmlElements.OUT_TRADE_NO);
        tradeEle.setTextContent(this.outTradeNo);
        root.appendChild(tradeEle);

        document.appendChild(root);
    }

    //========================= GETTER/SETTER方法 =========================//

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }
}
