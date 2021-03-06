package com.dong.sitserver.fetchMessage.controller;

import com.dong.sitserver.common.util.StringTools;
import com.dong.sitserver.fetchMessage.SearchRunnable;
import com.dong.sitserver.util.PropertiesUtil;
import com.dong.sitserver.util.PropertyConstant;
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
        ModelAndView mv = new ModelAndView("fetch/fetchSetting");
        return mv;
    }


    @RequestMapping("/fetch/startFetch.action")
    public ModelAndView fetch(@RequestParam(value = "keywords", defaultValue = "") String keywords,
                              @RequestParam(value = "searcherType", defaultValue = "") String searcherType,
                              @RequestParam(value = "dataType", defaultValue = "") String dataType,
                              @RequestParam(value = "regex", defaultValue = "") String regex) {

        if (StringTools.isEmptyOrNone(regex) || "1".equals(dataType)) {
            regex = PropertiesUtil.getVaue(PropertyConstant.FILE_PATH_CONFIG, PropertyConstant.REGEX_EMAIL);
        }

        SearchRunnable searchThread = new SearchRunnable(keywords, regex, searcherType);
//        searchThread.run();

        Thread th = new Thread(searchThread);
        th.run();
        ;

        ModelAndView mv = new ModelAndView("fetch/fetchSetting");
        mv.addObject("keywords", keywords);
        mv.addObject("searcherType", searcherType);
        mv.addObject("dataType", dataType);
        mv.addObject("regex", regex);
        mv.addObject("genPath", PropertiesUtil.getVaue(PropertyConstant.FILE_PATH_CONFIG, PropertyConstant.FETCH_OUTPUT_PATH));
        return mv;
    }

}
