package com.yoxiang.payhelper.wxpay.response;

import com.yoxiang.payhelper.util.StringUtils;
import com.yoxiang.payhelper.wxpay.*;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

/**
 * Author: Rivers
 * Date: 2018/2/13 15:37
 */
public class WechatQueryOrderResponse extends WechatPayResponse {

    private String tradeType;           // 交易类型，JSAPI--公众号支付、NATIVE--原生扫码支付、APP--app支付、MICROPAY--刷卡支付

    private String outTradeNo;          // 商户订单号
    private String transactionId;       // 微信订单号
    private String tradeState;          // 交易状态
    private String tradeStateDes;       // 交易状态描述

    private Integer totalFee;           // 总金额
    private String bankType;            // 银行类型
    private Integer cashFee;            // 现金支付金额
    private String timeEnd;             // 支付完成时间

    public WechatQueryOrderResponse() {
        this.wechatPayHeader = new WechatPayHeader();
        this.wechatPayHeader.setResultType(WechatPayTradeTypes.QUERY_ORDER_RESULT);
    }

    /**
     * 读取XML Document对象转换为对应字段
     * @param document
     */
    public void read(Document document) {

        this.returnCode = document.getElementsByTagName(WechatPayXmlElements.RETURN_CODE).item(0).getTextContent();
        if (WechatPayStatusCode.SUCCESS.equals(returnCode)) {
            wechatPayHeader.read(document);
            this.resultCode = document.getElementsByTagName(WechatPayXmlElements.RESULT_CODE).item(0).getTextContent();
            if (WechatPayStatusCode.SUCCESS.equals(resultCode)) {
                this.outTradeNo = document.getElementsByTagName(WechatPayXmlElements.OUT_TRADE_NO).item(0).getTextContent();

                Node transactionNode = document.getElementsByTagName(WechatPayXmlElements.TRANSACTION_ID).item(0);
                this.transactionId = transactionNode == null ? null : transactionNode.getTextContent();

                Node tradeTypeNode = document.getElementsByTagName(WechatPayXmlElements.TRADE_TYPE).item(0);
                this.tradeType = tradeTypeNode == null ? null : tradeTypeNode.getTextContent();

                Node totalFeeNode = document.getElementsByTagName(WechatPayXmlElements.TOTAL_FEE).item(0);
                this.totalFee = totalFeeNode == null ? null : (StringUtils.isEmpty(totalFeeNode.getTextContent()) ? null : Integer.valueOf(totalFeeNode.getTextContent()));

                Node cashFeeNode = document.getElementsByTagName(WechatPayXmlElements.CASH_FEE).item(0);
                this.cashFee = cashFeeNode == null ? null : (StringUtils.isEmpty(cashFeeNode.getTextContent()) ? null : Integer.valueOf(cashFeeNode.getTextContent()));

                Node tradeStateNode = document.getElementsByTagName(WechatPayXmlElements.TRADE_STATE).item(0);
                this.tradeState = tradeStateNode == null ? null : tradeStateNode.getTextContent();

                Node tradeStateDesNode = document.getElementsByTagName(WechatPayXmlElements.TRADE_STATE_DES).item(0);
                this.tradeStateDes = tradeStateDesNode == null ? null : tradeStateDesNode.getTextContent();

                Node bankTypeNode = document.getElementsByTagName(WechatPayXmlElements.BANK_TYPE).item(0);
                this.bankType = bankTypeNode == null ? null : bankTypeNode.getTextContent();

                Node timeEndNode = document.getElementsByTagName(WechatPayXmlElements.TIME_END).item(0);
                this.timeEnd = timeEndNode == null ? null : timeEndNode.getTextContent();
            } else {
                this.errCode = document.getElementsByTagName(WechatPayXmlElements.ERR_CODE).item(0).getTextContent();
                this.errCodeDes = document.getElementsByTagName(WechatPayXmlElements.ERR_CODE_DES).item(0).getTextContent();
            }
        } else {
            this.returnMsg = document.getElementsByTagName(WechatPayXmlElements.RETURN_MSG).item(0).getTextContent();
        }
    }

    /**
     * 查询订单查询响应对象，无需write操作
     * @param document
     */
    public void write(Document document) {
        // do nothing
    }

    //========================= GETTER/SETTER方法 =========================//

    public String getTradeType() {
        return tradeType;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public String getTradeState() {
        return tradeState;
    }

    public String getTradeStateDes() {
        return tradeStateDes;
    }

    public Integer getTotalFee() {
        return totalFee;
    }

    public String getBankType() {
        return bankType;
    }

    public Integer getCashFee() {
        return cashFee;
    }

    public String getTimeEnd() {
        return timeEnd;
    }
}
