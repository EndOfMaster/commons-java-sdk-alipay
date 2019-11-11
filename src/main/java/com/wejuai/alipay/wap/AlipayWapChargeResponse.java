package com.wejuai.alipay.wap;

import com.wejuai.alipay.AlipayResponse;
import org.apache.commons.lang3.NotImplementedException;

import java.util.Collections;
import java.util.Map;

/**
 * @author ZM.Wang
 */
public class AlipayWapChargeResponse extends AlipayResponse {

    private String html;

    public AlipayWapChargeResponse(String html) {
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
