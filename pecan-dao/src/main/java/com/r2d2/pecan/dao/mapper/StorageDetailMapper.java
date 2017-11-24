package com.r2d2.pecan.dao.mapper;

import com.r2d2.pecan.dao.model.StorageDetailDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by DiCaesar on 2017/11/17
 */
public interface StorageDetailMapper {

    int insertStorage(StorageDetailDO storageDetailDO);

    int updateStorage(@Param("storageDetailDO")StorageDetailDO storageDetailDO);

    int deleteStorage(String id);

    Integer queryStorageCount(@Param("storageDetailDO")StorageDetailDO storageDetailDO);

    List<StorageDetailDO> queryStoragePage(@Param("storageDetailDO")StorageDetailDO storageDetailDO,
                                           @Param("startRow") Integer startRow,
                                           @Param("pageSize") Integer pageSize);

}
