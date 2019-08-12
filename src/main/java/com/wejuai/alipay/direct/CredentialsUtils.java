package com.wejuai.alipay.direct;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

/**
 * @author YQ.Huang
 */
public abstract class CredentialsUtils {

    public static String buildQueryString(Map<String, String> params, String charset) throws UnsupportedEncodingException {
        StringBuilder url = new StringBuilder();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (entry.getKey() != null && entry.getValue() != null) {
                url.append(entry.getKey());
                url.append("=");
                url.append(URLEncoder.encode(entry.getValue(), charset));
                url.append("&");
            }
        }
        if (url.length() > 0)
            url.deleteCharAt(url.length() - 1);
        return url.toString();
    }

    public static String buildUrl(String rootUrl, Map<String, String> params, String charset) throws UnsupportedEncodingException {
        return rootUrl + "?" + buildQueryString(params, charset);
    }

    public static String buildForm(String actionUrl, Map<String, String> params) {
        return "<form name=\"punchout_form\" method=\"post\" action=\"" + actionUrl + "\">\n" +
                buildHiddenFields(params) +
                "<input type=\"submit\" value=\"立即支付\" style=\"display:none\" >\n" +
                "</form>\n" +
                "<script>document.forms[0].submit();</script>";
    }

    private static String buildHiddenFields(Map<String, String> params) {
        if (params != null && !params.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (Map.Entry<String, String> entry : params.entrySet()) {
                if (entry.getKey() != null && entry.getValue() != null) {
                    sb.append(buildHiddenField(entry.getKey(), entry.getValue()));
                }
            }
            return sb.toString();
        } else {
            return "";
        }
    }

    private static String buildHiddenField(String key, String value) {
        StringBuilder sb = new StringBuilder();
        sb.append("<input type=\"hidden\" name=\"");
        sb.append(key);
        sb.append("\" value=\"");
        String a = value.replace("\"", "&quot;");
        sb.append(a).append("\">\n");
        return sb.toString();
    }

}
