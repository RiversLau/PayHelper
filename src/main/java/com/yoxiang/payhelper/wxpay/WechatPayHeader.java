package com.yoxiang.payhelper.wxpay;

import com.yoxiang.payhelper.util.WechatPayUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Author: RiversLau
 * Date: 2018/2/6 11:43
 */
public class WechatPayHeader {

    private String appId;           // 应用ID
    private String mchId;           // 商户ID
    private String mchKey;          // 商户API 秘钥
    private String nonceStr;        // 随机字符串

    private String sign;            // 签名

    private String payType;
    private String resultType;

    public WechatPayHeader() {
        this.appId = Merchant.APP_ID;
        this.mchId = Merchant.MCH_ID;
        this.mchKey = Merchant.MCH_KEY;
        this.nonceStr = WechatPayUtils.genNonceStr();
    }

    protected void read(Document document) {
        this.appId = document.getElementsByTagName(WechatPayXmlElements.APPID).item(0).getTextContent();
        this.mchId = document.getElementsByTagName(WechatPayXmlElements.MCH_ID).item(0).getTextContent();
        this.nonceStr = document.getElementsByTagName(WechatPayXmlElements.NONCE_STR).item(0).getTextContent();
        this.sign = document.getElementsByTagName(WechatPayXmlElements.SIGN).item(0).getTextContent();
    }

    protected void write(Element root, Document document) {

        Element appIdEle = document.createElement(WechatPayXmlElements.APPID);
        appIdEle.setTextContent(this.appId);

        Element mchIdEle = document.createElement(WechatPayXmlElements.MCH_ID);
        mchIdEle.setTextContent(this.mchId);

        Element nonceStrEle = document.createElement(WechatPayXmlElements.NONCE_STR);
        nonceStrEle.setTextContent(this.nonceStr);

        Element signEle = document.createElement(WechatPayXmlElements.SIGN);
        signEle.setTextContent(this.sign);

        root.appendChild(appIdEle);
        root.appendChild(mchIdEle);
        root.appendChild(nonceStrEle);
        root.appendChild(signEle);
    }
}
