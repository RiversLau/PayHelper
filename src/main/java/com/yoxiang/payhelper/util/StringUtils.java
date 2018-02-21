package com.yoxiang.payhelper.util;

/**
 * Author: Rivers
 * Date: 2018/2/13 15:34
 */
public class StringUtils {

    private static final String EMPTY_STRING = "";

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

    /**
     * 判断字符串是否长度大于0
     * @param str
     * @return
     */
    public static boolean hasLength(String str) {
        return str != null && str.length() > 0;
    }

    /**
     * 清理字符串
     * @param in
     * @return
     */
    public static String clean(String in) {
        String out = in;

        if (in != null) {
            out = in.trim();
            if (out.equals(EMPTY_STRING)) {
                out = null;
            }
        }
        return out;
    }
}
