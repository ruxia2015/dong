package com.dong.sitserver.controller;

import com.dong.sitserver.common.util.JacksonUtil;
import com.dong.sitserver.common.util.StringTools;
import com.dong.sitserver.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by rxia on 2015/9/2.
 */

/**
 * 类别
 */
@Controller
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping("/category/list.action")
    public ModelAndView init() {
        ModelAndView mv = new ModelAndView("/queryList");
        mv.addObject("userJs", "category.js");
        return mv;

    }


}
