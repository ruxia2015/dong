package com.dong.sitserver.service.impl;

import com.dong.sitserver.bean.*;
import com.dong.sitserver.dao.ResourceTgDAO;
import com.dong.sitserver.service.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ResourceTgServiceImpl implements ResourceTgService {
    @Resource
    private ResourceTgDAO resourceTgDAO;

    @Resource
    private ResourceService resourceService;

    @Resource
    private ResourceTypeService resourceTypeService;

    @Resource
    private CategoryService categoryService;

    @Resource
    private WebsiteService websiteService;


    @Override
    public Collection<ResourceTgBean> queryResourceTgList(String tgId,
                                                          ResourceBean resourceBean) {
        //查询资源类型
        List<ResourceTypeBean> resourceTypeBeans = resourceTypeService.queryResourceTypes(new ResourceTypeBean());
        Map<String, String> rTypeMap = new HashMap<String, String>();
        for (ResourceTypeBean temp : resourceTypeBeans) {
            rTypeMap.put(temp.getId(), temp.getName());
        }

        //插叙所有的资源类别
        List<CategoryBean> categoryBeans = categoryService.queryCategorys(new CategoryBean());
        Map<String, String> categoryMap = new HashMap<String, String>();
        for (CategoryBean temp : categoryBeans) {
            categoryMap.put(temp.getId(), temp.getName());
        }
        categoryMap.put("-1", "未分类");

        WebsiteBean websiteBean = new WebsiteBean();
        websiteBean.setId(tgId);
        websiteBean = websiteService.findWebsite(websiteBean);

        //查询指定类别的资源
        resourceBean.setCategoryIds(websiteBean.getCategoryIds());
        List<ResourceBean> resourceBeans = resourceService.queryResources(resourceBean);

        Map<String, ResourceTgBean> resourceMap = new HashMap<String, ResourceTgBean>();
        String resourceIds = "";
        for (ResourceBean tempR : resourceBeans) {
            resourceIds = resourceIds + tempR.getId() + ",";

            ResourceTgBean rtBean = new ResourceTgBean();
            rtBean.setResourceId(tempR.getId());
            rtBean.setResourceDomain(tempR.getDomain());
            rtBean.setRegisterState(tempR.getRegisterState());
            rtBean.setResourceUrl(tempR.getUrl());
            rtBean.setResourceTypeName(rTypeMap.get(tempR.getType()) == null ? tempR.getType()
                    : rTypeMap.get(tempR.getType()));
            if (websiteBean != null) {
                rtBean.setTgId(websiteBean.getId());
                rtBean.setTgDomain(websiteBean.getDomain());
            }
            rtBean.setCategory(categoryMap.get(tempR.getCategoryId()));

            //放入到集合中
            resourceMap.put(tempR.getId(), rtBean);
        }

        //查询符合条件的推广的资源的资源账号信息
        ResourceTgBean bean = new ResourceTgBean();
        bean.setTgId(tgId);
        bean.setResourceIds(resourceIds);
        List<ResourceTgBean> beans = queryResourceTgs(bean);

        for (ResourceTgBean tempR : beans) {
            ResourceTgBean resourceTgBean = resourceMap.get(tempR.getResourceId());
            if (resourceTgBean == null) {
                resourceTgBean = new ResourceTgBean();
            }

            //复制值
            tempR.setResourceId(resourceTgBean.getResourceId());
            tempR.setResourceDomain(resourceTgBean.getResourceDomain());
            tempR.setRegisterState(resourceTgBean.getRegisterState());
            tempR.setCategory(resourceTgBean.getCategory());
            tempR.setResourceTypeName(resourceTgBean.getResourceTypeName());
            tempR.setResourceUrl(resourceTgBean.getResourceUrl());

            resourceMap.put(tempR.getResourceId(), tempR);
        }

        return resourceMap.values();
    }


    @Override
    public Integer addResourceTg(ResourceTgBean resourceTgBean) {
        return resourceTgDAO.addBean(resourceTgBean);
    }

    @Override
    public void updateResourceTg(ResourceTgBean resourceTgBean) {
        resourceTgDAO.updateBean(resourceTgBean);
    }

    @Override
    public void deleteResourceTg(ResourceTgBean resourceTgBean) {
        resourceTgDAO.deleteBean(resourceTgBean);

    }

    @Override
    public List<ResourceTgBean> queryResourceTgs(ResourceTgBean resourceTgBean) {
        return resourceTgDAO.queryBeans(resourceTgBean);
    }

    @Override
    public ResourceTgBean findResourceTg(ResourceTgBean resourceTgBean) {
        return resourceTgDAO.findBean(resourceTgBean);
    }

}
