package com.dong.sitserver.controller;

import com.dong.sitserver.bean.ResourceBean;
import com.dong.sitserver.bean.ResourceTgBean;
import com.dong.sitserver.common.util.JacksonUtil;
import com.dong.sitserver.common.util.StringTools;
import com.dong.sitserver.service.ResourceTgService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

/**
 * Created by rxia on 2015/9/7.
 */

@Controller
@RequestMapping("/resourceTgAjax")
public class ResourceTgAjaxController {

    @Resource
    private ResourceTgService resourceTgService;


    @RequestMapping("/list.action")
    protected void execute(HttpServletRequest req, HttpServletResponse resp) {

        ResourceBean resourceBean = new ResourceBean();
        String tgId = req.getParameter("tgId");
        Collection<ResourceTgBean> beans = resourceTgService.queryResourceTgList(tgId,
                resourceBean);

        try {
            req.setAttribute("json", JacksonUtil.objToJson(beans));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @RequestMapping("/add.action")
    public void add(HttpServletRequest req, HttpServletResponse resp)
            throws Exception {
        String objJson = (req.getParameter("bean"));

        System.out.println("bean==>" + objJson);

        ResourceTgBean bean = (ResourceTgBean) JacksonUtil.jsonToObj(objJson,
                ResourceTgBean.class);

        String tgId = req.getParameter("tgId");
        bean.setTgId(tgId);

        if (StringTools.isEmptyOrNone(bean.getId())) {
            resourceTgService.addResourceTg(bean);
        } else {
            resourceTgService.updateResourceTg(bean);
        }

    }

    @RequestMapping("/update.action")
    public void update(HttpServletRequest req, HttpServletResponse resp)
            throws Exception {
        add(req, resp);

    }

    @RequestMapping("/delete.action")
    public void delete(HttpServletRequest req, HttpServletResponse resp) {
        String id = req.getParameter("id");

        ResourceTgBean ResourceTgBean = new ResourceTgBean();
        ResourceTgBean.setId(id);
        resourceTgService.deleteResourceTg(ResourceTgBean);
    }
}
