package com.r2d2.repository.dao.imp;

import com.r2d2.repository.dao.manager.CategoriesManager;
import com.r2d2.repository.dao.mapper.CategoriesMapper;
import com.r2d2.repository.dao.model.CategoriesDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by DiCaesar on 2017/8/24
 */
@Service
public class CategoriesManagerImp implements CategoriesManager {

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
}
