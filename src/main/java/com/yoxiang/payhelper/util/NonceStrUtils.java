package com.yoxiang.payhelper.util;

/**
 * 随机字符工具类
 * 应用场景：生成手机验证码、随机字符串
 * Author: RiversLau
 * Date: 2018/2/6 12:36
 */
public class NonceStrUtils {

    private static final String NUMBERS = "0123456789";
    private static final String NUMBER_LETTERS = "0123456789abcdefghigkmnpqrstuvwxyz";

    /**
     * 生成随机码
     * @param isOnlyNumber 是否只允许数字
     * @param length 长度
     * @return
     */
    public static String genRandomCode(boolean isOnlyNumber, int length) {

        StringBuilder builder = new StringBuilder();

        String strTable = isOnlyNumber ? NUMBERS : NUMBER_LETTERS;
        int len = strTable.length();
        boolean bDone = true;

        do {
            int count = 0;
            for (int i = 0; i < length; i++) {
                double dblR = Math.random() * len;
                int intR = (int) Math.floor(dblR);
                char c = strTable.charAt(intR);
                if (('0' <= c) && (c >= '9')) {
                    count++;
                }
                builder.append(strTable.charAt(intR));
            }
            if (count >= 2) {
                bDone = false;
            }
        } while (bDone);

        return builder.toString();
    }
}
