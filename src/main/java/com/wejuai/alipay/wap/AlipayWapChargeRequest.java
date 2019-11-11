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

    public AlipayWapChargeRequest(String outTradeNo, String subject, String totalAmount, String returnUrl, String notifyUrl) {
        super(returnUrl, notifyUrl);
        this.outTradeNo = outTradeNo;
        this.subject = subject;
        this.totalAmount = totalAmount;
    }

    @Override
    public Map<String, String> buildRequestParams() throws JsonProcessingException {
        Map<String, String> params = super.buildRequestParams();
        params.put("method", "alipay.trade.wap.pay");
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
