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

    public static List<String> match(String source, String element, String attr) {
        List<String> result = new ArrayList<String>();
        String reg = "([a-zA-Z0-9_-])+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+";
        Matcher m = Pattern.compile(reg).matcher(source);
        while (m.find()) {
            String r = m.group();
            result.add(r);
        }
        return result;
    }

    public static void main(String[] args) {
        String source="   <p class=\"r_option\"> <a href=\"javascript:;\" onclick=\"window.scrollTo(0,0);\" id=\"a_top\" title=\"TOP\"><img src=\"image/top.gif\" alt=\"\" style=\"padding: 5px 6px 6px;\"></a> </p> \n" +
                "  <p> OPEN开源家园 - <a href=\"mailto:webadmin@open-open.com\">联系我们</a> - <a href=\"http://www.miibeian.gov.cn\" target=\"_blank\">闽ICP备10022058号</a></p> ";
        List<String> list = match(source, "a", "title");
        System.out.println(list);
    }


    public static void main2(String[] args){
        String content=" <li><a href= mailto: admin@bootcss.com >电子邮件</a></li>";
        String regex = "[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\\\.[a-zA-Z0-9_-]+)+";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(content);

        while (m.find()) {
            String g = m.group();
           System.out.println(g);
        }
    }


}
