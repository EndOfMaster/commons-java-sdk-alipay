package com.wejuai.alipay.direct;

import com.wejuai.alipay.AlipayResponse;
import org.apache.commons.lang3.NotImplementedException;

import java.util.Collections;
import java.util.Map;

/**
 * @author YQ.Huang
 */
@SuppressWarnings({"rawtypes"})
public class AlipayDirectChargeResponse extends AlipayResponse {

    private String html;

    public AlipayDirectChargeResponse(String html) {
        this.html = html;
    }

    public Map<String, String> buildCredentials() {
        return Collections.singletonMap("html", html);
    }

    @Override
    public Params getParams() {
        throw new NotImplementedException("不必实现");
    }
}
