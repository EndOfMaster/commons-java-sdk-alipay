package com.wejuai.alipay;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author ZM.Wang
 */
public abstract class AlipayResponse<T extends AlipayResponse.Params> {

    private String sign;

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public abstract T getParams();

    public boolean isSuccessful() {
        return getParams().isSuccessful();
    }

    public String getErrorMessage() {
        return getParams().getErrorMessage();
    }

    public static abstract class Params {

        @JsonProperty("code")
        public String code;

        @JsonProperty("msg")
        public String msg;

        @JsonProperty("sub_code")
        public String subCode;

        @JsonProperty("sub_msg")
        public String subMsg;

        public boolean isSuccessful() {
            return "10000".equals(code);
        }

        public String getErrorMessage() {
            return code + ":" + msg + ":" + subCode + ":" + subMsg;
        }
    }
}
