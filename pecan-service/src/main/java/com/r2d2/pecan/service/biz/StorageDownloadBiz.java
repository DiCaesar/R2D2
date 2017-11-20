package com.r2d2.pecan.service.biz;

import com.r2d2.pecan.common.utils.PageUtil;
import com.r2d2.pecan.dao.manager.CategoriesManager;
import com.r2d2.pecan.dao.manager.StorageDetailManager;
import com.r2d2.pecan.dao.model.CategoriesDO;
import com.r2d2.pecan.dao.model.StorageDetailDO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by DiCaesar on 2017/11/20
 */
public class StorageDownloadBiz {

    @Autowired
    CategoriesManager categoriesManager;

    @Autowired
    StorageDetailManager storageDetailManager;

    private void dispatcher(){
        CategoriesDO categoriesDO = new CategoriesDO();
        List<CategoriesDO> list = categoriesManager.queryCategoriesPage(categoriesDO,0,10);
        for(CategoriesDO cate : list) {
            StorageDetailDO storageDetailDO = new StorageDetailDO();
            storageDetailDO.setCategoryId(cate.getCategoryId());
            int totalCount = storageDetailManager.queryStorageCount(storageDetailDO);
            int pageCount = PageUtil.calPages(totalCount,100);
            for (int i=0;i<pageCount; i++){

            }
        }

    }
}
