package com.yoxiang.payhelper.wxpay;

/**
 * Author: RiversLau
 * Date: 2018/2/6 12:47
 */
public interface WechatPayXmlElements {

    /**根节点*/
    String ROOT = "xml";
    /**公众账号*/
    String APPID = "appid";
    /**商户号*/
    String MCH_ID = "mch_id";
    /**随机字符串*/
    String NONCE_STR = "nonce_str";
    /**商户订单号*/
    String OUT_TRADE_NO = "out_trade_no";
    /**微信订单号*/
    String TRANSACTION_ID = "transaction_id";

    /**签名*/
    String SIGN = "sign";
    /**商品描述*/
    String BODY = "body";
    /**商品详情*/
    String DETAIL = "detail";

    /**附加数据*/
    String ATTACH = "attach";

    /**货币类型*/
    String FEE_TYPE = "fee_type";
    /**总金额*/
    String TOTAL_FEE = "total_fee";
    /**现金支付金额*/
    String CASH_FEE = "cash_fee";
    /**现金支付货币类型，默认人民币CNY*/
    String CASH_FEE_TYPE = "cash_fee_type";
    /**代金券金额*/
    String COUPON_FEE = "coupon_fee";
    /**代金券使用数量*/
    String COUPON_COUNT = "coupon_count";
    /**终端IP*/
    String SPBILL_CREATE_IP = "spbill_create_ip";

    /** 交易开始时间*/
    String TIME_START = "time_start";
    /**交易结束时间*/
    String TIME_EXPIRE = "time_expire";
    /**商品标记*/
    String GOODS_TAG = "goods_tag";

    /**通知地址*/
    String NOTIFY_URL = "notify_url";
    /**交易类型*/
    String TRADE_TYPE = "trade_type";
    /**交易状态*/
    String TRADE_STATE = "trade_state";
    /**交易状态描述*/
    String TRADE_STATE_DES = "trade_state_desc";
    /**商品ID*/
    String PRODUCT_ID = "product_id";
    /**指定支付方式*/
    String LIMIT_PAY = "limit_pay";

    /**用户标识openid*/
    String OPENID = "openid";
    /**设备号*/
    String DEVICE_INFO = "device_info";

    /**返回状态码*/
    String RETURN_CODE = "return_code";
    /**返回信息*/
    String RETURN_MSG = "return_msg";
    /**业务结果*/
    String RESULT_CODE = "result_code";
    /**错误代码*/
    String ERR_CODE = "err_code";
    /**错误代码描述*/
    String ERR_CODE_DES = "err_code_des";
    /**是否关注*/
    String IS_SUBSCRIBE = "is_subscribe";
    /**付款银行*/
    String BANK_TYPE = "bank_type";
    /**支付完成时间*/
    String TIME_END = "time_end";
    /**子商户号*/
    String SUB_MCH_ID = "sub_mch_id";

    String PREPAY_ID = "prepay_id";
}
