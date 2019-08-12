package com.wejuai.alipay;


import com.endofmaster.commons.util.StreamUtils;
import com.endofmaster.commons.util.sign.PresignUtils;
import com.endofmaster.commons.util.sign.RsaSignUtils;
import com.wejuai.alipay.direct.AlipayDirectChargeRequest;
import com.wejuai.alipay.direct.AlipayDirectChargeResponse;
import com.wejuai.alipay.direct.CredentialsUtils;
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

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final String appId;
    private final String rsaPrivateKey;
    private final HttpClient httpClient;

    public AlipayClient(String appId, String rsaPrivateKey) {
        this.appId = appId;
        this.rsaPrivateKey = rsaPrivateKey;
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(3000).setConnectTimeout(3000).build();
        this.httpClient = HttpClientBuilder.create().setDefaultRequestConfig(requestConfig).build();
    }

    @SuppressWarnings("unchecked")
    public <T extends AlipayResponse> T execute(AlipayRequest<T> request) {
        try {
            Map<String, String> params = request.buildRequestParams();
            params.put("app_id", appId);
            sign(params);
            String url = CredentialsUtils.buildUrl(OPENAPI_GATEWAY_URL, params, CHARSET);
            if (request instanceof AlipayDirectChargeRequest) {
                return (T) new AlipayDirectChargeResponse(url);
            }
            HttpPost httpPost = new HttpPost(url);
            HttpResponse response = httpClient.execute(httpPost);
            String result = StreamUtils.copyToString(response.getEntity().getContent(), CHARSET_OBJ);
            logger.info("返回结果：" + result);
            return OBJECT_MAPPER.readValue(result, request.getResponseClass());
        } catch (IOException | SignatureException e) {
            throw new AlipayException(e);
        }
    }

//    public AlipayDirectChargeResponse execute(AlipayDirectChargeRequest request) {
//        try {
//            Map<String, String> requestParams = request.buildRequestParams();
//            sign(requestParams);
//            String url = CredentialsUtils.buildUrl(MAPI_GATEWAY_URL, requestParams, CHARSET);
//            String html = CredentialsUtils.buildForm(url, null);
//            return new AlipayDirectChargeResponse(html);
//        } catch (SignatureException | UnsupportedEncodingException | JsonProcessingException e) {
//            throw new AlipayException(e);
//        }
//    }

    private void sign(Map<String, String> params) throws SignatureException {
        String preSignString = PresignUtils.createLinkString(params, true);
        String sign = RsaSignUtils.sha256Sign(preSignString, rsaPrivateKey, CHARSET);
        params.put("sign", sign);
    }
}
