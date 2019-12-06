package com.wejuai.alipay;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.wejuai.alipay.Constants.CHARSET;
import static com.wejuai.alipay.Constants.OBJECT_MAPPER;
import static com.wejuai.alipay.Constants.SIGN_TYPE;
import static com.wejuai.alipay.Constants.TIMESTAMP_DATE_FORMAT;
import static com.wejuai.alipay.Constants.VERSION;

/**
 * @author ZM.Wang
 */
public abstract class AlipayRequest<T extends AlipayResponse> {

    public abstract Class<T> getResponseClass();

    public Map<String, String> buildRequestParams() throws JsonProcessingException {
        Map<String, String> params = new HashMap<>();
        params.put("charset", CHARSET);
        params.put("sign_type", SIGN_TYPE);
        params.put("timestamp", TIMESTAMP_DATE_FORMAT.format(new Date()));
        params.put("version", VERSION);
        Map<String, String> bizContentParams = buildBizContentParams();
        if (bizContentParams != null) {
            String bizContent = OBJECT_MAPPER.writeValueAsString(bizContentParams);
            params.put("biz_content", bizContent);
        }
        return params;
    }

    public abstract Map<String, String> buildBizContentParams();

}
