package com.wejuai.alipay;

import com.wejuai.alipay.query.AlipayChargeQueryRequest;
import com.wejuai.alipay.query.AlipayChargeQueryResponse;
import com.wejuai.alipay.direct.AlipayDirectChargeRequest;
import com.wejuai.alipay.direct.AlipayDirectChargeResponse;
import com.wejuai.alipay.wap.AlipayWapChargeRequest;
import com.wejuai.alipay.wap.AlipayWapChargeResponse;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;

/**
 * @author ZM.Wang
 */
public class AlipayClientTest {

    private AlipayClient alipayClient;

    public AlipayClientTest() {
        this.alipayClient = new AlipayClient("2018090861261835", "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQC+TOCCyEqTZstjG4EEQfF6mJ6z2ET27ya8OC+qy7Y03DTcU9qL0u7VezOjlbgLBPK1WAWUrbSRArP+vG+1vv+FjwJZ0DYx/7Khj35WFF4RBMqDyNquIsJL6dU4O/KXl/jxmG8KiK3GZ5ji51R+WT4O28niIgQRvgwTDKt6GbF3y4JmK57McfWAuxUgfBI0JVGu+QrXEu/JOLhCP1vqUBtxSPGUZBPoA3PVfNpx/z27oJ/GF6l5jwDypaElOmE5lMw42AEQUAKGswcV9bfd07KSFwKzKhPdOkcgjL7yomelbHVkiOQaXMTzzcP7zVKH1cmoOXdnMttjgzimBlSamlT7AgMBAAECggEABKJipUyzZDuQoG0/CxeABfhJhR+zLkBMtTC5GHO1kDv8nZD9NYKBhnllnIo83SfVsUzfdet1BkMGlyGHpulr15jP+df0Oet8MisIbJg6Yje3ptXxXWRDTw32v1PWaJKu4VNRAPSrJrXHJ19y01DhDgOUOwmRB82VBbG9S0/s3nnvhnSyW0GfdxBRLDJ9rLp6tkbItQd9tscVs0m1qcXFxi0mZt9DOqck+rXY0Q/t4ny6fbr+Plesz5ftqVZwvcutO/kyD2xXT4A1NOfEsMSp+2cjYv8s1Gh5TD0ibByETati5+kXyoy6H+VIsuZVNQW96lDm/foAh9eD6+O3pipAAQKBgQDg3BpVDnljtqK7QmFCK/HEyKq408NwHqLbFHFqSZsJISfUksy9rLCJJw6Y/K8OJIERRH2uAXytdh86FSf/oNhGfzlkdAghY2ELuuQkq+nHUHeGqDJh+v9lmYCC/wpc43NsoVbbvYzIseiCi1ToHbJyH+olIPO1kSWBjq8wZTYs+wKBgQDYp4u0QYDLbHS0QFL5OEwFZmMDxGbMS3/5B2cieQS0AWJ208Ux/qHknAMvBtZzNnaoauhDHUw0zoggSIRA1kNqMTW0QCVvmXPflpTZCzNDJTZ0pIqmZtU74R+ycs06hv63ZDz83vmU2/mxDIkUiwdIH+sFdjUfHCSmgUNTYqP4AQKBgHHwFYez5d6Y1Kx+Xjnheoh0Bc3Rv944J91K5o0s7toZas1T9VrG/k0A9Wyf3TQVWzEcu3JIXpymlfwgbDwjjHNsU/CKuvPwH2SKNsA+PB86rtYUtDLhtDgPKAc4pE+dEQeiWRjPW172hxJe948GPT2G3mYh5FvESHT1j9+96U2xAoGATl48oJVZGzw98r2ZxeBhT0q74i726EsfHVtaKRQRaXNexJDRscXjlagBVs9oXwCCZ2VUYoKEgE183ACfJjza4dyvCmZxpC6jxWrYqLHudwlM62BhbtOfqitCpe/04IjYNkoh7W5SZVA0v6BDLZUkqygwgSdDC9/IOhpj2hPCMAECgYEAjjkehsb/61gF0jpSI+0GIwsBSx6YPwKJxrkb8yiNnodLx55Xr1PXIDRleJZ8qFwV8Y8dAHl/hOF2SJTCxucAz5H4tol64nmWUl1bAVMXVChTAb2H+oMOrynqvihrTEghT73GgHKcaRNE+3UEOY2ovcdqSE8AVyAkFeoYSIIOLCY=");
    }

    @Test
    public void chargeTest() {
        AlipayDirectChargeRequest request = new AlipayDirectChargeRequest(RandomStringUtils.randomAlphanumeric(32),
                "测试商品", "1.00", "", "");
        AlipayDirectChargeResponse response = alipayClient.execute(request);
        System.err.println(response.buildCredentials());
    }

    @Test
    public void wapChargeTest() {
        String outTradeOn = RandomStringUtils.randomAlphanumeric(32);
        System.err.println(outTradeOn);
        AlipayWapChargeRequest request = new AlipayWapChargeRequest(outTradeOn, "测试商品", "1.00", "", "");
        AlipayWapChargeResponse response = alipayClient.execute(request);
        System.err.println(response.buildCredentials());
    }

    @Test
    public void questTest() {
        AlipayChargeQueryRequest request = new AlipayChargeQueryRequest("", "", "n1cRIgQRpIcKrz3LlJcRzCWYAlfqn8vh");
        AlipayChargeQueryResponse response = alipayClient.execute(request);
        System.err.println(response.getParams().tradeNo);
    }
}
