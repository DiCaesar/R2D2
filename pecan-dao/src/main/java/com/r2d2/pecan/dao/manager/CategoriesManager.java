package com.r2d2.pecan.dao.manager;

import com.r2d2.pecan.dao.model.CategoriesDO;

import java.util.List;

/**
 * Created by DiCaesar on 2017/8/24
 */
public interface CategoriesManager {

    int insertCategories(CategoriesDO categoriesDO);

    Integer selectCategoryCount(CategoriesDO categoriesDO);

    List<CategoriesDO> queryCategoriesPage(CategoriesDO categoriesDO,Integer startRow,Integer pageSize);

}
