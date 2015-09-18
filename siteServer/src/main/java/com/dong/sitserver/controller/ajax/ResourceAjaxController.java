package com.dong.sitserver.controller.ajax;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by rxia on 2015/9/7.
 */

@Controller
public class ResourceAjaxController {

    @Resource
    private CategoryService categoryService;

    @Resource
    private ResourceService resourceService;

    @Autowired
    private ResourceTypeService resourceTypeService;


    @ResponseBody
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

    @ResponseBody
    @RequestMapping("/resourceAjax/add.action")
    public String addAjax(HttpServletRequest req, HttpServletResponse resp)
            throws Exception {
        String objJson = (req.getParameter("bean"));
        ResourceBean resourceBean = (ResourceBean) JacksonUtil.jsonToObj(objJson,
                ResourceBean.class);
        resourceService.addResource(resourceBean);

        return new BackJsonBean().toJson();

    }

    @ResponseBody
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

    @ResponseBody
    @RequestMapping("/resourceAjax/delete.action")
    public String delete(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String id = req.getParameter("id");
        ResourceBean ResourceBean = new ResourceBean();
        ResourceBean.setId(id);
        resourceService.deleteResource(ResourceBean);
        return new BackJsonBean().toJson();
    }


}
