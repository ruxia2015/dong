package com.dong.sitserver.service.impl;

import com.dong.sitserver.bean.ResourceBean;
import com.dong.sitserver.dao.ResourceDAO;
import com.dong.sitserver.service.ResourceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by rxia on 2015/9/7.
 */
@Service
public class ResourceServiceImpl implements ResourceService {

    @Resource
    private ResourceDAO resourceDAO;

    /**
     * 添加资源类型
     *
     * @param resourceBean
     * @return
     */
    public Integer addResource(ResourceBean resourceBean) {
        return resourceDAO.addBean(resourceBean);

    }

    /**
     * 更新资源类型
     *
     * @param resourceBean
     */
    public void updateResource(ResourceBean resourceBean) {
        resourceDAO.updateBean(resourceBean);
    }

    /**
     * 删除资源类型
     *
     * @param resourceBean
     */
    public void deleteResource(ResourceBean resourceBean) {
        resourceDAO.deleteBean(resourceBean);

    }

    /**
     * 查询资源类型
     *
     * @param resourceBean
     * @return
     */
    public List<ResourceBean> queryResources(ResourceBean resourceBean) {
        return resourceDAO.queryBeans(resourceBean);

    }

    /**
     * 查询资源类型
     *
     * @param resourceBean
     * @return
     */
    public ResourceBean findResource(ResourceBean resourceBean) {
        return resourceDAO.findBean(resourceBean);
    }
}
