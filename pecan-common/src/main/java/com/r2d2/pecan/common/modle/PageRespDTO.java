package com.r2d2.pecan.common.modle;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * 分页查询返回包装对象
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
public class PageRespDTO<T> implements Serializable {

    /**
     * 总行数
     */
    private Integer totalSize;

    /**
     * 分页结果集
     */
    private List<T> results;
}
