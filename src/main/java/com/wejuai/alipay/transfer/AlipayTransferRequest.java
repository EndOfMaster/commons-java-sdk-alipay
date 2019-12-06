package com.wejuai.alipay.transfer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.wejuai.alipay.AlipayException;
import com.wejuai.alipay.AlipayRequest;

import java.util.HashMap;
import java.util.Map;

import static com.wejuai.alipay.AlipayClient.ALIPAY_ROOT_CERT_SN;
import static com.wejuai.alipay.Constants.OBJECT_MAPPER;

/**
 * @author ZM.Wang
 */
public class AlipayTransferRequest extends AlipayRequest<AlipayTransferResponse> {

    private final String chargeId;
    private final String amount;
    private final String title;
    private final String userAccounts;
    private final String userName;
    private final String productCode;
    private final String appCertSn;

    public AlipayTransferRequest(String chargeId, String amount, String title, String userAccounts, String userName, String productCode, String appCertSn) {
        this.chargeId = chargeId;
        this.amount = amount;
        this.title = title;
        this.userAccounts = userAccounts;
        this.userName = userName;
        this.productCode = productCode;
        this.appCertSn = appCertSn;
    }

    @Override
    public Class<AlipayTransferResponse> getResponseClass() {
        return AlipayTransferResponse.class;
    }

    @Override
    public Map<String, String> buildBizContentParams() {
        Map<String, String> params = new HashMap<>();
        params.put("out_biz_no", chargeId);
        params.put("trans_amount", amount);
        params.put("product_code", productCode);
        params.put("biz_scene", "DIRECT_TRANSFER");
        params.put("order_title", title);
        Map<String, String> payeeInfo = new HashMap<>(3);
        payeeInfo.put("identity", userAccounts);
        payeeInfo.put("identity_type", "ALIPAY_LOGON_ID");
        payeeInfo.put("name", userName);
        try {
            params.put("payee_info", OBJECT_MAPPER.writeValueAsString(payeeInfo));
        } catch (JsonProcessingException e) {
            throw new AlipayException("付款人信息转json失败");
        }
        return params;
    }

    @Override
    public Map<String, String> buildRequestParams() throws JsonProcessingException {
        Map<String, String> params = super.buildRequestParams();
        params.put("method", "alipay.fund.trans.uni.transfer");
        params.put("alipay_root_cert_sn", ALIPAY_ROOT_CERT_SN);
        params.put("app_cert_sn", appCertSn);
        return params;
    }
}
