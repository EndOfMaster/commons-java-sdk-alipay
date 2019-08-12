package com.wejuai.alipay;

import com.endofmaster.commons.util.validate.InvalidParamException;
import org.apache.commons.lang3.StringUtils;

/**
 * @author YQ.Huang
 */
public abstract class ValidationUtils {

    /**
     * 校验参数，失败时抛出InvalidParamException
     *
     * @param param    参数名
     * @param expected 期望值
     * @param actual   实际值
     */
    public static void validateParam(String param, String expected, String actual) {
        if (!StringUtils.equals(expected, actual))
            throw new InvalidParamException(param, expected, actual);
    }

    /**
     * 校验参数，失败时抛出InvalidParamException
     *
     * @param param    参数名
     * @param expected 期望值
     * @param actual   实际值
     */
    public static void validateParam(String param, int expected, int actual) {
        if (expected != actual)
            throw new InvalidParamException(param, expected, actual);
    }

    /**
     * 校验参数，失败时抛出InvalidParamException
     *
     * @param param    参数名
     * @param expected 期望值
     * @param actual   实际值
     */
    public static void validateParam(String param, double expected, double actual) {
        if (expected != actual)
            throw new InvalidParamException(param, expected, actual);
    }
}
