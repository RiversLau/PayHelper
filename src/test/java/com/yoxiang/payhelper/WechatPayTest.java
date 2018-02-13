package com.yoxiang.payhelper;

import com.yoxiang.payhelper.wxpay.*;
import com.yoxiang.payhelper.wxpay.handler.WechatPayRequestHandler;
import com.yoxiang.payhelper.wxpay.request.WechatCloseOrderRequest;
import com.yoxiang.payhelper.wxpay.request.WechatQueryOrderRequest;
import com.yoxiang.payhelper.wxpay.request.WechatUnifiedOrderRequest;
import com.yoxiang.payhelper.wxpay.response.WechatCloseOrderResponse;
import com.yoxiang.payhelper.wxpay.response.WechatQueryOrderResponse;
import com.yoxiang.payhelper.wxpay.response.WechatUnifiedOrderResponse;
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

    /**
     * 关闭订单
     */
    @Test
    public void testCloseOrder() {
        WechatCloseOrderRequest closeOrderRequest = new WechatCloseOrderRequest();
        closeOrderRequest.setOutTradeNo("201802131509358989");

        WechatPayRequestHandler requestHandler = new WechatPayRequestHandler(closeOrderRequest);
        WechatCloseOrderResponse closeOrderResponse = (WechatCloseOrderResponse) requestHandler.process();

        if (closeOrderResponse.isCloseOrderSucceed()) {
            System.out.println("订单关闭成功");
        } else {
            System.out.println("订单关闭失败，errCode=" + closeOrderResponse.getErrCode() +
                    "，errCodeDes=" + closeOrderResponse.getErrCodeDes());
        }
    }
}
