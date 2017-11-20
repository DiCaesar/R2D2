package com.r2d2.pecan.dao.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * Created by DiCaesar on 2017/8/28
 */
@Getter
@Setter
@ToString
public class StorageDetailDO implements Serializable {
    private Integer id;

    private Integer categoryId;

    private String groupName;

    private String groupDesc;

    private String groupUrl;

    private String tags;

    private String picList;

    private String filePath;

    private String downloadFlag;

    private String usableFlag;
}
