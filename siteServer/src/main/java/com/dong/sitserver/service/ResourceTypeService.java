package com.dong.sitserver.service;

import com.dong.sitserver.bean.ResourceTypeBean;

import java.util.List;

/**
 * Created by rxia on 2015/9/7.
 */
public interface ResourceTypeService {
    /**
     * 添加资源类型
     *
     * @param resourceTypeBean
     * @return
     */
    Integer addResourceType(ResourceTypeBean resourceTypeBean);

    /**
     * 更新资源类型
     *
     * @param resourceTypeBean
     */
    void updateResourceType(ResourceTypeBean resourceTypeBean);

    /**
     * 删除资源类型
     *
     * @param resourceTypeBean
     */
    void deleteResourceType(ResourceTypeBean resourceTypeBean);

    /**
     * 查询资源类型
     *
     * @param resourceTypeBean
     * @return
     */
    List<ResourceTypeBean> queryResourceTypes(ResourceTypeBean resourceTypeBean);

    /**
     * 查询资源类型
     *
     * @param resourceTypeBean
     * @return
     */
    ResourceTypeBean findResourceType(ResourceTypeBean resourceTypeBean);
}
