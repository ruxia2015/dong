package com.dong.sitserver.controller;

import com.dong.sitserver.bean.ResourceTypeBean;
import com.dong.sitserver.common.BackJsonBean;
import com.dong.sitserver.common.annotation.util.JacksonUtil;
import com.dong.sitserver.common.annotation.util.StringTools;
import com.dong.sitserver.service.ResourceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by rxia on 2015/9/7.
 */
@Controller
public class ResourceTypeController {


    @Autowired
    private ResourceTypeService resourceTypeService;

    @RequestMapping("/resourceType/list.action")
    public ModelAndView init() {
        ModelAndView mv = new ModelAndView("/queryList");
        mv.addObject("userJs", "resourceType.js");
        return mv;

    }

    @RequestMapping("/resourceTypeAjax/list.action")
    @ResponseBody
    protected Object list(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        String name = (String) req.getParameter("name");
        String remark = (String) req.getParameter("remark");
        ResourceTypeBean bean = new ResourceTypeBean();
        bean.setName(name);
        bean.setRemark(remark);
        List<ResourceTypeBean> beans = resourceTypeService.queryResourceTypes(bean);

        return JacksonUtil.objToJson(beans);

    }


    @RequestMapping("/resourceTypeAjax/add.action")
    @ResponseBody
    public Object add(HttpServletRequest req, HttpServletResponse resp) throws Exception {


        String objJson = (req.getParameter("bean"));
        if (StringTools.isEmptyOrNone(objJson)) {
            return new BackJsonBean("无数据，不能添加").toJson();
        }

        ResourceTypeBean categoryBean = (ResourceTypeBean) JacksonUtil.jsonToObj(objJson,
                ResourceTypeBean.class);
        //判断是否可以添加
        ResourceTypeBean beanQ = new ResourceTypeBean();
        beanQ.setName(categoryBean.getName());
        beanQ = resourceTypeService.findResourceType(beanQ);
        if (beanQ != null) {
            return new BackJsonBean("已经存在该资源类型，添加失败").toJson();
        }

        //添加
        resourceTypeService.addResourceType(categoryBean);

        return new BackJsonBean().toJson();


    }

    @RequestMapping("/resourceTypeAjax/update.action")
    @ResponseBody
    public Object update(HttpServletRequest req, HttpServletResponse resp)
            throws Exception {
        String objJson = (req.getParameter("bean"));
        ResourceTypeBean categoryBean = (ResourceTypeBean) JacksonUtil.jsonToObj(objJson,
                ResourceTypeBean.class);
        resourceTypeService.updateResourceType(categoryBean);
        return new BackJsonBean().toJson();
    }

    @RequestMapping("/resourceTypeAjax/delete.action")
    @ResponseBody
    public Object delete(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String id = req.getParameter("id");

        ResourceTypeBean categoryBean = new ResourceTypeBean();
        categoryBean.setId(id);
        resourceTypeService.deleteResourceType(categoryBean);
        return new BackJsonBean().toJson();
    }
}
