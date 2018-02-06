package com.yoxiang.payhelper.wxpay;

import org.w3c.dom.Document;

/**
 * Author: RiversLau
 * Date: 2018/2/6 11:39
 */
public abstract class WechatPay {

    /**
     *
     * @param document
     */
    public abstract void read(Document document);

    /**
     *
     * @param document
     */
    public abstract void write(Document document);
}
