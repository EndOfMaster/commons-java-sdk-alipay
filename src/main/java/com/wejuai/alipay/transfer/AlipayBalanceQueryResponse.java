package com.wejuai.alipay.transfer;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wejuai.alipay.AlipayResponse;

/**
 * @author ZM.Wang
 */
public class AlipayBalanceQueryResponse extends AlipayResponse<AlipayBalanceQueryResponse.Params> {

    @JsonProperty("alipay_fund_account_query_response")
    private Params params;

    @Override
    public Params getParams() {
        return params;
    }

    public static class Params extends AlipayResponse.Params {

        @JsonProperty("available_amount")
        private String amount;

        public String getAmount() {
            return amount;
        }
    }

    public long getAmount() {
        return (long) (Double.parseDouble(params.getAmount()) * 100);
    }
}
