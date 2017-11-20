package com.r2d2.pecan.common.exception;


/**
 * 异常
 *
 */
public class ServiceException extends BaseException {

    /**
     * 提供异常码构造异常
     *
     * @param errorCode 异常错误码
     * @param cause     异常
     */
    public ServiceException(ErrorCode errorCode, Throwable cause) {
        this(errorCode, null, cause);
    }

    /**
     * 提供异常码构造异常
     *
     * @param errorCode 异常错误码
     * @param cause     异常
     * @param extraMsg  错误信息
     */
    public ServiceException(ErrorCode errorCode, String extraMsg, Throwable cause) {
        super(errorCode, extraMsg, cause);
    }

    /**
     * 提供异常码构造异常
     *
     * @param errorCode 异常错误码
     */
    public ServiceException(ErrorCode errorCode) {
        this(errorCode, null, null);
    }

    /**
     * 提供异常码构造异常
     *
     * @param errorCode 异常错误码
     * @param extraMsg  错误信息
     */
    public ServiceException(ErrorCode errorCode, String extraMsg) {
        this(errorCode, extraMsg, null);
    }

}
