package com.yoxiang.payhelper.wxpay.handler;

import com.yoxiang.payhelper.util.Charsets;
import com.yoxiang.payhelper.wxpay.WechatPayResponse;
import com.yoxiang.payhelper.wxpay.response.WechatNotificationResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 微信支付回调通知处理器
 * 调用process，且callBack回复后，必须调用close方法关闭输入、输出流
 * Author: Rivers
 * Date: 2018/2/13 21:30
 */
public class WechatPayNotificationHandler {

    private static final Logger logger = LoggerFactory.getLogger(WechatPayNotificationHandler.class);

    private InputStream input;
    private OutputStream output;

    private DocumentBuilder builder;

    public WechatPayNotificationHandler(InputStream input, OutputStream output) {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            builder = factory.newDocumentBuilder();

            this.input = input;
            this.output = output;
        } catch (ParserConfigurationException e) {
            throw new IllegalStateException("构建Xml Document Builder失败", e);
        }
    }

    /**
     * 通过输入流，将XML文档转换为微信回调通知对象
     * @return
     */
    public WechatPayResponse process() {
        try {
            Document document = builder.parse(input);
            WechatNotificationResponse notifyResult = new WechatNotificationResponse();
            notifyResult.read(document);
            return notifyResult;
        } catch (Exception e) {
            throw new IllegalStateException("处理微信回调内容出错，无法转换输入流");
        }
    }

    /**
     * 回复微信回调接口
     * 接收到微信回调后，处理完业务逻辑，需要回复通知给微信服务端
     * @param msg
     */
    public void callBack(String msg) {

        try {
            output.write(msg.getBytes(Charsets.DEFAULT_CHARSET_NAME));
        } catch (Exception e) {
            throw new IllegalStateException("写出内容失败");
        }
    }

    /**
     * 关闭输入、输出流，处理完微信回调后必须调用此方法
     */
    public void close() {
        try {
            if (input != null) {
                input.close();
            }
            if (output != null) {
                output.flush();
                output.close();
            }
        } catch (IOException e) {
            logger.error("关闭输入输出流失败", e);
            // do nothing
        }
    }
}
