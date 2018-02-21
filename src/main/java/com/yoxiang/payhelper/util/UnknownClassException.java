package com.yoxiang.payhelper.util;

import com.yoxiang.payhelper.PayException;

/**
 * Author: RiversLau
 * Date: 2018/1/3 12:29
 */
public class UnknownClassException extends PayException {

    public UnknownClassException() {
        super();
    }

    public UnknownClassException(String message) {
        super(message);
    }

    public UnknownClassException(Throwable cause) {
        super(cause);
    }

    public UnknownClassException(String message, Throwable cause) {
        super(message, cause);
    }
}
