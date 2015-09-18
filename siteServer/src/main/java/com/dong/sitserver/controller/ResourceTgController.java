package com.dong.sitserver.controller;

import com.dong.sitserver.bean.CategoryBean;
import com.dong.sitserver.service.CategoryService;
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
@RequestMapping("/resourceTg")
public class ResourceTgController {

    @Autowired
    private CategoryService categoryService;



    @RequestMapping("/list.action")
    public ModelAndView init() {
        ModelAndView mv = new ModelAndView("/queryList");
        mv.addObject("userJs", "resourceTg.js");
        return mv;
    }






}
