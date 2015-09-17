package com.dong.sitserver.service.impl;

import com.dong.sitserver.bean.CategoryBean;
import com.dong.sitserver.dao.CategoryDAO;
import com.dong.sitserver.service.CategoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by rxia on 2015/9/2.
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Resource
    private CategoryDAO categoryDAO;


    /**
     * 添加category
     *
     * @param categoryBean
     * @return
     */
    public Integer addCategory(CategoryBean categoryBean) {

        return categoryDAO.addBean(categoryBean);


    }

    /**
     * 更新category
     *
     * @param categoryBean
     */
    public void updateCategory(CategoryBean categoryBean) {
        categoryDAO.updateBean(categoryBean);

    }

    @Override
    public void deleteCategory(CategoryBean categoryBean) {
        categoryDAO.deleteBean(categoryBean);
    }

    /**
     * 查询category
     *
     * @param categoryBean
     * @return
     */
    public List<CategoryBean> queryCategorys(CategoryBean categoryBean) {
        return categoryDAO.queryBeans(categoryBean);

    }


    public CategoryBean findCategory(CategoryBean categoryBean) {
        return categoryDAO.findBean(categoryBean);
    }


}
