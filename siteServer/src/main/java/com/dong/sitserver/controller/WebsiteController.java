package com.dong.sitserver.controller;

import com.dong.sitserver.bean.CategoryBean;
import com.dong.sitserver.bean.WebsiteBean;
import com.dong.sitserver.common.util.JacksonUtil;
import com.dong.sitserver.common.util.StringTools;
import com.dong.sitserver.service.CategoryService;
import com.dong.sitserver.service.WebsiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by rxia on 2015/9/7.
 */

@Controller
@RequestMapping("website")
public class WebsiteController {
    @Autowired
    private WebsiteService websiteService;

    @Autowired
    private CategoryService categoryService;

    @RequestMapping("/list.action")
    public ModelAndView init() {
        ModelAndView mv = new ModelAndView("/queryList");
        mv.addObject("userJs", "website.js");
        return mv;
    }

    @RequestMapping("/toAdd.action")
    public ModelAndView toAdd() {
        List<CategoryBean> categoryBeanList = categoryService.queryCategorys(null);
        ModelAndView mv = new ModelAndView("/addWebsite");
        mv.addObject("categoryList", categoryBeanList);
        return mv;
    }

    @RequestMapping("/add.action")
    public ModelAndView add(@RequestParam("categoryId")String categoryId,@RequestParam("sites")String sites) {

        String[] sitArr = sites.split("\n");

        for(String str:sitArr){
            WebsiteBean websiteBean = new WebsiteBean();
            str = str.trim();
            //str.replace("\r","");
            websiteBean.setDomain(str);
            websiteBean.setCategoryId(categoryId);
            websiteService.addWebsite(websiteBean);
        }


        ModelAndView mv = new ModelAndView("/success");
        return mv;
    }


}
