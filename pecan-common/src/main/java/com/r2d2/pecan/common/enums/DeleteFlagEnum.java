package com.r2d2.pecan.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * 是否可用
 */
@Getter
@ToString
@AllArgsConstructor
public enum DeleteFlagEnum {

    /**
     * 可用
     */
    USABLE("USABLE", "可用"),

    /**
     * 不可用
     */
    UN_USABLE("UN_USABLE", "不可用");

    /**
     * key
     */
    private String code;

    /**
     * 描述
     */
    private String desc;
}
