package com.yoxiang.payhelper.wxpay.response;

import com.yoxiang.payhelper.wxpay.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;

/**
 * 调用微信统一下单后，微信服务端返回的响应XML对应的对象
 * Author: Rivers
 * Date: 2018/2/12 21:11
 */
public class WechatUnifiedOrderResponse extends WechatPayResponse {

    private static final Logger logger = LoggerFactory.getLogger(WechatUnifiedOrderResponse.class);

    // 微信返回的预支付ID
    private String prePayId;
    // 交易类型，JSAPI--公众号支付、NATIVE--原生扫码支付、APP--app支付、MICROPAY--刷卡支付，刷卡支付有单独的支付接口
    private String tradeType;
    // 签名
    private String sign;

    public WechatUnifiedOrderResponse() {
        this.wechatPayHeader = new WechatPayHeader();
        this.wechatPayHeader.setResultType(WechatPayTradeTypes.UNIFIED_ORDER_RESULT);
    }

    public WechatUnifiedOrderResponse(WechatPayHeader wechatPayHeader) {
        this.wechatPayHeader = wechatPayHeader;
    }

    /**
     * 读取XML Document对象，装换为对应的字段值
     * @param document
     */
    public void read(Document document) {

        logger.info("Begin========开始读取统一下单后微信返回的预支付订单XML========");

        this.returnCode = document.getElementsByTagName(WechatPayXmlElements.RETURN_CODE).item(0).getTextContent();
        if (WechatPayStatusCode.SUCCESS.equals(returnCode)) {
            wechatPayHeader.read(document);
            this.resultCode = document.getElementsByTagName(WechatPayXmlElements.RESULT_CODE).item(0).getTextContent();
            if (WechatPayStatusCode.SUCCESS.equals(resultCode)) {
                this.prePayId = document.getElementsByTagName(WechatPayXmlElements.PREPAY_ID).item(0).getTextContent();
                this.tradeType = document.getElementsByTagName(WechatPayXmlElements.TRADE_TYPE).item(0).getTextContent();
                this.sign = document.getElementsByTagName(WechatPayXmlElements.SIGN).item(0).getTextContent();
            } else {
                this.errCode = document.getElementsByTagName(WechatPayXmlElements.ERR_CODE).item(0).getTextContent();
                this.errCodeDes = document.getElementsByTagName(WechatPayXmlElements.ERR_CODE_DES).item(0).getTextContent();
            }
        } else {
            this.returnMsg = document.getElementsByTagName(WechatPayXmlElements.RETURN_MSG).item(0).getTextContent();
        }

        logger.info("End========读取统一下单后微信返回的预支付订单XML结束========");
    }

    /**
     * 将字段内容写到Document对象中，由于该
     * 对象无需处理写XML DOM对象，所以此方法暂不处理
     * @param document
     */
    public void write(Document document) {
        // do nothing
    }

    //========================= GETTER/SETTER方法 =========================//


    public String getPrePayId() {
        return prePayId;
    }

    public String getTradeType() {
        return tradeType;
    }

    public String getSign() {
        return sign;
    }
}
