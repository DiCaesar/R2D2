package com.r2d2.pecan.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 异常公共枚举
 */
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public enum CommonErrorCode implements ErrorCode {

    /**
     * 参数校验不通过
     */
    PARAMETER_VALID_NOT_PASS("PARAMETER_VALID_NOT_PASS", "参数校验不通过"),

    /**
     * 不支持的取值
     */
    VALUE_NOT_SUPPORT("VALUE_NOT_SUPPORT", "不支持的取值"),

    /**
     * 参数为null（不含空字符串）是非法的
     */
    NULL_IS_ILLEGAL_PARAM("NULL_IS_ILLEGAL_PARAM", "参数为null是非法的"),

    /**
     * 执行数据库操作发生异常
     */
    DATA_BASE_ERROR("DATA_BASE_ERROR", "数据库操作异常"),

    /**
     * 系统内部异常
     */
    SYSTEM_INNER_ERROR("SYSTEM_INNER_ERROR", "系统内部异常"),

    /**
     * 未知错误
     */
    UNKNOWN_ERROR("UNKNOWN_ERROR", "未知错误"),

    JOB_DATA_MAP_IS_NULL("JOB_DATA_MAP_IS_NULL", "JOB数据MAP为空"),


    JOB_CONFIG_NOT_EXISTS("JOB_CONFIG_NOT_EXISTS", "JOB配置信息不存在"),


    ;


    /**
     * 异常码
     */
    private String code;
    /**
     * 异常描述
     */
    private String desc;
}
