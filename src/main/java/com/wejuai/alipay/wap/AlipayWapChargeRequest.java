package com.wejuai.alipay.wap;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.wejuai.alipay.AlipayRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ZM.Wang
 */
public class AlipayWapChargeRequest extends AlipayRequest<AlipayWapChargeResponse> {

    private final String outTradeNo;
    private final String subject;
    private final String totalAmount;
    private final String returnUrl;
    private final String notifyUrl;

    public AlipayWapChargeRequest(String outTradeNo, String subject, String totalAmount, String returnUrl, String notifyUrl) {
        this.outTradeNo = outTradeNo;
        this.subject = subject;
        this.totalAmount = totalAmount;
        this.returnUrl = returnUrl;
        this.notifyUrl = notifyUrl;
    }

    @Override
    public Map<String, String> buildRequestParams() throws JsonProcessingException {
        Map<String, String> params = super.buildRequestParams();
        params.put("method", "alipay.trade.wap.pay");
        params.put("return_url", returnUrl);
        params.put("notify_url", notifyUrl);
        return params;
    }

    @Override
    public Class<AlipayWapChargeResponse> getResponseClass() {
        return AlipayWapChargeResponse.class;
    }

    @Override
    public Map<String, String> buildBizContentParams() {
        Map<String, String> params = new HashMap<>();
        params.put("out_trade_no", outTradeNo);
        params.put("product_code", "QUICK_WAP_WAY");
        params.put("total_amount", totalAmount);
        params.put("subject", subject);
        return params;
    }
}
