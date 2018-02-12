package com.yoxiang.payhelper.wxpay;

/**
 * 微信支付交易码
 * Author: Rivers
 * Date: 2018/2/12 21:43
 */
public interface WechatPayStatusCode {

    /**成功，可用于返回的状态码、业务结果和支付成功*/
    String SUCCESS = "SUCCESS";
    /**失败，可用于返回的状态码、业务结果*/
    String FAIL = "FAIL";

    /**支付失败*/
    String TRADE_PAYERROR = "PAYERROR";
    /**转入退款*/
    String TRADE_REFUND = "REFUND";
    /**未支付*/
    String TRADE_NOT_PAY = "NOTPAY";
    /**已关闭*/
    String TRADE_CLOSED = "CLOSED";
    /**用户支付中*/
    String TRADE_USERPAYING = "USERPAYING";


    /**订单不存在*/
    String ORDER_NOT_EXIST = "ORDERNOTEXIST";
    /**系统错误*/
    String SYSTEM_ERR = "SYSTEMERROR";
    /**订单号重复*/
    String OUT_TRADE_NO_USED = "OUT_TRADE_NO_USED";
    /**订单已支付*/
    String ORDERPAID = "ORDERPAID";
    /**订单已关闭*/
    String ORDERCLOSED = "ORDERCLOSED";
    /**余额不足*/
    String NOTENOUGH = "NOTENOUGH";
}
