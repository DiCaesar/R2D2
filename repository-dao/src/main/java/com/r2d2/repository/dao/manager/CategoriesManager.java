package com.r2d2.repository.dao.manager;

import com.r2d2.repository.dao.model.CategoriesDO;

import java.util.List;

/**
 * Created by DiCaesar on 2017/8/24
 */
public interface CategoriesManager {

    int insertNewCategories(CategoriesDO categoriesDO);

    List<CategoriesDO> queryCategoriesList(String CategoryId,String CategoryName);
}
