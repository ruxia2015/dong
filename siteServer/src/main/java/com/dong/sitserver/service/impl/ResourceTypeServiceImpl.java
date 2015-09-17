package com.dong.sitserver.service.impl;

import com.dong.sitserver.bean.ResourceTypeBean;
import com.dong.sitserver.dao.ResourceTypeDAO;
import com.dong.sitserver.service.ResourceTypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by rxia on 2015/9/7.
 */
@Service
public class ResourceTypeServiceImpl implements ResourceTypeService {

    @Resource
    private ResourceTypeDAO resourceTypeDAO;

    /**
     * 添加资源类型
     *
     * @param resourceTypeBean
     * @return
     */
    public Integer addResourceType(ResourceTypeBean resourceTypeBean) {
        return resourceTypeDAO.addBean(resourceTypeBean);

    }

    /**
     * 更新资源类型
     *
     * @param resourceTypeBean
     */
    public void updateResourceType(ResourceTypeBean resourceTypeBean) {
        resourceTypeDAO.updateBean(resourceTypeBean);
    }

    /**
     * 删除资源类型
     *
     * @param resourceTypeBean
     */
    public void deleteResourceType(ResourceTypeBean resourceTypeBean) {
        resourceTypeDAO.deleteBean(resourceTypeBean);

    }

    /**
     * 查询资源类型
     *
     * @param resourceTypeBean
     * @return
     */
    public List<ResourceTypeBean> queryResourceTypes(ResourceTypeBean resourceTypeBean) {
        return resourceTypeDAO.queryBeans(resourceTypeBean);

    }

    /**
     * 查询资源类型
     *
     * @param resourceTypeBean
     * @return
     */
    public ResourceTypeBean findResourceType(ResourceTypeBean resourceTypeBean) {
        return resourceTypeDAO.findBean(resourceTypeBean);
    }
}
