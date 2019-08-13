package com.wejuai.alipay.direct;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wejuai.alipay.AlipayException;
import com.wejuai.alipay.AlipayResponse;
import org.apache.commons.lang3.StringUtils;

/**
 * @author ZM.Wang
 */
public class AlipayDirectChargeQueryResponse extends AlipayResponse<AlipayDirectChargeQueryResponse.Params> {


    @JsonProperty("alipay_trade_query_response")
    private Params params;

    @Override
    public Params getParams() {
        return params;
    }

    public void setParams(Params params) {
        this.params = params;
    }

    /** 是否完成 */
    public boolean getChargeStatus() {
        if (!isSuccessful())
            throw new AlipayException(getErrorMessage());
        String status = params.tradeStatus;
        return StringUtils.equals("TRADE_SUCCESS", status);
    }

    public static class Params extends AlipayResponse.Params {

        @JsonProperty("out_trade_no")
        public String outTradeNo;

        @JsonProperty("trade_no")
        public String tradeNo;

        @JsonProperty("trade_status")
        public String tradeStatus;

        @JsonProperty("total_amount")
        public String totalAmount;

        @JsonProperty("send_pay_date")
        public String sendPayDate;
    }
}
