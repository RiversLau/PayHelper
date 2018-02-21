package com.yoxiang.payhelper.wxpay.response;

import com.yoxiang.payhelper.wxpay.WechatPayResponse;
import com.yoxiang.payhelper.wxpay.WechatPayStatusCode;
import com.yoxiang.payhelper.wxpay.WechatPayXmlElements;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

/**
 * 关单响应对象
 * Author: Rivers
 * Date: 2018/2/13 20:20
 */
public class WechatCloseOrderResponse extends WechatPayResponse {

    /**
     * 读取关单微信服务端返回的XML文档，转换为关单响应对象
     * @param document
     */
    public void read(Document document) {

        this.returnCode = document.getElementsByTagName(WechatPayXmlElements.RETURN_CODE).item(0).getTextContent();
        if (WechatPayStatusCode.SUCCESS.equals(returnCode)) {
            wechatPayHeader.read(document);

            Node resultCodeNode = document.getElementsByTagName(WechatPayXmlElements.RESULT_CODE).item(0);
            this.resultCode = resultCodeNode == null ? null : resultCodeNode.getTextContent();

            Node errCodeNode = document.getElementsByTagName(WechatPayXmlElements.ERR_CODE).item(0);
            this.errCode = errCodeNode == null ? null : errCodeNode.getTextContent();

            Node errCodeDesNode = document.getElementsByTagName(WechatPayXmlElements.ERR_CODE_DES).item(0);
            this.errCodeDes = errCodeDesNode == null ? null : errCodeDesNode.getTextContent();
        } else {
            this.returnMsg = document.getElementsByTagName(WechatPayXmlElements.RETURN_MSG).item(0).getTextContent();
        }
    }

    /**
     * 本类主要读取微信服务端返回的数据，无需write方法
     * @param document
     */
    public void write(Document document) {
        // do nothing
    }

    /**
     * 判断是否关单成功
     * @return
     */
    public boolean isCloseOrderSucceed() {

        if (WechatPayStatusCode.SUCCESS.equals(this.resultCode)) {
            return true;
        }
        return false;
    }
}
