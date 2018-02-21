package com.yoxiang.payhelper.wxpay.handler;

import com.yoxiang.payhelper.util.Charsets;
import com.yoxiang.payhelper.util.WechatPayUtils;
import com.yoxiang.payhelper.wxpay.*;
import com.yoxiang.payhelper.wxpay.exception.WechatPayException;
import com.yoxiang.payhelper.wxpay.response.WechatCloseOrderResponse;
import com.yoxiang.payhelper.wxpay.response.WechatQueryOrderResponse;
import com.yoxiang.payhelper.wxpay.response.WechatUnifiedOrderResponse;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Author: Rivers
 * Date: 2018/2/12 18:05
 */
public class WechatPayRequestHandler {

    private static final Logger logger = LoggerFactory.getLogger(WechatPayRequestHandler.class);

    private final DocumentBuilder builder;
    private final TransformerFactory transFactory;

    private WechatPay wechatPay;
    private final String unifiedOrderUrl;
    private final String queryOrderUrl;
    private final String closeOrderUrl;

    public WechatPayRequestHandler(WechatPay wechatPay) {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            builder = factory.newDocumentBuilder();
            transFactory = TransformerFactory.newInstance();

            this.wechatPay = wechatPay;
            this.unifiedOrderUrl = WechatPayApies.UNIFIED_ORDER_API_URL;
            this.queryOrderUrl = WechatPayApies.QUERY_ORDER_API_URL;
            this.closeOrderUrl = WechatPayApies.CLOSE_ORDER_API_URL;
        } catch (ParserConfigurationException e) {
            String msg = "构建Xml Document失败";
            throw new IllegalStateException(msg);
        }
    }

    /**
     * 处理请求
     */
    public WechatPayResponse process() {
        return handleWechatOrder(this.wechatPay);
    }

    /**
     * 处理请求，并得到响应对象
     * @param wechatPay
     * @return
     */
    private WechatPayResponse handleWechatOrder(WechatPay wechatPay) {

        Document document = builder.newDocument();

        // 生成签名
        String sign = WechatPayUtils.genSign(wechatPay);
        wechatPay.getWechatPayHeader().setSign(sign);
        wechatPay.write(document);

        // 待发送的XML字符串数据
        String toSendXml = transXmlDocment2String(document);
        logger.info("=========发送的XML : " + toSendXml);

        // 构建HttpClient对象
        CloseableHttpClient client = HttpClientBuilder.create().build();

        HttpPost httpost;
        String payType = wechatPay.getWechatPayHeader().getPayType();
        String resultType = wechatPay.getWechatPayHeader().getResultType();
        if (WechatPayTradeTypes.UNIFIED_ORDER.equals(payType)) {
            httpost = new HttpPost(unifiedOrderUrl);
        } else if (WechatPayTradeTypes.QUERY_ORDER.equals(payType)) {
            httpost = new HttpPost(queryOrderUrl);
        } else if (WechatPayTradeTypes.CLOSE_ORDER.equals(payType)) {
            httpost = new HttpPost(closeOrderUrl);
        } else {
            throw new WechatPayException("错误的微信交易类型");
        }

        InputStream is = null;
        HttpResponse response = null;
        try {
            // 发送Http请求，并处理响应数据
            httpost.setEntity(new StringEntity(toSendXml, Charsets.DEFAULT_CHARSET_NAME));
            response = client.execute(httpost);
            is = response.getEntity().getContent();
            Document resultDoc = builder.parse(is);

            // 根据resultType得到对应的响应对象，暂时处理统一下单、查询订单、关单接口，
            // 后续添加查询退款、申请退款
            if (WechatPayTradeTypes.UNIFIED_ORDER_RESULT.equals(resultType)) {
                WechatUnifiedOrderResponse preOrder = new WechatUnifiedOrderResponse();
                preOrder.read(resultDoc);
                return preOrder;
            } else if (WechatPayTradeTypes.QUERY_ORDER_RESULT.equals(resultType)) {
                WechatQueryOrderResponse queryOrder = new WechatQueryOrderResponse();
                queryOrder.read(resultDoc);
                return queryOrder;
            } else if (WechatPayTradeTypes.CLOSE_ORDER_RESULT.equals(resultType)) {
                WechatCloseOrderResponse queryOrder = new WechatCloseOrderResponse();
                queryOrder.read(resultDoc);
                return queryOrder;
            } else {
                throw new WechatPayException("错误的微信响应类型【" + resultType + "】，暂时无法处理");
            }
        } catch (Exception e) {
            throw new WechatPayException("处理微信支付失败", e);
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    // do nothing
                }
            }
            if (null != httpost && !httpost.isAborted()) {
                httpost.abort();
            }
            HttpClientUtils.closeQuietly(client);
            HttpClientUtils.closeQuietly(response);
        }
    }

    /**
     * 将XML文档转换为字符串
     * @param document xml 文档对象
     * @return
     */
    public String transXmlDocment2String(Document document) {

        try {
            Transformer transformer = transFactory.newTransformer();

            transformer.setOutputProperty("encoding", Charsets.DEFAULT_CHARSET_NAME);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            transformer.transform(new DOMSource(document), new StreamResult(bos));

            return bos.toString(Charsets.DEFAULT_CHARSET_NAME);
        } catch (Exception e) {
            throw new WechatPayException("将XML Document对象转换为字符串时出错");
        }
    }
}
