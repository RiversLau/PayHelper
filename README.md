# PayHelper
支付开源框架（暂时只支持微信支付）

由于微信支付申请退款接口需要双向证书，另外退款可以直接在微信后台操作，相比通过应用系统自己的后台，微信的后台操作更简单直接，因此不在此项目中添加关于有关退款、查询退款以及下载对账单等接口；目前项目只支持统一下单、查询订单、关闭订单、微信支付通知。


引入Maven依赖

      <dependency>
         <groupId>vip.yoxiang</groupId>
         <artifactId>PayHelper</artifactId>
         <version>${version}</version>
      </dependency>

使用简介

项目资源目录中添加wx_merchant.ini配置文件，配置商户APPID、商户ID、商户API秘钥，格式如下

====================================================================

# 商户应用ID
appId=XXXXXX

# 商户ID
mchId=XXXXXX

# 商户API密钥
mchKey=XXXXX

=====================================================================

通过Merchant.getInstance()获取商户对象，该方法默认自动加载资源目录下的wx_merchant.ini配置文件；
当然你也可以通过Merchant.getInstance("资源路径")方法加载指定路径中的配置文件，可以通过classpath、url等方式加载配置文件

   

统一下单

    String notifyUrl = "回调地址";
    Merchant merchant = Merchant.getInstance();
    WechatPayHeader payHeader = new WechatPayHeader(merchant.getAppId(), merchant.getMchId(), merchant.getMchKey());
    WechatUnifiedOrderRequest unifiedOrderRequest = new WechatUnifiedOrderRequest(payHeader, notifyUrl);
    unifiedOrderRequest.setBody("测试数据");
    unifiedOrderRequest.setAttach("测试附加内容");
    unifiedOrderRequest.setOutTradeNo("201802131509358989");
    unifiedOrderRequest.setSpbillCreateIp("127.0.0.1");
    unifiedOrderRequest.setTotalFee("1");
    unifiedOrderRequest.setTradeType("APP");
    WechatPayRequestHandler requestHandler = new WechatPayRequestHandler(unifiedOrderRequest);
    WechatPayResponse wechatPay = requestHandler.process();
    WechatUnifiedOrderResponse unifiedOrderResponse = (WechatUnifiedOrderResponse) wechatPay;
        

查询订单

    Merchant merchant = Merchant.getInstance();
    WechatQueryOrderRequest queryOrderRequest = new WechatQueryOrderRequest(merchant.getAppId(), merchant.getMchId(),     merchant.getMchKey());
    queryOrderRequest.setOutTradeNo("201802131509358989");
    WechatPayRequestHandler requestHandler = new WechatPayRequestHandler(queryOrderRequest);
    WechatQueryOrderResponse queryOrderResponse = (WechatQueryOrderResponse) requestHandler.process();
    

关闭订单

    Merchant merchant = Merchant.getInstance();
    WechatCloseOrderRequest closeOrderRequest = new WechatCloseOrderRequest(merchant.getAppId(), merchant.getMchId(), merchant.getMchKey());
    closeOrderRequest.setOutTradeNo("201802131509358989");
    WechatPayRequestHandler requestHandler = new WechatPayRequestHandler(closeOrderRequest);
    WechatCloseOrderResponse closeOrderResponse = (WechatCloseOrderResponse) requestHandler.process();
