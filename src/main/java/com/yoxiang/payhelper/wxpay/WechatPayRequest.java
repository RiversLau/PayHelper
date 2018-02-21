package com.yoxiang.payhelper.wxpay;

import org.w3c.dom.Document;

/**
 * 微信支付请求对象
 * Author: RiversLau
 * Date: 2018/2/6 11:39
 */
public abstract class WechatPayRequest {

    /**
     * 微信支付接口Header，用于处理相同的字段
     */
    public WechatPayHeader wechatPayHeader;

    /**
     * 将请求对象转换为XML Document对象
     * @param document
     */
    public abstract void write(Document document);

    //========================= GETTER/SETTER方法 =========================//

    public WechatPayHeader getWechatPayHeader() {
        return wechatPayHeader;
    }
}
