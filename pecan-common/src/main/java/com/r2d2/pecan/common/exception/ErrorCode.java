package com.r2d2.pecan.common.exception;


/**
 * 错误码接口
 * User: LZQ Date: 2015/09/04 ProjectName: settleCenter Version: 1.0
 */
public interface ErrorCode {

    /**
     * 错误码
     */
    String getCode();

    /**
     * 错误描述
     */
    String getDesc();
}
