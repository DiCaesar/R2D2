package com.r2d2.repository.dao.mapper;

import com.r2d2.repository.dao.model.CategoriesDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by DiCaesar on 2017/8/24
 */
public interface CategoriesMapper {

    int insert(CategoriesDO categoriesDO);

    List<CategoriesDO> selectCategoryList(@Param("categoryId") String categoryId,
                                          @Param("categoryName") String categoryName);
}
