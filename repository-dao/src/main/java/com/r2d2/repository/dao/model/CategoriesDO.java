package com.r2d2.repository.dao.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;


/**
 * Created by DiCaesar on 2017/8/24
 */
@Setter
@Getter
@ToString(callSuper = true)
public class CategoriesDO implements Serializable {

    private String categoryId;

    private String categories;

    private String categoryDesc;
}
