package com.yoxiang.payhelper.wxpay.response;

import com.yoxiang.payhelper.wxpay.*;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

/**
 * 微信支付回调通知对象
 * Author: Rivers
 * Date: 2018/2/13 21:37
 */
public class WechatNotificationResponse extends WechatPayResponse {

    private String transactionId;                   // 微信订单号
    private String outTradeNo;                      // 商户订单号

    private String tradeType;                       // 交易类型，JSAPI--公众号支付、NATIVE--原生扫码支付、APP--app支付、MICROPAY--刷卡支付
    private String bankType;                        // 银行类型
    private Integer totalFee;                       // 总金额，以分为单位
    private String feeType;
    private Integer cashFee;                        // 现金支付金额，以分为单位
    private String cashFeeType;
    private Integer couponFee;                      // 代金券金额
    private Integer couponCount;                    // 代金券数量
    private String attach;                          // 商家数据附加
    private String timeEnd;                         // 支付完成时间
    private String openId;                          // 用户在商户appid下的唯一标识
    private SubscribeStatus subscribeStatus;        // 关注状态

    public WechatNotificationResponse() {
        this.wechatPayHeader = new WechatPayHeader();
    }

    /**
     * 读取微信回调内容，将XML文档转换为该对象对应字段内容
     * @param document
     */
    public void read(Document document) {
        this.returnCode = document.getElementsByTagName(WechatPayXmlElements.RETURN_CODE).item(0).getTextContent();
        if (WechatPayStatusCode.SUCCESS.equals(returnCode)) {
            this.resultCode = document.getElementsByTagName(WechatPayXmlElements.RESULT_CODE).item(0).getTextContent();
            if (WechatPayStatusCode.SUCCESS.equals(resultCode)) {

                wechatPayHeader.read(document);

                this.outTradeNo = document.getElementsByTagName(WechatPayXmlElements.OUT_TRADE_NO).item(0).getTextContent();
                this.transactionId = document.getElementsByTagName(WechatPayXmlElements.TRANSACTION_ID).item(0).getTextContent();

                Node tradeTypeNode = document.getElementsByTagName(WechatPayXmlElements.TRADE_TYPE).item(0);
                this.tradeType = tradeTypeNode == null ? null : tradeTypeNode.getTextContent();

                Node bankTypeNode = document.getElementsByTagName(WechatPayXmlElements.BANK_TYPE).item(0);
                this.bankType = bankTypeNode == null ? null : bankTypeNode.getTextContent();

                Node totalFeeNode = document.getElementsByTagName(WechatPayXmlElements.TOTAL_FEE).item(0);
                this.totalFee = totalFeeNode == null ? null : Integer.valueOf(totalFeeNode.getTextContent());

                Node feeTypeNode = document.getElementsByTagName(WechatPayXmlElements.FEE_TYPE).item(0);
                this.feeType = feeTypeNode == null ? null : feeTypeNode.getTextContent();

                Node cashFeeNode = document.getElementsByTagName(WechatPayXmlElements.CASH_FEE).item(0);
                this.cashFee = cashFeeNode == null ? null : Integer.valueOf(cashFeeNode.getTextContent());

                Node cashFeeTypeNode = document.getElementsByTagName(WechatPayXmlElements.CASH_FEE_TYPE).item(0);
                this.cashFeeType = cashFeeTypeNode == null ? null : cashFeeTypeNode.getTextContent();

                // 代金券金额
                Node couponFeeNode = document.getElementsByTagName(WechatPayXmlElements.COUPON_FEE).item(0);
                this.couponFee = couponFeeNode == null ? null : Integer.valueOf(couponFeeNode.getTextContent());

                // 代金券数量
                Node couponCountNode = document.getElementsByTagName(WechatPayXmlElements.COUPON_COUNT).item(0);
                this.couponCount = couponCountNode == null ? null : Integer.valueOf(couponCountNode.getTextContent());

                // 商户自定义附加内容
                Node attachNode = document.getElementsByTagName(WechatPayXmlElements.ATTACH).item(0);
                this.attach = attachNode == null ? null : attachNode.getTextContent();

                // 支付完成时间
                Node timeEndNode = document.getElementsByTagName(WechatPayXmlElements.TIME_END).item(0);
                this.timeEnd = timeEndNode == null ? null : timeEndNode.getTextContent();

                // 用户openID
                Node openidNode = document.getElementsByTagName(WechatPayXmlElements.OPENID).item(0);
                this.openId = openidNode == null ? null : openidNode.getTextContent();

                // 关注状态
                Node subscribeNode = document.getElementsByTagName(WechatPayXmlElements.IS_SUBSCRIBE).item(0);
                this.subscribeStatus = subscribeNode == null ? null : SubscribeStatus.valueOf(subscribeNode.getTextContent());
            } else {
                this.errCode = document.getElementsByTagName(WechatPayXmlElements.ERR_CODE).item(0).getTextContent();
                this.errCodeDes = document.getElementsByTagName(WechatPayXmlElements.ERR_CODE_DES).item(0).getTextContent();
            }
        } else {
            this.returnMsg = document.getElementsByTagName(WechatPayXmlElements.RETURN_MSG).item(0).getTextContent();
        }
    }

    /**
     * 读取微信回调内容，无需write方法
     * @param document
     */
    public void write(Document document) {
        // do nothing
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

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public String getBankType() {
        return bankType;
    }

    public void setBankType(String bankType) {
        this.bankType = bankType;
    }

    public Integer getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(Integer totalFee) {
        this.totalFee = totalFee;
    }

    public String getFeeType() {
        return feeType;
    }

    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }

    public Integer getCashFee() {
        return cashFee;
    }

    public void setCashFee(Integer cashFee) {
        this.cashFee = cashFee;
    }

    public String getCashFeeType() {
        return cashFeeType;
    }

    public void setCashFeeType(String cashFeeType) {
        this.cashFeeType = cashFeeType;
    }

    public Integer getCouponFee() {
        return couponFee;
    }

    public void setCouponFee(Integer couponFee) {
        this.couponFee = couponFee;
    }

    public Integer getCouponCount() {
        return couponCount;
    }

    public void setCouponCount(Integer couponCount) {
        this.couponCount = couponCount;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public SubscribeStatus getSubscribeStatus() {
        return subscribeStatus;
    }

    public void setSubscribeStatus(SubscribeStatus subscribeStatus) {
        this.subscribeStatus = subscribeStatus;
    }
}
