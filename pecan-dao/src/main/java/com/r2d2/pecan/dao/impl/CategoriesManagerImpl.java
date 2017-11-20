package com.r2d2.pecan.dao.impl;

import com.r2d2.pecan.dao.manager.CategoriesManager;
import com.r2d2.pecan.dao.mapper.CategoriesMapper;
import com.r2d2.pecan.dao.model.CategoriesDO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by DiCaesar on 2017/8/24
 */
@Slf4j
@Repository
public class CategoriesManagerImpl implements CategoriesManager {

    @Autowired
    private CategoriesMapper categoriesMapper;

    @Override
    public int insertCategories(CategoriesDO categoriesDO){
        return  categoriesMapper.insertCategories(categoriesDO);
    }

    @Override
    public Integer selectCategoryCount(CategoriesDO categoriesDO) {
        return categoriesMapper.selectCategoryCount(categoriesDO);
    }


    @Override
    public List<CategoriesDO> queryCategoriesPage(CategoriesDO categoriesDO,Integer startRow,Integer pageSize){
        if(null == categoriesDO){
            log.info("nullllllllll");
        }
        log.info("categoriesDO{}",categoriesDO);
        return categoriesMapper.selectCategoryPage(categoriesDO,startRow,pageSize);

    }

}
