package com.wejuai.alipay;


import com.endofmaster.commons.util.StreamUtils;
import com.endofmaster.commons.util.sign.PresignUtils;
import com.endofmaster.commons.util.sign.RsaSignUtils;
import com.wejuai.alipay.direct.AlipayDirectChargeRequest;
import com.wejuai.alipay.direct.AlipayDirectChargeResponse;
import com.wejuai.alipay.direct.CredentialsUtils;
import com.wejuai.alipay.wap.AlipayWapChargeRequest;
import com.wejuai.alipay.wap.AlipayWapChargeResponse;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.security.SignatureException;
import java.util.Map;

import static com.wejuai.alipay.Constants.CHARSET;
import static com.wejuai.alipay.Constants.CHARSET_OBJ;
import static com.wejuai.alipay.Constants.OBJECT_MAPPER;
import static com.wejuai.alipay.Constants.OPENAPI_GATEWAY_URL;

/**
 * @author YQ.Huang
 */
public class AlipayClient {

    private final static Logger logger = LoggerFactory.getLogger(AlipayClient.class);

    private final String appId;
    private final String rsaPrivateKey;
    private final HttpClient httpClient;
    private final String appCertSn;

    public static final String ALIPAY_CERT_SN = "ffc5e726cb3739f201cdc150da20a830";//支付宝公钥证书序列号
    public static final String ALIPAY_ROOT_CERT_SN = "687b59193f3f462dd5336e5abf83c5d8_02941eef3187dddf3d3b83462e1dfcf6";//支付宝跟证书的序列号

    public AlipayClient(String appId, String rsaPrivateKey, String appCertSn) {
        this.appId = appId;
        this.rsaPrivateKey = rsaPrivateKey;
        this.appCertSn = appCertSn;
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(3000).setConnectTimeout(3000).build();
        this.httpClient = HttpClientBuilder.create().setDefaultRequestConfig(requestConfig).build();
    }

    @SuppressWarnings("unchecked")
    public <T extends AlipayResponse> T execute(AlipayRequest<T> request) {
        try {
            Map<String, String> params = request.buildRequestParams();
            params.put("alipay_root_cert_sn", ALIPAY_ROOT_CERT_SN);
            params.put("app_cert_sn", appCertSn);
            params.put("app_id", appId);
            sign(params);
            String url = CredentialsUtils.buildUrl(OPENAPI_GATEWAY_URL, params, CHARSET);
            if (request instanceof AlipayDirectChargeRequest) {
                return (T) new AlipayDirectChargeResponse(url);
            }
            if (request instanceof AlipayWapChargeRequest) {
                return (T) new AlipayWapChargeResponse(url);
            }
            HttpPost httpPost = new HttpPost(url);
            HttpResponse response = httpClient.execute(httpPost);
            String result = StreamUtils.copyToString(response.getEntity().getContent(), CHARSET_OBJ);
            logger.debug("返回结果：" + result);
            return OBJECT_MAPPER.readValue(result, request.getResponseClass());
        } catch (IOException | SignatureException e) {
            throw new AlipayException(e);
        }
    }

    private void sign(Map<String, String> params) throws SignatureException {
        String preSignString = PresignUtils.createLinkString(params, true);
        String sign = RsaSignUtils.sha256Sign(preSignString, rsaPrivateKey, CHARSET);
        params.put("sign", sign);
    }

}
