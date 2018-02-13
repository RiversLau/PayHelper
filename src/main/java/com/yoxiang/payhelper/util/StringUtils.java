package com.yoxiang.payhelper.util;

/**
 * Author: Rivers
 * Date: 2018/2/13 15:34
 */
public class StringUtils {

    /**
     * 判断字符串是否为空
     * 如果字符串为null，长度为0，trim后长度为0，则返回true；否则返回false
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        boolean flag = false;
        if (str == null || str.length() == 0 || str.trim().length() == 0) {
            flag = true;
        }
        return flag;
    }
}
