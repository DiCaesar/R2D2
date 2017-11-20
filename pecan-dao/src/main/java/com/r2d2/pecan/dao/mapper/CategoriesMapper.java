package com.r2d2.pecan.dao.mapper;

import com.r2d2.pecan.dao.model.CategoriesDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by DiCaesar on 2017/8/24
 */
public interface CategoriesMapper {

    Integer insertCategories(CategoriesDO categoriesDO);

    Integer selectCategoryCount(@Param("categoriesDO") CategoriesDO categoriesDO);

    List<CategoriesDO> selectCategoryPage(@Param("categoriesDO") CategoriesDO categoriesDO,
                                          @Param("startRow") Integer startRow,
                                          @Param("pageSize") Integer pageSize);
}
