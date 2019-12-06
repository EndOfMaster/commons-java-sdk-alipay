package com.wejuai.alipay.transfer;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wejuai.alipay.AlipayResponse;

/**
 * @author ZM.Wang
 */
public class AlipayQueryTransferResponse extends AlipayResponse<AlipayQueryTransferResponse.Params> {

    @JsonProperty("alipay_fund_trans_common_query_response")
    private Params params;

    @Override
    public Params getParams() {
        return params;
    }

    public static class Params extends AlipayResponse.Params {

        @JsonProperty("trans_amount")
        private String amount;

        private String status;

        @JsonProperty("order_fee")
        private String handleFee;

        @JsonProperty("fail_reason")
        private String reason;

        public String getAmount() {
            return amount;
        }

        public String getStatus() {
            return status;
        }

        public String getHandleFee() {
            return handleFee;
        }

        public String getReason() {
            return reason;
        }
    }

    public String getAmount() {
        return params.getAmount();
    }

    public String getStatus() {
        return params.getStatus();
    }

    public String getHandleFee() {
        return params.getHandleFee();
    }

    public String getReason() {
        return params.getReason();
    }
}
