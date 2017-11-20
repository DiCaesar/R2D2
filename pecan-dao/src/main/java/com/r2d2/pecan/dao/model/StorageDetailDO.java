package com.r2d2.pecan.dao.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by DiCaesar on 2017/8/28
 */
@Getter
@Setter
@ToString
public class StorageDetailDO extends BaseDO{

    private String id;

    private String categoryId;

    private String groupName;

    private String storageUrl;

    private String filePath;

    private String fileName;

    private String status;

    private String urlStr;

    //备注
    private String remark;


}
