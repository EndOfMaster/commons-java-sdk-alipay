package com.wejuai.alipay.transfer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.wejuai.alipay.AlipayRequest;

import java.util.HashMap;
import java.util.Map;

import static com.wejuai.alipay.AlipayClient.ALIPAY_ROOT_CERT_SN;

/**
 * @author ZM.Wang
 */
public class AlipayQueryTransferRequest extends AlipayRequest<AlipayQueryTransferResponse> {

    private final String chargeId;
    private final String productCode;
    private final String scene;
    private final String appCertSn;

    public AlipayQueryTransferRequest(String chargeId, String productCode, String scene, String appCertSn) {
        this.chargeId = chargeId;
        this.productCode = productCode;
        this.scene = scene;
        this.appCertSn = appCertSn;
    }

    @Override
    public Map<String, String> buildRequestParams() throws JsonProcessingException {
        Map<String, String> params = super.buildRequestParams();
        params.put("method", "alipay.fund.trans.common.query");
        params.put("alipay_root_cert_sn", ALIPAY_ROOT_CERT_SN);
        params.put("app_cert_sn", appCertSn);
        return params;
    }

    @Override
    public Class<AlipayQueryTransferResponse> getResponseClass() {
        return AlipayQueryTransferResponse.class;
    }

    @Override
    public Map<String, String> buildBizContentParams() {
        Map<String, String> params = new HashMap<>(3);
        params.put("out_biz_no", chargeId);
        params.put("product_code", productCode);
        params.put("biz_scene", scene);
        return params;
    }
}
