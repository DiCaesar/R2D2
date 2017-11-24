package com.r2d2.pecan.dao.manager;

import com.r2d2.pecan.dao.model.StorageDetailDO;

import java.util.List;

/**
 * Created by DiCaesar on 2017/11/17
 */
public interface StorageDetailManager {


    int insertStorage(StorageDetailDO storageDetailDO);

    int updateStorage(StorageDetailDO storageDetailDO);

    int deleteStorage(String id);

    Integer queryStorageCount(StorageDetailDO storageDetailDO);


    List<StorageDetailDO> queryStoragePage(StorageDetailDO storageDetailDO, int startRow, int pageSize);

}
