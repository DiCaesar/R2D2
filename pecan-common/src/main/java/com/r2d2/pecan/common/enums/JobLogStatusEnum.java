package com.r2d2.pecan.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 任务日志状态枚举类
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum JobLogStatusEnum {

    /**
     * 运行中
     */
    RUNNING("RUNNING", "运行中"),

    /**
     * 已完成
     */
    FINISHED("FINISHED", "已完成"),

    /**
     * 异常
     */
    EXCEPTION("EXCEPTION", "异常");

    /**
     * key
     */
    private String code;

    /**
     * 描述
     */
    private String desc;
}
