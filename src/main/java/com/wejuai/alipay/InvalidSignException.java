package com.wejuai.alipay;


/**
 * 签名无效异常
 *
 * @author YQ.Huang
 */
public class InvalidSignException extends RuntimeException {

    public InvalidSignException() {
        super("签名不正确");
    }

}
