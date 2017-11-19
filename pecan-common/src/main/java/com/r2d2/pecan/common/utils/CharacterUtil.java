package com.r2d2.pecan.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

/**
 * 字符处理
 * <p>
 * 1.字符串截取（按字符）
 * 2.字符串截取(按子节)
 * 3.null 转 String
 * </p>
 */
@Slf4j
public class CharacterUtil {

    /**
     * 字符串截取（按字符）
     *
     * @param subStr 截取字符
     * @param len    截取长度
     * @return 截取值
     */
    public static String substring(String subStr, int len) {

        if (StringUtils.isBlank(subStr) || subStr.length() < len) {
            return subStr;
        }
        return subStr.substring(0, len);
    }

    /**
     * 字符串截取(按子节)
     *
     * @param subStr 截取字符
     * @param length 截取长度
     * @param encode 编码格式
     * @return 截取值
     */
    public static String substringByByte(String subStr, int length, String encode) {

        if (subStr == null) {
            return null;
        }
        try {
            StringBuilder sb = new StringBuilder();
            int currentLength = 0;
            for (char c : subStr.toCharArray()) {
                currentLength += String.valueOf(c).getBytes(encode).length;
                if (currentLength <= length) {
                    sb.append(c);
                } else {
                    break;
                }
            }
            return sb.toString();
        } catch (Exception e) {
            return substring(subStr, length / 3);
        }
    }

    /**
     * null 转 String
     *
     * @param str 字符
     * @return 字符
     */
    public static String null2String(String str) {

        if (StringUtils.isBlank(str)) {
            return StringUtils.EMPTY;
        }
        return str.trim();
    }
}