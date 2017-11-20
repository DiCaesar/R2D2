package com.r2d2.pecan.dao.impl;

import com.r2d2.pecan.dao.manager.CategoriesManager;
import com.r2d2.pecan.dao.mapper.CategoriesMapper;
import com.r2d2.pecan.dao.model.CategoriesDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by DiCaesar on 2017/8/24
 */

@Repository
public class CategoriesManagerImpl implements CategoriesManager {

    @Autowired(required = false)
    private CategoriesMapper categoriesMapper;

    @Override
    public int insertNewCategories(CategoriesDO categoriesDO){
        return  categoriesMapper.insert(categoriesDO);
    }

    @Override
    public List<CategoriesDO> queryCategoriesList(String CategoryId, String CategoryName){
        return categoriesMapper.selectCategoryList(CategoryId,CategoryName);

    }

    @Override
    public int updateCategoriesList(CategoriesDO categoriesDO) {
        return 0;
    }
}
