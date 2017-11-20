package com.r2d2.pecan.common.exception;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

/**
 * 异常基类
 * <p>
 * 1.提供异常码和异常源构造异常
 * 2.除了错误码本身描述的提示信息外的补充信息
 * 3、提供异常码构造异常
 * </p>
 */
public abstract class BaseException extends RuntimeException {

    /**
     * 错误码信息
     */
    @Setter
    @Getter
    private ErrorCode errorCode;

    /**
     * 除了错误码本身描述的提示信息外，额外补充的信息
     */
    @Setter
    @Getter
    private String extraMsg;

    /**
     * 提供异常码和异常源构造异常
     *
     * @param errorCode 异常码
     * @param cause     异常原因
     * @throws NullPointerException <code>errorCode</code>不能为空，否则抛出异常
     * @see ErrorCode
     */
    public BaseException(ErrorCode errorCode, Throwable cause) {
        this(errorCode, null, cause);
    }

    /**
     * 设置除了错误码本身描述的提示信息外，额外补充的信息
     * 当错误码本身只是比较模糊的提示下，可以额外补充其他信息
     *
     * @param extraMsg 额外补充的信息
     */
    public BaseException(ErrorCode errorCode, String extraMsg, Throwable cause) {
        super(getMessage(errorCode, extraMsg), cause);
        this.errorCode = errorCode;
        this.extraMsg = extraMsg;
    }

    /**
     * 提供异常码构造异常
     *
     * @param errorCode 异常码异常码
     * @throws NullPointerException <code>errorCode</code>不能为空，否则抛出异常
     * @see ErrorCode
     */
    public BaseException(ErrorCode errorCode) {
        this(errorCode, null, null);
    }

    /**
     * 设置除了错误码本身描述的提示信息外，额外补充的信息
     * 当错误码本身只是比较模糊的提示下，可以额外补充其他信息
     *
     * @param extraMsg 额外补充的信息
     */
    public BaseException(ErrorCode errorCode, String extraMsg) {
        this(errorCode, extraMsg, null);
    }

    /**
     * 返回异常信息
     */
    private static String getMessage(ErrorCode errorCode, String extraMessage) {
        String msg = "errCode:" + errorCode.getCode() + ", errMsg :" + errorCode.getDesc();
        if (StringUtils.isEmpty(extraMessage)) {
            return msg;
        }
        return msg + ", extMsg :" + extraMessage;
    }
}