package com.yoxiang.payhelper;

import com.yoxiang.payhelper.wxpay.*;
import com.yoxiang.payhelper.wxpay.handler.WechatPayRequestHandler;
import org.junit.Assert;
import org.junit.Test;

/**
 * Author: Rivers
 * Date: 2018/2/13 14:56
 */
public class WechatPayTest {

    /**
     * 统一下单
     */
    @Test
    public void testUnifiedOrder() {

        WechatUnifiedOrderRequest unifiedOrderRequest = new WechatUnifiedOrderRequest();
        unifiedOrderRequest.setBody("测试数据");
        unifiedOrderRequest.setAttach("测试附加内容");
        unifiedOrderRequest.setNotifyUrl("http://riverslau.natapp1.cc/wxpay/msg");
        unifiedOrderRequest.setOutTradeNo("201802131509358989");
        unifiedOrderRequest.setSpbillCreateIp("127.0.0.1");
        unifiedOrderRequest.setTotalFee("1");
        unifiedOrderRequest.setTradeType("APP");

        WechatPayRequestHandler requestHandler = new WechatPayRequestHandler(unifiedOrderRequest);
        WechatPay wechatPay = requestHandler.process();
        WechatUnifiedOrderResponse unifiedOrderResponse = (WechatUnifiedOrderResponse) wechatPay;

        Assert.assertEquals(WechatPayStatusCode.SUCCESS, unifiedOrderResponse.getReturnCode());
        Assert.assertEquals(WechatPayStatusCode.SUCCESS, unifiedOrderResponse.getResultCode());

        System.out.println(unifiedOrderResponse.getPrePayId());
    }

    /**
     * 查询订单
     */
    @Test
    public void testQueryOrder() {
        WechatQueryOrderRequest queryOrderRequest = new WechatQueryOrderRequest();
        queryOrderRequest.setOutTradeNo("201802131509358989");

        WechatPayRequestHandler requestHandler = new WechatPayRequestHandler(queryOrderRequest);
        WechatQueryOrderResponse queryOrderResponse = (WechatQueryOrderResponse) requestHandler.process();

        Assert.assertEquals(WechatPayStatusCode.SUCCESS, queryOrderResponse.getReturnCode());
        Assert.assertEquals(WechatPayStatusCode.SUCCESS, queryOrderResponse.getResultCode());

        System.out.println("交易状态：" + queryOrderResponse.getTradeState() + ";交集状态描述：" + queryOrderResponse.getTradeStateDes());
    }
}
