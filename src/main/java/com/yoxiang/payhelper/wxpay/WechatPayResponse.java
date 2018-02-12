package com.yoxiang.payhelper.wxpay;

/**
 * Author: Rivers
 * Date: 2018/2/12 21:15
 */
public abstract class WechatPayResponse extends WechatPay {

    protected String returnCode;          // 通信标识
    protected String returnMsg;           // 通信消息

    protected String resultCode;          // 业务状态码
    protected String errCode;             // 错误码
    protected String errCodeDes;          // 错误码描述信息

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public String getReturnMsg() {
        return returnMsg;
    }

    public void setReturnMsg(String returnMsg) {
        this.returnMsg = returnMsg;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrCodeDes() {
        return errCodeDes;
    }

    public void setErrCodeDes(String errCodeDes) {
        this.errCodeDes = errCodeDes;
    }
}
