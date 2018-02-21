package com.yoxiang.payhelper.wxpay;

import org.w3c.dom.Document;

/**
 * 微信支付响应对象，用于接收微信服务端响应数据
 * Author: Rivers
 * Date: 2018/2/12 21:15
 */
public abstract class WechatPayResponse {

    protected WechatPayHeader wechatPayHeader;

    protected String returnCode;                // 通信标识
    protected String returnMsg;                 // 通信消息

    protected String resultCode;                // 业务状态码
    protected String errCode;                   // 错误码
    protected String errCodeDes;                // 错误码描述信息

    /**
     * 抽象方法，读取微信服务端响应，并转换为相应响应对象的字段值
     * @param document
     */
    public abstract void read(Document document);

    //========================= GETTER/SETTER方法 =========================//

    public WechatPayHeader getWechatPayHeader() {
        return wechatPayHeader;
    }

    public void setWechatPayHeader(WechatPayHeader wechatPayHeader) {
        this.wechatPayHeader = wechatPayHeader;
    }

    public String getReturnCode() {
        return returnCode;
    }

    public String getReturnMsg() {
        return returnMsg;
    }

    public String getResultCode() {
        return resultCode;
    }

    public String getErrCode() {
        return errCode;
    }

    public String getErrCodeDes() {
        return errCodeDes;
    }
}
