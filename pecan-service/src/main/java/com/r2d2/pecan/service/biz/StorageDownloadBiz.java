package com.r2d2.pecan.service.biz;

import com.r2d2.pecan.common.enums.StatusEnum;
import com.r2d2.pecan.common.utils.FileUtil;
import com.r2d2.pecan.common.utils.PageUtil;
import com.r2d2.pecan.dao.manager.CategoriesManager;
import com.r2d2.pecan.dao.manager.StorageDetailManager;
import com.r2d2.pecan.dao.model.CategoriesDO;
import com.r2d2.pecan.dao.model.StorageDetailDO;
import com.r2d2.pecan.service.thread.CategoriesDownLoadThread;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by DiCaesar on 2017/11/20
 */
@Slf4j
@Service
public class StorageDownloadBiz {

    @Autowired
    CategoriesManager categoriesManager;

    @Autowired
    StorageDetailManager storageDetailManager;

    private String basePath = "E:/storageDown/";

    public void doBiz(){
        int cateStartInx = 0;
        ExecutorService threadPool = Executors.newFixedThreadPool(20);
        Map<String,List<StorageDetailDO>> detailMap = getDetailMap(getCateMap(cateStartInx));
        for(Map.Entry<String,List<StorageDetailDO>> entry : detailMap.entrySet()){
            String cateName = entry.getKey();
            log.info("类型 {}",cateName);
            List<StorageDetailDO> detailList = entry.getValue();
            for (StorageDetailDO detailDO : detailList) {
                String filePath = basePath+cateName+"/"+detailDO.getGroupName();
                FileUtil.createPathIfNotExists(filePath);
                threadPool.submit(new CategoriesDownLoadThread(filePath,detailDO,storageDetailManager));
            }
        }
    }

    private Map<Integer,String> getCateMap(int cateStartInx){
        Map<Integer,String> cataMap = new HashMap<Integer, String>();
        CategoriesDO categoriesDO = new CategoriesDO();
        List<CategoriesDO> list = categoriesManager.queryCategoriesPage(categoriesDO,cateStartInx,2);
        for (CategoriesDO aDo : list) {
            cataMap.put(aDo.getCategoryId(),aDo.getCategoryName());
        }
        return cataMap;
    }

    private Map<String,List<StorageDetailDO>>  getDetailMap(Map<Integer,String> cateMap){
        Map<String,List<StorageDetailDO>> detailDOMap = new HashMap<String, List<StorageDetailDO>>();
        for(Map.Entry<Integer,String> entry : cateMap.entrySet()){
            List<StorageDetailDO> totalList = new ArrayList<StorageDetailDO>();
            Integer cateId = entry.getKey();
            String cateName = entry.getValue();
            StorageDetailDO storageDetailDO = new StorageDetailDO();
            storageDetailDO.setCategoryId(cateId);
            storageDetailDO.setDownloadFlag(StatusEnum.Initial.getCode());
            int pageSize = 100;
            int totalCount = storageDetailManager.queryStorageCount(storageDetailDO);
            int pageCount = PageUtil.calPages(totalCount,pageSize);
            for (int i=0;i<pageCount; i++){
                List<StorageDetailDO> detailList = storageDetailManager.queryStoragePage(
                        storageDetailDO,i*pageSize,pageSize);
                totalList.addAll(detailList);
            }
            detailDOMap.put(cateName,totalList);
        }
        return detailDOMap;
    }

}
