package com.r2d2.pecan.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Created by Administrator on 2017/11/4.
 */
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public enum StatusEnum {

    /**
     * 处理成功
     */
    Success("S", "成功"),

    /**
     * 处理失败
     */
    Failure("F", "失败"),

    /**
     * 初始化
     */
    Initial("I", "初始化"),

    /**
     * 待处理
     */
    Wait("W", "待处理"),

    /**
     * 处理中
     */
    Processing("P", "处理中");


    /**
     * 编码
     */
    private String code;

    /**
     * 描述
     */
    private String desc;


}
