package com.yoxiang.payhelper.util;

import com.yoxiang.payhelper.PayException;

/**
 * Author: RiversLau
 * Date: 2018/1/3 14:04
 */
public class InstantiationException extends PayException {

    public InstantiationException() {
        super();
    }

    public InstantiationException(String message) {
        super(message);
    }

    public InstantiationException(Throwable cause) {
        super(cause);
    }

    public InstantiationException(String message, Throwable cause) {
        super(message, cause);
    }
}
