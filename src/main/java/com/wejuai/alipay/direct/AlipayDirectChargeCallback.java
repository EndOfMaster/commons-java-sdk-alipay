package com.wejuai.alipay.direct;

import com.wejuai.alipay.AlipayCallback;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.Map;

import static com.endofmaster.commons.util.validate.ParamUtils.findParam;
import static com.wejuai.alipay.Constants.OPENAPI_PUBLIC_KEY;
import static com.wejuai.alipay.ValidationUtils.validateParam;

/**
 * @author YQ.Huang
 */
public class AlipayDirectChargeCallback extends AlipayCallback {

    public AlipayDirectChargeCallback(HttpServletRequest request) {
        super(request);
    }

    public void validate(String outTradeNo, long amount) {
        validateTradeStatus();
        validateOutTradeNo(outTradeNo);
        validateTotalFee(amount);
    }

    @Override
    public Map<String, String> buildResultParams() {
        return Collections.singletonMap("trade_no", findParam(params, "trade_no"));
    }

    public String getTradeNo() {
        return buildResultParams().get("trade_no");
    }

    @Override
    protected String getPublicKey() {
        return OPENAPI_PUBLIC_KEY;
    }

    private void validateTradeStatus() {
        String param = "trade_status";
        String expected = "TRADE_SUCCESS";
        String actual = findParam(params, param);
        validateParam(param, expected, actual);
    }

    private void validateOutTradeNo(String outTradeNo) {
        String param = "out_trade_no";
        String actual = findParam(params, param);
        validateParam(param, outTradeNo, actual);
    }

    private void validateTotalFee(long amount) {
        String param = "total_amount";
        double expected = amount / 100.0;
        String actual = findParam(params, param);
        validateParam(param, expected, Double.valueOf(actual));
    }

}
