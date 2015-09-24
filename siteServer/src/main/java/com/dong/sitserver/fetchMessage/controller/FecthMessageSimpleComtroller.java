package com.dong.sitserver.fetchMessage.controller;

import com.dong.sitserver.common.util.StringTools;
import com.dong.sitserver.fetchMessage.thread.FastSearchRunnable;
import com.dong.sitserver.util.PropertiesUtil;
import com.dong.sitserver.util.PropertyConstant;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by rxia on 2015/9/15.
 */

@Controller
public class FecthMessageSimpleComtroller {
    private  Log logger = LogFactory.getLog(getClass());
    @RequestMapping("/fetch/toFetch.action")
    public ModelAndView toSetting() {
        ModelAndView mv = new ModelAndView("fetch/fastFetchSetting");
        return mv;
    }


    @RequestMapping("/fetch/fastStartFetch.action")
    public ModelAndView fetch(@RequestParam(value = "keywords", defaultValue = "") String keywords,
                              @RequestParam(value = "searcherType", defaultValue = "") String searcherType,
                              @RequestParam(value = "dataType", defaultValue = "") String dataType,
                              @RequestParam(value = "regex", defaultValue = "") String regex) {

        if (StringTools.isEmptyOrNone(regex) || "1".equals(dataType)) {
            regex = PropertiesUtil.getVaue(PropertyConstant.FILE_PATH_CONFIG, PropertyConstant.REGEX_EMAIL);
        }

        if("all".equals(searcherType)){
            String[] searchers = new String[]{"yahoo","yahoojp","bing","google","baidu"};
            for(String s:searchers){
                logger.info("启动【"+s+"】的搜索线程!");
                new Thread(new FastSearchRunnable(keywords,regex,s)).start();
            }
        }else{
            FastSearchRunnable fastSearchRunnable = new FastSearchRunnable(keywords,regex,searcherType);
            new Thread(fastSearchRunnable).start();
        }


        ModelAndView mv = new ModelAndView("fetch/fastFetchSetting");
        mv.addObject("keywords", keywords);
        mv.addObject("searcherType", searcherType);
        mv.addObject("dataType", dataType);
        mv.addObject("regex", regex);
        mv.addObject("genPath", PropertiesUtil.getVaue(PropertyConstant.FILE_PATH_CONFIG, PropertyConstant.FETCH_OUTPUT_PATH));
        return mv;
    }

}
