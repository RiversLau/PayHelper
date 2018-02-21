package com.yoxiang.payhelper.wxpay;

import com.yoxiang.payhelper.util.ConfigurationException;
import com.yoxiang.payhelper.util.ResourceUtils;
import com.yoxiang.payhelper.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * 商户信息
 * Author: RiversLau
 * Date: 2018/2/6 11:48
 */
public class Merchant {

    private static final Logger logger = LoggerFactory.getLogger(Merchant.class);

    // 默认配置文件路径
    private static final String DEFAULT_CONFIG_FILE = "classpath:wx_merchant.ini";

    public static final String DEFAULT_CHARSET_NAME = "UTF-8";

    /**Ini配置文件注释*/
    public static final String COMMENT_POUND = "#";
    /**Ini配置文件分隔符*/
    public static final String COMMENT_SEMICOLON = ";";
    /**转义字符*/
    protected static final char ESCAPE_TOKEN = '\\';

    private final List<String[]> infos;

    private String appId;
    private String mchId;
    private String mchKey;

    private Merchant() {
        infos = new LinkedList<String[]>();
    }

    public static Merchant getInstance() {
        return getInstance(DEFAULT_CONFIG_FILE);
    }

    /**
     * 加载指定路径下的资源文件来创建Merchant实例
     *
     * @param resourcePath 资源路径
     * @return
     */
    public static Merchant getInstance(String resourcePath) {
        if (!StringUtils.hasLength(resourcePath)) {
            throw new IllegalArgumentException("Resource path argument cannot be null or empty.");
        }
        Merchant merchant = new Merchant();
        merchant.loadFromPath(resourcePath);
        return merchant;
    }

    public void loadFromPath(String resourcePath) {
        InputStream is;
        try {
            is = ResourceUtils.getInputStreamForPath(resourcePath);
        } catch (IOException e) {
            throw new ConfigurationException(e);
        }
        load(is);
    }

    /**
     * 加载InputStream中的数据到Merchant实例
     *
     * @param is
     */
    public void load(InputStream is) {
        if (is == null) {
            throw new NullPointerException("InputStream argument cannot be null.");
        }
        InputStreamReader isr;
        try {
            isr = new InputStreamReader(is, DEFAULT_CHARSET_NAME);
        } catch (UnsupportedEncodingException e) {
            throw new ConfigurationException(e);
        }
        load(isr);
    }

    /**
     * 加载Reader中的数据到Merchant实例
     *
     * @param reader
     */
    public void load(Reader reader) {
        Scanner scanner = new Scanner(reader);
        try {
            load(scanner);
        } finally {
            try {
                scanner.close();
            } catch (Exception ex) {
                logger.debug("Unable to cleanly close the InputStream scanner. Non-critical -ignoring.", ex);
            }
        }
    }

    public void load(Scanner scanner) {

        while (scanner.hasNextLine()) {

            String rawLine = scanner.nextLine();
            String line = StringUtils.clean(rawLine);

            if (line == null || line.startsWith(COMMENT_POUND) || line.startsWith(COMMENT_SEMICOLON)) {
                continue;
            }

            String[] keyValuePair = splitKeyValue(line);
            infos.add(keyValuePair);
        }

        // 处理商户信息
        if (!infos.isEmpty()) {
            loadMerchantInfo();
        }
    }

    private void loadMerchantInfo() {
        if (infos.isEmpty()) {
            throw new ConfigurationException("配置错误");
        }

        String[] appPair = infos.get(0);
        this.appId = appPair[1];

        String[] mchPair = infos.get(1);
        this.mchId = mchPair[1];

        String[] mchKeyPair = infos.get(2);
        this.mchKey = mchKeyPair[1];
    }

    /**
     * 处理Ini配置文件每行数据，拆分成key-value对
     * @param keyValueLine
     * @return
     */
    private static String[] splitKeyValue(String keyValueLine) {
        String line = StringUtils.clean(keyValueLine);
        if (line == null) {
            return null;
        }
        StringBuilder keyBuilder = new StringBuilder();
        StringBuilder valueBuilder = new StringBuilder();

        boolean buildingKey = true;
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);

            if (buildingKey) {
                if (isKeyValueSeparatorChar(c) && !isCharEscaped(line, i)) {
                    buildingKey = false;
                } else {
                    keyBuilder.append(c);
                }
            } else {
                if (valueBuilder.length() == 0 && isKeyValueSeparatorChar(c) && !isCharEscaped(line, i)) {

                } else {
                    valueBuilder.append(c);
                }
            }
        }

        String key = StringUtils.clean(keyBuilder.toString());
        String value = StringUtils.clean(valueBuilder.toString());
        if (key == null || value == null) {
            String msg = "Line argument must contain a key and a value. Only one string token was found.";
            throw new IllegalArgumentException(msg);
        }

        logger.trace("Discovered key/value pair: {} = {}", key, value);
        return new String[]{key, value};
    }

    /**
     * 判断是否为key value分隔符
     * 空格(" ")、冒号(":")、等号("=")被认定为分隔符
     *
     * @param c
     * @return
     */
    private static boolean isKeyValueSeparatorChar(char c) {
        return Character.isWhitespace(c) || c == ':' || c == '=';
    }

    /**
     * 判断索引位置字符是否为转义字符
     * 如果该字符前面的为"\"，则为需要转移的
     *
     * @param s     字符序列
     * @param index 索引位置
     * @return
     */
    private static boolean isCharEscaped(CharSequence s, int index) {
        return index > 0 && s.charAt(index - 1) == ESCAPE_TOKEN;
    }

    //========================= GETTER/SETTER方法 =========================//

    public String getAppId() {
        return appId;
    }

    public String getMchId() {
        return mchId;
    }

    public String getMchKey() {
        return mchKey;
    }
}
