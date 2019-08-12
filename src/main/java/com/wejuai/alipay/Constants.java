package com.wejuai.alipay;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * @author YQ.Huang
 */
public class Constants {

    /**
     * 支付宝OpenAPI网关地址
     */
    public static final String OPENAPI_GATEWAY_URL = "https://openapi.alipay.com/gateway.do";

    /**
     * 支付宝OpenAPI公钥
     */
    public static final String OPENAPI_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAhOvDjcCBz/s0AWU8sydztzh5Cei4thpW+cZPtVow90rEI8d/bjWkcFYHj6uKVLMpVZxiOGTIoj6k7p5pbRt3LbcS9jqygROHOqLf8ICXOJ+ZRATwUogQrCGCNTfpVPcJcy5s/yYzpwOhwesxcVNy3AzBuEi3zhUvGMRzr8EX5YQXrMWc2u+RVt14SfTpLBCxh1+93xSz+gCnxMla5m7G5RkBrg4jUJCEaofQYQkYxBqUN2chdW0xS03jGv+0ZpUhbUg/QYPNxhkTFoBiSP/QJFYMb4YVU8akS1g23P9dHMzl77ns2aFmepcqntzk1dP47pCo8M5J9TB/eUcFfOELcwIDAQAB";

    /**
     * 支付宝MAPI网关地址
     */
    public static final String MAPI_GATEWAY_URL = "https://mapi.alipay.com/gateway.do";

    /**
     * 支付宝MAPI公钥
     */
    public static final String MAPI_PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnxj/9qwVfgoUh/y2W89L6BkRAFljhNhgPdyPuBV64bfQNN1PjbCzkIM6qRdKBoLPXmKKMiFYnkd6rAoprih3/PrQEB/VsW8OoM8fxn67UDYuyBTqA23MML9q1+ilIZwBC2AQ2UBVOrFXfFl75p6/B5KsiNG9zpgmLCUYuLkxpLQIDAQAB";

    /**
     * 即时到账支付Service
     */
    public static final String DIRECT_PAY_SERVICE = "create_direct_pay_by_user";

    /**
     * 即时到账退款Service
     */
    public static final String DIRECT_REFUND_SERVICE = "refund_fastpay_by_platform_pwd";

    /**
     * 字符集
     */
    public static final String CHARSET = "UTF-8";

    /**
     * 签名类型
     */
    public static final String SIGN_TYPE = "RSA2";

    /**
     * 格式
     */
    public static final String FORMAT = "json";

    /**
     * 版本
     */
    public static final String VERSION = "1.0";

    /**
     * APP支付Method
     */
    public static final String APP_PAY_METHOD = "alipay.trade.app.pay";

    /**
     * 预创建Method
     */
    public static final String PRECREATE_METHOD = "alipay.trade.precreate";

    /**
     * 退款查询Method
     */
    public static final String REFUND_QUERY_METHOD = "alipay.trade.fastpay.refund.query";

    /**
     * 退款Method
     */
    public static final String REFUND_METHOD = "alipay.trade.refund";

    /**
     * 交易查询Method
     */
    public static final String TRADE_QUERY_METHOD = "alipay.trade.query";

    /**
     * 手机网站支付Method
     */
    public static final String WAP_PAY_METHOD = "alipay.trade.wap.pay";

    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    public static final DateFormat TIMESTAMP_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static final DateFormat REFUND_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static final DateFormat BATCH_NO_FORMAT = new SimpleDateFormat("yyyyMMddHHmmssSSS");

    public static final Charset CHARSET_OBJ = Charset.forName(CHARSET);

}
