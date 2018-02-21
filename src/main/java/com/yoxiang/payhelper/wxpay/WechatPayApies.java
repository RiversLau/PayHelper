package com.yoxiang.payhelper.wxpay;

/**
 * 微信支付API接口地址
 * Author: Rivers
 * Date: 2018/2/12 18:18
 */
public interface WechatPayApies {

    // 统一下单API URL地址
    String UNIFIED_ORDER_API_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";

    // 查询订单API URL地址
    String QUERY_ORDER_API_URL = "https://api.mch.weixin.qq.com/pay/orderquery";

    // 关闭订单API URL地址
    String CLOSE_ORDER_API_URL = "https://api.mch.weixin.qq.com/pay/closeorder";
}
