package com.dong.sitserver.dao;

import java.util.List;
import com.dong.sitserver.bean.WebsiteBean;

public interface WebsiteDAO
{
    public int saveWebsite(WebsiteBean bean);
    public int updateWebsite(WebsiteBean bean);
    public int deleteWebsite(WebsiteBean bean);
    public int countWebsite(WebsiteBean bean);
    public List<WebsiteBean> queryWebsiteList(WebsiteBean bean);
    public WebsiteBean  findWebsite(WebsiteBean bean);
} 