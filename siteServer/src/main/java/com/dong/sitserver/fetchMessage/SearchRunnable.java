package com.dong.sitserver.fetchMessage;


import com.dong.sitserver.common.util.FileUtil.FileUtil;
import com.dong.sitserver.util.FetchDataUtil;
import com.dong.sitserver.util.PropertiesUtil;
import com.dong.sitserver.util.PropertyConstant;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apdplat.search.Searcher;
import org.apdplat.search.baiduSearcher.impl.JSoupBaiduSearcher;
import org.apdplat.search.bean.SearchResult;
import org.apdplat.search.bean.Webpage;
import org.apdplat.search.googleSearcher.impl.GoogleAjaxSearcher;
import org.apdplat.search.yahooSearcher.impl.JsoupYahooJpSearcher;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

/**
 * Created by rxia on 2015/9/16.
 */
public class SearchRunnable implements Runnable {
    static   Log  logger = LogFactory.getLog(SearchRunnable.class);
    public static List<Webpage> webPageList;
    public static Set<String> emails = new HashSet<String>();
    private final String keywords;
    private String regex;
    private String searcherType;


    public SearchRunnable(String keywords) {
        this.keywords = keywords;
    }

    public SearchRunnable(String keywords, String regex, String searcherType) {
        this.keywords = keywords;
        this.regex = regex;
        this.searcherType = searcherType;
    }

    private static Map<String, Set<String>> fetchContent(String url, String regex, Integer times) {
        if(url.indexOf("www.treasure-f.com")>0){
            logger.info("=========>"+url);
        }
            if (times == null) {
            times = 0;
        }
        if (times > 10) {
            return null;
        }
        Set<String> contents = FetchDataUtil.fectchDataByUrl(regex, url);

        if(contents!=null && contents.size()>0){
            String str = url + "\n\r" +ArrayUtils.toString(contents);
            FileUtil.writeToFile(PropertiesUtil.getConfigVaue( PropertyConstant.FETCH_OUTPUT_PATH) +  "\\count_email_" + System.currentTimeMillis() + ".txt",str,true);

        }


        Map<String, Set<String>> result = new HashMap<String, Set<String>>();
        if (contents != null) {
            result.put(url, contents);
        }
        Set<String> urls = new HashSet<String>();
        urls.add(url);

        Set<String> new_urls = FetchDataUtil.getUrls(url);
         times = times +1;
        for (String str : new_urls) {
            result.putAll(fetchContent(str, regex, times));
            urls.add(url);
        }




        return result;
    }

    @Override
    public void run() {
        //搜索地址
        Searcher searcher = null;

        if ("baidu".equals(searcherType)) {
            searcher = new JSoupBaiduSearcher();
        } else if ("yahoojp".equals(searcherType)) {
            searcher = new JsoupYahooJpSearcher();
        } else if ("google".equals(searcherType)) {
            searcher = new GoogleAjaxSearcher();
        } else {
            return;
        }

        String[] keywordArr = keywords.split(",");
        Map<String, Set<String>> result = new HashMap<String, Set<String>>();

        //抓取文件
        for (String key : keywordArr) {
            try {
                key = URLEncoder.encode(key,"UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                logger.error(e);
            }
            int pagesize = 0;
            while(true) {
                pagesize ++;

                SearchResult searchResult = searcher.search(key,pagesize);
                if(searchResult==null || searchResult.getWebpages().size()==0){
                    break;
                }
                logger.info("the current pagesize is " +pagesize +".<<<<<  the count page is " + searchResult.getPageSize());

                webPageList = searchResult.getWebpages();
                for (Webpage w : webPageList) {
                    logger.info("**********************" + w.getUrl());
                    if (regex == null || regex == "") {
                        regex = PropertiesUtil.getVaue(PropertyConstant.FILE_PATH_CONFIG, PropertyConstant.REGEX_EMAIL);
                    }
//                emails.addAll(FetchDataUtil.fectchDataByUrl(regex, w.getUrl()));
//                emails.addAll(FetchDataUtil.fetchFromContent(w.getSummary(), regex));
                    Map<String, Set<String>> urls_temp = fetchContent(w.getUrl(), regex, null);

                    if (urls_temp != null) {
                        result.putAll(urls_temp);
                    }
                }
            }



            StringBuffer sb = new StringBuffer();
            Set<Map.Entry<String, Set<String>>> maps = result.entrySet();
            Iterator<Map.Entry<String, Set<String>>> it = maps.iterator();
            while (it.hasNext()) {
                Map.Entry<String, Set<String>> entry = it.next();

                sb.append(entry.getKey() + "\n\r");

                for (String str : entry.getValue()) {
                    sb.append("\t\t    " + entry.getKey() + "\n\r");
                }


            }

            FileUtil.writeToFile(PropertiesUtil.getVaue(PropertyConstant.FILE_PATH_CONFIG, PropertyConstant.FETCH_OUTPUT_PATH)+ "\\email_" + System.currentTimeMillis() + ".txt", sb.toString(),true);

        }
    }


}
