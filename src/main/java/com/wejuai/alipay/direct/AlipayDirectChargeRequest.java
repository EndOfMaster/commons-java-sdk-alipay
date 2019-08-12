package com.wejuai.alipay.direct;


import com.wejuai.alipay.AlipayRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ZM.Wang
 */
public class AlipayDirectChargeRequest extends AlipayRequest<AlipayDirectChargeResponse> {

    private final String outTradeNo;
    private final String subject;
    private final String totalAmount;

    public AlipayDirectChargeRequest(String method, String outTradeNo, String subject, String totalAmount, String returnUrl, String notifyUrl) {
        super(method, returnUrl, notifyUrl);
        this.outTradeNo = outTradeNo;
        this.subject = subject;
        this.totalAmount = totalAmount;
    }

    @Override
    public Class<AlipayDirectChargeResponse> getResponseClass() {
        return AlipayDirectChargeResponse.class;
    }

    @Override
    public Map<String, String> buildBizContentParams() {
        Map<String, String> params = new HashMap<>();
        params.put("out_trade_no", outTradeNo);
        params.put("product_code", "FAST_INSTANT_TRADE_PAY");
        params.put("total_amount", totalAmount);
        params.put("subject", subject);
        return params;
    }

}
