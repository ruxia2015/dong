package com.dong.sitserver.controller;

import com.dong.sitserver.bean.WebsiteBean;
import com.dong.sitserver.common.BackJsonBean;
import com.dong.sitserver.common.annotation.util.JacksonUtil;
import com.dong.sitserver.common.annotation.util.StringTools;
import com.dong.sitserver.service.WebsiteService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by rxia on 2015/9/7.
 */

@Controller
public class WebsiteController {
    private WebsiteService websiteService;

    @RequestMapping("/website/list.action")
    public ModelAndView init() {
        ModelAndView mv = new ModelAndView("/queryList");
        mv.addObject("userJs", "website.js");
        return mv;
    }

    @RequestMapping("/websiteAjax/list.action")
    protected String list(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        WebsiteBean bean = new WebsiteBean();
        List<WebsiteBean> beans = websiteService.queryWebsites(bean);

        return JacksonUtil.objToJson(beans);

    }

    @RequestMapping("/websiteAjax/add.action")
    public String add(HttpServletRequest req, HttpServletResponse resp)
            throws Exception {
        String objJson = (req.getParameter("bean"));
        if (StringTools.isEmptyOrNone(objJson)) {
            return new BackJsonBean("无数据，不能添加").toJson();
        }

        WebsiteBean bean = (WebsiteBean) JacksonUtil.jsonToObj(objJson, WebsiteBean.class);

        //判断是否可以添加
        WebsiteBean beanQ = new WebsiteBean();
        beanQ.setDomain(bean.getDomain());
        beanQ = websiteService.findWebsite(beanQ);
        if (beanQ != null) {
            return new BackJsonBean("已经存在该推广网站，添加失败").toJson();
        }

        websiteService.addWebsite(bean);

        return new BackJsonBean().toJson();
    }

    @RequestMapping("/websiteAjax/update.action")
    public String update(HttpServletRequest req, HttpServletResponse resp)
            throws Exception {

        String objJson = (req.getParameter("bean"));
        WebsiteBean bean;
        bean = (WebsiteBean) JacksonUtil.jsonToObj(objJson, WebsiteBean.class);
        websiteService.updateWebsite(bean);
        return new BackJsonBean().toJson();

    }

    @RequestMapping("/websiteAjax/delete.action")
    public String delete(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String id = req.getParameter("id");

        WebsiteBean WebsiteBean = new WebsiteBean();
        WebsiteBean.setId(id);
        websiteService.deleteWebsite(WebsiteBean);

        return new BackJsonBean().toJson();
    }


}
