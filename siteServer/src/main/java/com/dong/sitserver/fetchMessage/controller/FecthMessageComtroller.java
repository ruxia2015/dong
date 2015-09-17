package com.dong.sitserver.fetchMessage.controller;

import com.dong.sitserver.fetchMessage.SearchThread;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by rxia on 2015/9/15.
 */

@Controller
public class FecthMessageComtroller {

    @RequestMapping("/fetch/toSetting.action")
    public ModelAndView toSetting() {
        ModelAndView mv = new ModelAndView("/fetch/setting");
        return mv;
    }


    @RequestMapping("/fetch/startFetch.action")
    public ModelAndView fetch(@RequestParam(value = "keywords",defaultValue = "") String keywords,
                              @RequestParam(value = "searchType",defaultValue ="1" ) String searchType,
                              @RequestParam(value = "regex",defaultValue = "") String regex) {

        SearchThread searchThread = new SearchThread(keywords,regex);
        searchThread.run();


        return new ModelAndView("/fetch/setting");
    }

}
