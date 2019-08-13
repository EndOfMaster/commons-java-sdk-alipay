package com.wejuai.alipay.direct;

import com.wejuai.alipay.AlipayRequest;

import java.util.Collections;
import java.util.Map;

/**
 * @author ZM.Wang
 */
public class AlipayDirectChargeQueryRequest extends AlipayRequest<AlipayDirectChargeQueryResponse> {

    private final String outTradeNo;

    public AlipayDirectChargeQueryRequest(String method, String returnUrl, String notifyUrl, String outTradeNo) {
        super(method, returnUrl, notifyUrl);
        this.outTradeNo = outTradeNo;
    }

    @Override
    public Class<AlipayDirectChargeQueryResponse> getResponseClass() {
        return AlipayDirectChargeQueryResponse.class;
    }

    @Override
    public Map<String, String> buildBizContentParams() {
        return Collections.singletonMap("out_trade_no", outTradeNo);
    }
}
