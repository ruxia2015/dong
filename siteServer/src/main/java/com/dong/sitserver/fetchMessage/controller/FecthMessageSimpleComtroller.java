package com.dong.sitserver.fetchMessage.controller;

import com.dong.sitserver.common.ServiceException;
import com.dong.sitserver.common.util.FileUtil;
import com.dong.sitserver.common.util.StringTools;
import com.dong.sitserver.fetchMessage.thread.FastSearchRunnable;
import com.dong.sitserver.util.FetchDataUtil;
import com.dong.sitserver.util.PropertiesUtil;
import com.dong.sitserver.util.PropertyConstant;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by rxia on 2015/9/15.
 */

@Controller
public class FecthMessageSimpleComtroller {
    private Log logger = LogFactory.getLog(getClass());

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

        if ("all".equals(searcherType)) {
            String[] searchers = new String[]{"yahoo", "yahoojp", "bing", "google", "baidu"};
            for (String s : searchers) {
                logger.info("启动【" + s + "】的搜索线程!");
                new Thread(new FastSearchRunnable(keywords, regex, s)).start();
            }
        } else {
            FastSearchRunnable fastSearchRunnable = new FastSearchRunnable(keywords, regex, searcherType);
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


    @RequestMapping("fetch/toFetchFromSite")
    public ModelAndView toFetchFromSite() {
        ModelAndView mv = new ModelAndView("fetch/fetchFromSite");
        return mv;
    }

    @RequestMapping("fetch/fetchFromSite")
    public ModelAndView fetch(@RequestParam(value = "sitePages", defaultValue = "") String sitePages,
                              @RequestParam(value = "type", defaultValue = "") String type,
                              @RequestParam(value = "regex", defaultValue = "") String regex) {

        if (StringTools.isEmptyOrNone(regex)) {
            regex = PropertiesUtil.getConfigVaue(PropertyConstant.REGEX_EMAIL);
        }

        Set<String> fails = new HashSet<String>();
        Set<String> emails = new HashSet<String>();

        if ("http".equals(type)) {
            String[] siteArr = sitePages.split("\r|\n");
            for (String str : siteArr) {
                try {
                    emails.addAll(FetchDataUtil.fectchDataByUrl(regex, str));
                } catch (ServiceException e) {
                    logger.error(e);
                    fails.add(str);
                }
            }
        } else if ("str".equals(type)) {
            emails = FetchDataUtil.fetchFromContent(sitePages, regex);

        } else if ("file".equals(type)) {
            String[] siteArr = sitePages.split("\r|\n");
            for (String temp : siteArr) {
                try {
                    String content = FileUtil.readFile(temp);
                    emails.addAll(FetchDataUtil.fetchFromContent(content, regex));
                } catch (ServiceException e) {
                    logger.error(e);
                    fails.add(temp);
                }
            }
        }

        ModelAndView mv = new ModelAndView("fetch/fetchFromSite");
        mv.addObject("sitePages", sitePages);
        mv.addObject("type", type);
        mv.addObject("emails", emails);
        mv.addObject("fails", fails);
        return mv;
    }





    @RequestMapping("/fetch/toDepthFetchFromSite.action")
    public ModelAndView toDepthFetchFromSite() {
        ModelAndView mv = new ModelAndView("fetch/depthFetchFromSite");
        return mv;
    }


    @RequestMapping("fetch/depthFetchFromSite.action")
    public ModelAndView depthFetchFromSite(@RequestParam(value = "sitePages", defaultValue = "") String sitePages,
                              @RequestParam(value = "type", defaultValue = "") String type,
                              @RequestParam(value = "regex", defaultValue = "") String regex,
                              @RequestParam(value = "onlySelfDomain",defaultValue="")String onlySelfDomain) {

        if (StringTools.isEmptyOrNone(regex)) {
            regex = PropertiesUtil.getConfigVaue(PropertyConstant.REGEX_EMAIL);
        }

        Set<String> fails = new HashSet<String>();
        Set<String> emails = new HashSet<String>();

        if ("http".equals(type)) {
            String[] siteArr = sitePages.split("\r|\n");
            for (String str : siteArr) {
                try {
                    emails.addAll(FetchDataUtil.fectchDataByUrl(regex, str));
                } catch (ServiceException e) {
                    logger.error(e);
                    fails.add(str);
                }
            }
        } else if ("str".equals(type)) {
            emails = FetchDataUtil.fetchFromContent(sitePages, regex);

        } else if ("file".equals(type)) {
            String[] siteArr = sitePages.split("\r|\n");
            for (String temp : siteArr) {
                try {
                    String content = FileUtil.readFile(temp);
                    emails.addAll(FetchDataUtil.fetchFromContent(content, regex));
                } catch (ServiceException e) {
                    logger.error(e);
                    fails.add(temp);
                }
            }
        }

        ModelAndView mv = new ModelAndView("fetch/depthFetchFromSite");
        mv.addObject("sitePages", sitePages);
        mv.addObject("type", type);
        mv.addObject("emails", emails);
        mv.addObject("fails", fails);
        return mv;
    }

}
