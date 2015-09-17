package com.dong.sitserver.service;

import com.dong.sitserver.bean.CategoryBean;

import java.util.List;

/**
 * Created by rxia on 2015/9/2.
 */

public interface CategoryService {

    /**
     * 添加category
     *
     * @param categoryBean
     * @return
     */
    Integer addCategory(CategoryBean categoryBean);

    /**
     * 更新category
     *
     * @param categoryBean
     */
    void updateCategory(CategoryBean categoryBean);

    void deleteCategory(CategoryBean categoryBean);

    /**
     * 查询category
     *
     * @param categoryBean
     * @return
     */
    List<CategoryBean> queryCategorys(CategoryBean categoryBean);


    CategoryBean findCategory(CategoryBean categoryBean);


}
