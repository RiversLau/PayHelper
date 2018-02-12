package com.yoxiang.payhelper;

/**
 * Author: Rivers
 * Date: 2018/2/12 21:48
 */
public class PayException extends RuntimeException {

    public PayException() {
        super();
    }

    public PayException(String msg) {
        super(msg);
    }

    public PayException(Throwable cause) {
        super(cause);
    }

    public PayException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
