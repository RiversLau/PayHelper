package com.yoxiang.payhelper.wxpay.exception;

import com.yoxiang.payhelper.PayException;

/**
 * Author: Rivers
 * Date: 2018/2/12 21:49
 */
public class WechatPayException extends PayException {

    public WechatPayException() {
        super();
    }

    public WechatPayException(String msg) {
        super(msg);
    }

    public WechatPayException(Throwable cause) {
        super(cause);
    }

    public WechatPayException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
