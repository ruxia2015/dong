package com.dong.sitserver.controller;

import com.dong.sitserver.bean.CategoryBean;
import com.dong.sitserver.bean.ResourceBean;
import com.dong.sitserver.bean.ResourceTypeBean;
import com.dong.sitserver.common.BackJsonBean;
import com.dong.sitserver.common.annotation.util.JacksonUtil;
import com.dong.sitserver.common.annotation.util.StringTools;
import com.dong.sitserver.service.CategoryService;
import com.dong.sitserver.service.ResourceService;
import com.dong.sitserver.service.ResourceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by rxia on 2015/9/7.
 */

@Controller
public class ResourceController {

    @Resource
    private CategoryService categoryService;

    @Resource
    private ResourceService resourceService;

    @Autowired
    private ResourceTypeService resourceTypeService;

    private static String getDomain(String url) {
        if (StringTools.isEmptyOrNone(url)) {
            return null;
        }
        if (!url.contains(".")) {
            return null;
        }

        String domain = url.replaceFirst("^http[s]?://+", "");

        if (domain.indexOf("/") > 0) {
            domain = domain.substring(0, domain.indexOf("/"));
        }
        if (domain.indexOf("?") > 0) {
            domain = domain.substring(0, domain.indexOf("?"));
        }

        String[] arr = domain.split("\\.");

        if (arr.length > 2) {
            domain = arr[arr.length - 2] + "." + arr[arr.length - 1];
        }

        System.out.println(domain);
        return domain;

    }

    @RequestMapping("/resource/list.action")
    public ModelAndView init() {
        ModelAndView mv = new ModelAndView("/queryList");
        mv.addObject("userJs", "resource.js");
        return mv;

    }

    @RequestMapping("/resource/toAdd.action")
    public ModelAndView toAdd(HttpServletRequest req, HttpServletResponse resp)
            throws Exception {
        List<ResourceTypeBean> resourceTypeBeans = resourceTypeService.queryResourceTypes(new ResourceTypeBean());

        CategoryBean categoryBean = new CategoryBean();
        List<CategoryBean> categoryBeans = categoryService.queryCategorys(categoryBean);


        ModelAndView mv = new ModelAndView("/addResource");
        mv.addObject("categoryList", categoryBeans);
        mv.addObject("resourceTypes", resourceTypeBeans);
        return mv;

    }

    @RequestMapping("/resource/add.action")
    public void add(HttpServletRequest req, HttpServletResponse resp)
            throws Exception {
        String urls = req.getParameter("urls");
        String resourceType = req.getParameter("resourceType");
        String categoryId = req.getParameter("categoryId");
        String override = req.getParameter("override");

        String message = "";
        int addCnt = 0;

        if (!StringTools.isEmptyOrNone(urls)) {
            String[] urlArr = urls.split("\n");
            for (String str : urlArr) {
                if (StringTools.isEmptyOrNone(str)) {
                    continue;
                }
                String domain = getDomain(str);
                if (StringTools.isEmptyOrNone(domain)) {
                    continue;
                }
                str = str.trim();

                //是否存在
                ResourceBean resourceBean = new ResourceBean();
                resourceBean.setDomain(domain);
                resourceBean = resourceService.findResource(resourceBean);

                //添加资源
                if (resourceBean == null) {
                    resourceBean = new ResourceBean();
                    resourceBean.setType(resourceType);
                    resourceBean.setDomain(domain);
                    resourceBean.setUrl(str);
                    resourceBean.setCategoryId(categoryId);
                    resourceBean.setPr("-1");
                    resourceBean.setAccessState("-1");
                    resourceBean.setRegisterState("-1");
                    resourceBean.setOtherState("-1");
                    resourceService.addResource(resourceBean);
                    addCnt++;
                } else if ("on".equals(override)
                        && !StringTools.isEmptyOrNone(resourceType)) {
                    //更新资源类型
                    resourceBean.setType(resourceType);
                    resourceService.updateResource(resourceBean);
                    resourceService.updateResource(resourceBean);
                }
            }
            message = "添加成功的个数为 【" + addCnt + "】";

        } else {
            message = "地址不能为空";
        }

        req.setAttribute("message", message);
        toAdd(req, resp);


    }

    @RequestMapping("/resourceAjax/list.action")
    protected String list(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        String domain = (String) req.getParameter("domain");
        String type = (String) req.getParameter("type");

        System.out.println("domain  " + domain);

        ResourceBean bean = new ResourceBean();

        bean.setDomain(domain);
        bean.setType(type);
        List<ResourceBean> beans = resourceService.queryResources(bean);


        return JacksonUtil.objToJson(beans);
    }

    @RequestMapping("/resourceAjax/add.action")
    public String addAjax(HttpServletRequest req, HttpServletResponse resp)
            throws Exception {
        String objJson = (req.getParameter("bean"));
        ResourceBean resourceBean = (ResourceBean) JacksonUtil.jsonToObj(objJson,
                ResourceBean.class);
        resourceService.addResource(resourceBean);

        return new BackJsonBean().toJson();

    }

    @RequestMapping("/resourceAjax/update.action")
    public String update(HttpServletRequest req, HttpServletResponse resp)
            throws Exception {
        String objJson = (req.getParameter("bean"));
        System.out.println("=========" + objJson);

        ResourceBean resourceBean = (ResourceBean) JacksonUtil.jsonToObj(objJson,
                ResourceBean.class);
        resourceService.updateResource(resourceBean);
        return new BackJsonBean().toJson();

    }

    @RequestMapping("/resourceAjax/delete.action")
    public String delete(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String id = req.getParameter("id");
        ResourceBean ResourceBean = new ResourceBean();
        ResourceBean.setId(id);
        resourceService.deleteResource(ResourceBean);
        return new BackJsonBean().toJson();
    }


}
