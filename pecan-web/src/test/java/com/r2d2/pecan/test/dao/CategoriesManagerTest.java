package com.r2d2.pecan.test.dao;

import com.r2d2.pecan.dao.manager.CategoriesManager;
import com.r2d2.pecan.dao.model.CategoriesDO;
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

    @Test
    public void insertTest(){
        CategoriesDO categoriesDO = new CategoriesDO();
        categoriesDO.setCategoryId("3");
        categoriesDO.setCategories("aaa");
        categoriesDO.setCategoryDesc("dddd");
        categoriesManager.insertNewCategories(categoriesDO);
    }

    @Test
    public void queryTest(){
        List<CategoriesDO> list = categoriesManager.queryCategoriesList(null,null);
        log.info("CategoriesDOs========{}",list);
    }

}
