package com.r2d2.repository.dao.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


/**
 * Created by DiCaesar on 2017/8/24
 */
@Setter
@Getter
@ToString(callSuper = true)
public class CategoriesDO {

    private String categoryId;

    private String categories;

    private String categoryDesc;
}
