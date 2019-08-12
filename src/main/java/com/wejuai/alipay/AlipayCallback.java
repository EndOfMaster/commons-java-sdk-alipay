package com.wejuai.alipay;


import com.endofmaster.commons.util.HttpRequestUtils;
import com.endofmaster.commons.util.sign.PresignUtils;
import com.endofmaster.commons.util.sign.RsaSignUtils;
import com.endofmaster.commons.util.validate.InvalidParamException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.security.SignatureException;
import java.util.HashMap;
import java.util.Map;

import static com.wejuai.alipay.Constants.CHARSET;


/**
 * @author YQ.Huang
 */
public abstract class AlipayCallback {

    protected Map<String, String> params;

    protected AlipayCallback(HttpServletRequest request) {
        try {
            this.params = HttpRequestUtils.getRequestParams(request, CHARSET);
            validateSign(request);
        } catch (UnsupportedEncodingException | SignatureException e) {
            throw new AlipayException(e);
        }
    }

    public Map<String, String> getParams() {
        return params;
    }

    public Map<String, String> buildResultParams() {
        return new HashMap<>();
    }

    public void ack(HttpServletResponse response) {
        AckUtils.ack(response, "success");
    }

    private void validateSign(HttpServletRequest request) throws SignatureException {
        // NOTE: 签名必需提前获取，否则会有编码问题
        String sign = request.getParameter("sign");
        String signType = request.getParameter("sign_type");
        params.remove("sign");
        params.remove("sign_type");
        if (!"RSA".equals(signType)) {
            throw new InvalidParamException("sign_type", "RSA", signType);
        }
        String preSignString = PresignUtils.createLinkString(params, true);
        if (!RsaSignUtils.sha1Verify(preSignString, sign, getPublicKey(), CHARSET)) {
            throw new InvalidSignException();
        }
    }

    protected abstract String getPublicKey();

}
