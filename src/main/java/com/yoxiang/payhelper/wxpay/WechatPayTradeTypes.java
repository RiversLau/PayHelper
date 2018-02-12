package com.yoxiang.payhelper.wxpay;

/**
 * 微信支付交易类型以及响应结果类型
 * Author: Rivers
 * Date: 2018/2/12 18:30
 */
public interface WechatPayTradeTypes {

    /**统一下单*/
    String UNIFIED_ORDER = "unified";
    /**预支付订单*/
    String UNIFIED_ORDER_RESULT = "preorder";

    /**订单查询*/
    String QUERY_ORDER = "query";
    String QUERY_ORDER_RESULT = "result";


    /**支付结果通知*/
    String ORDER_RESULT = "result";

    /**关闭订单*/
    String CLOSE_ORDER = "close";
    String CLOSE_ORDER_RESULT = "close_result";

    String NOTIFY_RESULT = "notify_result";

    /**微信网页授权支付*/
    String JSAPI_TYPE = "JSAPI";
    /**APP内支付*/
    String APP_TYPE = "APP";
}
