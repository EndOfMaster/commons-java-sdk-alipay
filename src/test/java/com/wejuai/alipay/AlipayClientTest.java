package com.wejuai.alipay;

import com.wejuai.alipay.direct.AlipayDirectChargeRequest;
import com.wejuai.alipay.direct.AlipayDirectChargeResponse;
import com.wejuai.alipay.query.AlipayChargeQueryRequest;
import com.wejuai.alipay.query.AlipayChargeQueryResponse;
import com.wejuai.alipay.transfer.AlipayBalanceQueryRequest;
import com.wejuai.alipay.transfer.AlipayBalanceQueryResponse;
import com.wejuai.alipay.transfer.AlipayQueryTransferRequest;
import com.wejuai.alipay.transfer.AlipayQueryTransferResponse;
import com.wejuai.alipay.transfer.AlipayTransferRequest;
import com.wejuai.alipay.transfer.AlipayTransferResponse;
import com.wejuai.alipay.wap.AlipayWapChargeRequest;
import com.wejuai.alipay.wap.AlipayWapChargeResponse;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.junit.Test;

import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Collection;

/**
 * @author ZM.Wang
 */
public class AlipayClientTest {

    private static BouncyCastleProvider provider;

    static {
        provider = new BouncyCastleProvider();
        Security.addProvider(provider);
    }

    private AlipayClient alipayClient;

    public AlipayClientTest() {
        this.alipayClient = new AlipayClient("2018090861261835", "*****");
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

    @Test
    public void queryBalance() {
        AlipayBalanceQueryRequest request = new AlipayBalanceQueryRequest("2088231568196061");
        AlipayBalanceQueryResponse response = alipayClient.execute(request);
        if (response.isSuccessful()) {
            System.err.println(response.getAmount());
        } else {
            System.err.println(response.getErrorMessage());
        }
    }

    @Test
    public void transfer() {
        String chargeId = RandomStringUtils.randomAlphanumeric(32);
        AlipayTransferRequest request = new AlipayTransferRequest(chargeId, "1.00", "测试转账", "****",
                "*****", "TRANS_ACCOUNT_NO_PWD", "*********");
        AlipayTransferResponse response = alipayClient.execute(request);
        if (response.isSuccessful()) {
            System.err.println(response.getOrderId());
        } else {
            System.err.println(response.getErrorMessage());
        }
    }

    @Test
    public void queryTransfer() {
        AlipayQueryTransferRequest request = new AlipayQueryTransferRequest("80uCuSOS9oKgZC3Uv1yXGWLHyLNo3J9U",
                "TRANS_ACCOUNT_NO_PWD", "DIRECT_TRANSFER", "*******");
        AlipayQueryTransferResponse response = alipayClient.execute(request);
        if (response.isSuccessful()) {
            System.err.println(response.getAmount());
            System.err.println(response.getHandleFee());
            System.err.println(response.getStatus());
            System.err.println(response.getReason());
        } else {
            System.err.println(response.getErrorMessage());
        }
    }

    public static void main(String[] args) {
        InputStream inputStream = AlipayClientTest.class.getResourceAsStream("/你的应用证书文件");
        String myAppCertSn = getCertSn(inputStream);
        System.err.println(myAppCertSn);
        InputStream alipay = AlipayClientTest.class.getResourceAsStream("/阿里云的根证书文件");
        String rootCertSn = getRootCertSN(alipay);
        System.err.println(rootCertSn);
    }

    /**
     * 获取根证书序列号
     */
    public static String getRootCertSN(InputStream inputStream) {
        try {
            String rootCertSN = null;
            CertificateFactory factory = CertificateFactory.getInstance("X.509", provider);
            Collection<? extends Certificate> certificates = factory.generateCertificates(inputStream);
            X509Certificate[] x509Certificates = (X509Certificate[]) certificates.toArray(new X509Certificate[0]);
            MessageDigest md = MessageDigest.getInstance("MD5");
            for (X509Certificate c : x509Certificates) {
                if (c.getSigAlgOID().startsWith("1.2.840.113549.1.1")) {
                    md.update((c.getIssuerX500Principal().getName() + c.getSerialNumber()).getBytes());
                    String certSN = new BigInteger(1, md.digest()).toString(16);
                    //BigInteger会把0省略掉，需补全至32位
                    certSN = fillMD5(certSN);
                    if (StringUtils.isEmpty(rootCertSN)) {
                        rootCertSN = certSN;
                    } else {
                        rootCertSN = rootCertSN + "_" + certSN;
                    }
                }
            }
            return rootCertSN;
        } catch (Exception e) {
            throw new RuntimeException("根证书提取失败", e);
        }
    }

    private static String getCertSn(InputStream inputStream) {
        try {
            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            X509Certificate cert = (X509Certificate) cf.generateCertificate(inputStream);
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update((cert.getIssuerX500Principal().getName() + cert.getSerialNumber()).getBytes());
            String certSN = new BigInteger(1, md.digest()).toString(16);
            //BigInteger会把0省略掉，需补全至32位
            certSN = fillMD5(certSN);
            return certSN;
        } catch (CertificateException | NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    private static String fillMD5(String md5) {
        return md5.length() == 32 ? md5 : fillMD5("0" + md5);
    }

}
