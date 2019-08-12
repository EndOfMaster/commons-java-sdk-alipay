package com.wejuai.alipay;

/**
 * @author ZM.Wang
 */
public class AlipayException extends RuntimeException {

    public AlipayException(String msg) {
        super(msg);
    }

    public AlipayException(Throwable cause) {
        super(cause);
    }
}
