package com.wejuai.alipay.query;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.wejuai.alipay.AlipayRequest;

import java.util.Collections;
import java.util.Map;

/**
 * @author ZM.Wang
 */
public class AlipayChargeQueryRequest extends AlipayRequest<AlipayChargeQueryResponse> {

    private final String outTradeNo;

    public AlipayChargeQueryRequest(String returnUrl, String notifyUrl, String outTradeNo) {
        super(returnUrl, notifyUrl);
        this.outTradeNo = outTradeNo;
    }

    @Override
    public Map<String, String> buildRequestParams() throws JsonProcessingException {
        Map<String, String> params = super.buildRequestParams();
        params.put("method", "alipay.trade.query");
        return params;
    }

    @Override
    public Class<AlipayChargeQueryResponse> getResponseClass() {
        return AlipayChargeQueryResponse.class;
    }

    @Override
    public Map<String, String> buildBizContentParams() {
        return Collections.singletonMap("out_trade_no", outTradeNo);
    }
}
