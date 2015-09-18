package com.dong.sitserver.controller;

import com.dong.sitserver.bean.WebsiteBean;
import com.dong.sitserver.service.WebsiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by rxia on 2015/9/18.
 */

@Controller
@RequestMapping("tg")
public class TgController {

    @Autowired
    private WebsiteService websiteService;

    @RequestMapping("toTg.action")
    public ModelAndView toTg(){
        List<WebsiteBean> websiteBeanList = websiteService.queryWebsites(null);

        ModelAndView mv = new ModelAndView("/queryTgResourceMain");
        mv.addObject("websiteBeanList",websiteBeanList);
        return mv;

    }

}
