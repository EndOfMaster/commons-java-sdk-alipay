package com.wejuai.alipay.transfer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.wejuai.alipay.AlipayRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ZM.Wang
 */
public class AlipayBalanceQueryRequest extends AlipayRequest<AlipayBalanceQueryResponse> {

    private final String merchantId;

    public AlipayBalanceQueryRequest(String merchantId) {
        this.merchantId = merchantId;
    }

    @Override
    public Map<String, String> buildRequestParams() throws JsonProcessingException {
        Map<String, String> params = super.buildRequestParams();
        params.put("method", "alipay.fund.account.query");
        return params;
    }

    @Override
    public Class<AlipayBalanceQueryResponse> getResponseClass() {
        return AlipayBalanceQueryResponse.class;
    }

    @Override
    public Map<String, String> buildBizContentParams() {
        Map<String, String> params = new HashMap<>(2);
        params.put("alipay_user_id", merchantId);
        params.put("account_type", "ACCTRANS_ACCOUNT");
        return params;
    }
}
