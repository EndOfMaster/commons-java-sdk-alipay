package com.wejuai.alipay.transfer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.wejuai.alipay.AlipayRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ZM.Wang
 */
public class AlipayQueryTransferRequest extends AlipayRequest<AlipayQueryTransferResponse> {

    private final String chargeId;
    private final String productCode;
    private final String scene;

    public AlipayQueryTransferRequest(String chargeId, String productCode, String scene) {
        this.chargeId = chargeId;
        this.productCode = productCode;
        this.scene = scene;
    }

    @Override
    public Map<String, String> buildRequestParams() throws JsonProcessingException {
        Map<String, String> params = super.buildRequestParams();
        params.put("method", "alipay.fund.trans.common.query");
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
