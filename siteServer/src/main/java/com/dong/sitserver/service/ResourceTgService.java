package com.dong.sitserver.service;

import com.dong.sitserver.bean.ResourceBean;
import com.dong.sitserver.bean.ResourceTgBean;

import java.util.Collection;
import java.util.List;

/**
 * Created by rxia on 2015/9/7.
 */
public interface ResourceTgService {


    Collection<ResourceTgBean> queryResourceTgList(String tgId,
                                                   ResourceBean resourceBean);


    /**
     * 添加推广资源
     *
     * @param resourceTgBean
     * @return
     */
    Integer addResourceTg(ResourceTgBean resourceTgBean);

    /**
     * 更新推广资源
     *
     * @param resourceTgBean
     */
    void updateResourceTg(ResourceTgBean resourceTgBean);

    /**
     * 删除推广资源
     *
     * @param resourceTgBean
     */
    void deleteResourceTg(ResourceTgBean resourceTgBean);

    /**
     * 查询推广资源
     *
     * @param resourceTgBean
     * @return
     */
    List<ResourceTgBean> queryResourceTgs(ResourceTgBean resourceTgBean);

    /**
     * 查询推广资源
     *
     * @param resourceTgBean
     * @return
     */
    ResourceTgBean findResourceTg(ResourceTgBean resourceTgBean);


}
