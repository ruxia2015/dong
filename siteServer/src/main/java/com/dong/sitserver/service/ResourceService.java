package com.dong.sitserver.service;

import com.dong.sitserver.bean.ResourceBean;

import java.util.List;

/**
 * Created by rxia on 2015/9/7.
 */
public interface ResourceService {
    /**
     * 添加资源类型
     *
     * @param resourceBean
     * @return
     */
    Integer addResource(ResourceBean resourceBean);

    /**
     * 更新资源类型
     *
     * @param resourceBean
     */
    void updateResource(ResourceBean resourceBean);

    /**
     * 删除资源类型
     *
     * @param resourceBean
     */
    void deleteResource(ResourceBean resourceBean);

    /**
     * 查询资源类型
     *
     * @param resourceBean
     * @return
     */
    List<ResourceBean> queryResources(ResourceBean resourceBean);

    /**
     * 查询资源类型
     *
     * @param resourceBean
     * @return
     */
    ResourceBean findResource(ResourceBean resourceBean);
}
