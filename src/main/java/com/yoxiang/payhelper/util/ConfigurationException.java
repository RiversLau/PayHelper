package com.yoxiang.payhelper.util;

import com.yoxiang.payhelper.PayException;

/**
 * Author: Rivers
 * Date: 2018/2/21 13:13
 */
public class ConfigurationException extends PayException {

    public ConfigurationException() {
        super();
    }

    public ConfigurationException(String msg) {
        super(msg);
    }

    public ConfigurationException(Throwable cause) {
        super(cause);
    }

    public ConfigurationException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
