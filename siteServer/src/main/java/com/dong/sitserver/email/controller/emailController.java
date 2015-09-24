package com.dong.sitserver.email.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by rxia on 2015/9/24.
 */
@Controller
public class emailController {

    @RequestMapping("email/toSendMail.action")
    public ModelAndView toSendMail(){
        ModelAndView mv = new ModelAndView("email/sendEmail");
        return mv;
    }


}
