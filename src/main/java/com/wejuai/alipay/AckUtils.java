package com.wejuai.alipay;


import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author YQ.Huang
 */
public abstract class AckUtils {

    public static void ack(HttpServletResponse response, String message) {
        try {
            PrintWriter writer = response.getWriter();
            writer.println(message);
            writer.flush();
        } catch (IOException e) {
            throw new AlipayException(e);
        }
    }
}
