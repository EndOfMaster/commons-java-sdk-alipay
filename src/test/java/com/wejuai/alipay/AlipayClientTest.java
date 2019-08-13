package com.wejuai.alipay;

import com.wejuai.alipay.direct.AlipayDirectChargeQueryRequest;
import com.wejuai.alipay.direct.AlipayDirectChargeQueryResponse;
import com.wejuai.alipay.direct.AlipayDirectChargeRequest;
import com.wejuai.alipay.direct.AlipayDirectChargeResponse;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;

/**
 * @author ZM.Wang
 */
public class AlipayClientTest {

    private AlipayClient alipayClient;

    public AlipayClientTest() {
        this.alipayClient = new AlipayClient("2018090861261835", "***");
    }

    @Test
    public void chargeTest() {
        AlipayDirectChargeRequest request = new AlipayDirectChargeRequest("alipay.trade.page.pay", RandomStringUtils.randomAlphanumeric(32),
                "测试商品", "1.00", "", "");
        AlipayDirectChargeResponse response = alipayClient.execute(request);
        System.err.println(response.buildCredentials());
    }

    @Test
    public void questTest() {
        AlipayDirectChargeQueryRequest request = new AlipayDirectChargeQueryRequest("alipay.trade.query", "", "", "aasdasdadadadasdas");
        AlipayDirectChargeQueryResponse response = alipayClient.execute(request);
        System.err.println(response.getParams().tradeNo);
    }
}
