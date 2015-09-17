package com.dong.sitserver.service;

import com.dong.sitserver.bean.WebsiteBean;

import java.util.List;

/**
 * Created by rxia on 2015/9/7.
 */
public interface WebsiteService {

    /**
     * 添加网站
     *
     * @param websiteBean
     * @return
     */
    Integer addWebsite(WebsiteBean websiteBean);

    /**
     * 更新网站
     *
     * @param websiteBean
     */
    void updateWebsite(WebsiteBean websiteBean);

    /**
     * 删除网站
     *
     * @param websiteBean
     */
    void deleteWebsite(WebsiteBean websiteBean);

    /**
     * 查询网站
     *
     * @param websiteBean
     * @return
     */
    List<WebsiteBean> queryWebsites(WebsiteBean websiteBean);

    /**
     * 查询网站
     *
     * @param websiteBean
     * @return
     */
    WebsiteBean findWebsite(WebsiteBean websiteBean);
}
