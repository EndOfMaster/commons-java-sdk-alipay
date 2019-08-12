package com.wejuai.alipay.direct;

import com.wejuai.alipay.AlipayResponse;

import java.util.Collections;
import java.util.Map;

/**
 * @author YQ.Huang
 */
public class AlipayDirectChargeResponse extends AlipayResponse {

    private String html;

    public AlipayDirectChargeResponse(String html) {
        this.html = html;
    }

    public Map<String, String> buildCredentials() {
        return Collections.singletonMap("html", html);
    }

}
