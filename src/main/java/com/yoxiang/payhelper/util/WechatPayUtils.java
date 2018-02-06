package com.yoxiang.payhelper.util;

/**
 * Author: RiversLau
 * Date: 2018/2/6 11:33
 */
public class WechatPayUtils {

    /**
     * 生成随机字符串
     * @return
     */
    public static String genNonceStr() {
        return NonceStrUtils.genRandomCode(false, 8);
    }

    public static String genUnifiedOrderSign() {

        return null;
    }

    /**
     * 构建随机数
     * @param length 随机数长度
     *
     * @return
     */
    private static int buildRandom(int length) {
        int num = 1;
        double random = Math.random();
        if (random < 0.1) {
            random = random + 0.1;
        }
        for (int i = 0; i < length; i++) {
            num = num * 10;
        }
        return (int) ((random * num));
    }
}
