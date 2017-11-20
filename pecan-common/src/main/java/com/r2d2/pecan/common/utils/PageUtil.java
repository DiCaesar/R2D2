package com.r2d2.pecan.common.utils;

/**
 * 分页计算
 * <p>
 * 1、根据总数和每页条数计算页数
 * </p>
 */
public class PageUtil {

    /**
     * 根据总数和每页条数计算页数
     *
     * @param count     总行数
     * @param pageSize 每页条数
     * @return 所有页数
     */
    public static Integer calPages(Integer count, Integer pageSize) {

        if (count <= 0) {
            return 0;
        }

        return (count % pageSize) == 0 ? (count / pageSize)
                : (count / pageSize + 1);
    }
}
