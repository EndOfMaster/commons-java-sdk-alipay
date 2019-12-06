package com.wejuai.alipay.transfer;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wejuai.alipay.AlipayResponse;

/**
 * @author ZM.Wang
 */
public class AlipayTransferResponse extends AlipayResponse<AlipayTransferResponse.Params> {

    @JsonProperty("alipay_fund_trans_uni_transfer_response")
    private Params params;

    @Override
    public Params getParams() {
        return params;
    }

    public static class Params extends AlipayResponse.Params {

        @JsonProperty("order_id")
        private String orderId;

        @JsonProperty("pay_fund_order_id")
        private String payOrderId;

        private String status;

        public String getOrderId() {
            return orderId;
        }

        public String getPayOrderId() {
            return payOrderId;
        }

        public String getStatus() {
            return status;
        }
    }

    public String getOrderId() {
        return params.getPayOrderId();
    }

    public String getStatus() {
        return params.getStatus();
    }

}
