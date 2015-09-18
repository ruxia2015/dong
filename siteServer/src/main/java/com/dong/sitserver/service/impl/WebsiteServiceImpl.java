package com.dong.sitserver.service.impl;

import com.dong.sitserver.bean.WebsiteBean;
import com.dong.sitserver.dao.WebsiteDAO;
import com.dong.sitserver.service.WebsiteService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by rxia on 2015/9/7.
 */
@Service
public class WebsiteServiceImpl implements WebsiteService {

    @Resource
    private WebsiteDAO websiteDAO;

    /**
     * 添加网站
     *
     * @param websiteBean
     * @return
     */
    public Integer addWebsite(WebsiteBean websiteBean) {

        return websiteDAO.saveWebsite(websiteBean);

    }

    /**
     * 更新网站
     *
     * @param websiteBean
     */
    public void updateWebsite(WebsiteBean websiteBean) {
        websiteDAO.updateWebsite(websiteBean);

    }

    /**
     * 删除网站
     *
     * @param websiteBean
     */
    public void deleteWebsite(WebsiteBean websiteBean) {
        websiteDAO.deleteWebsite(websiteBean);

    }

    /**
     * 查询网站
     *
     * @param websiteBean
     * @return
     */
    public List<WebsiteBean> queryWebsites(WebsiteBean websiteBean) {
        return websiteDAO.queryWebsiteList(websiteBean);

    }

    /**
     * 查询网站
     *
     * @param websiteBean
     * @return
     */
    public WebsiteBean findWebsite(WebsiteBean websiteBean) {
        return websiteDAO.findWebsite(websiteBean);

    }
}
