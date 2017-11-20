package com.r2d2.pecan.test.dao;

import com.r2d2.pecan.dao.manager.CategoriesManager;
import com.r2d2.pecan.dao.manager.StorageDetailManager;
import com.r2d2.pecan.dao.model.CategoriesDO;
import com.r2d2.pecan.dao.model.StorageDetailDO;
import com.r2d2.pecan.test.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * Created by DiCaesar on 2017/8/24
 */
@Slf4j
public class CategoriesManagerTest extends BaseTest{

    @Autowired
    CategoriesManager categoriesManager;

    @Autowired
    StorageDetailManager storageDetailManager;

    @Test
    public void insertTest(){
        CategoriesDO categoriesDO = new CategoriesDO();
        categoriesDO.setCategoryId(3);
        categoriesDO.setCategoryName("aaa");
        categoriesDO.setCategoryDesc("dddd");
        categoriesManager.insertCategories(categoriesDO);
    }

    @Test
    public void queryTest1(){
        CategoriesDO categoriesDO = new CategoriesDO();

        Integer count = categoriesManager.selectCategoryCount(categoriesDO);
        List<CategoriesDO> list = categoriesManager.queryCategoriesPage(categoriesDO,0,10);
        log.info("CategoriesDOs========{},{}",count,list);
    }

    @Test
    public void queryTest2(){
        StorageDetailDO storageDetailDO = new StorageDetailDO();

        Integer count = storageDetailManager.queryStorageCount(storageDetailDO);
        List<StorageDetailDO> list = storageDetailManager.queryStoragePage(storageDetailDO,0,10);
        log.info("CategoriesDOs========{},{}",count,list);
    }

}
