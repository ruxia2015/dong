package com.dong.sitserver.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by rxia on 2015/9/7.
 */

@Controller
@RequestMapping("/resourceTg")
public class ResourceTgController {


    @RequestMapping("/list.action")
    public ModelAndView init() {
        ModelAndView mv = new ModelAndView("/queryList");
        mv.addObject("userJs", "resourceTg.js");
        return mv;

    }


}
