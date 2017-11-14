package com.r2d2.pecan.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Flag枚举
 */
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public enum FlagEnum {

    /**
     * TRUE     是
     * FALSE    否
     */
    TRUE("TRUE", "是"),
    FALSE("FALSE", "否");

    /**
     * key
     */
    private String code;

    /**
     * 描述
     */
    private String desc;

    /**
     * 根据Code获取Value
     *
     * @param code 键
     * @return 值
     */
    public static String explain(String code) {
        for (FlagEnum certifyEnum : FlagEnum.values()) {
            if (certifyEnum.getCode().equals(code)) {
                return certifyEnum.getDesc();
            }
        }
        return null;
    }
}
