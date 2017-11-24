package com.r2d2.pecan.dao.impl;

import com.r2d2.pecan.dao.manager.StorageDetailManager;
import com.r2d2.pecan.dao.mapper.StorageDetailMapper;
import com.r2d2.pecan.dao.model.StorageDetailDO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Created by DiCaesar on 2017/11/17
 */
@Slf4j
@Repository
public class StorageDetailManagerImpl implements StorageDetailManager {

    @Autowired(required = false)
    StorageDetailMapper storageDetailMapper;

    @Override
    public int insertStorage(StorageDetailDO storageDetailDO) {
        return storageDetailMapper.insertStorage(storageDetailDO);
    }

    @Override
    public int updateStorage(StorageDetailDO storageDetailDO) {
        return storageDetailMapper.updateStorage(storageDetailDO);
    }

    @Override
    public int deleteStorage(String id) {
        return storageDetailMapper.deleteStorage(id);
    }

    @Override
    public Integer queryStorageCount(StorageDetailDO storageDetailDO) {
        return storageDetailMapper.queryStorageCount(storageDetailDO);
    }

    @Override
    public List<StorageDetailDO> queryStoragePage(StorageDetailDO storageDetailDO, int startRow, int pageSize) {
        return storageDetailMapper.queryStoragePage(storageDetailDO,startRow,pageSize);
    }
}
