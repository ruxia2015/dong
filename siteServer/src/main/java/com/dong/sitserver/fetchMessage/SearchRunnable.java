package com.dong.sitserver.fetchMessage;


import com.dong.sitserver.common.util.FileUtil;
import com.dong.sitserver.util.FetchDataUtil;
import com.dong.sitserver.util.PropertiesUtil;
import com.dong.sitserver.util.PropertyConstant;
import org.apdplat.search.Searcher;
import org.apdplat.search.baiduSearcher.impl.JSoupBaiduSearcher;
import org.apdplat.search.bean.SearchResult;
import org.apdplat.search.bean.Webpage;

import java.util.*;

/**
 * Created by rxia on 2015/9/16.
 */
public class SearchRunnable implements Runnable {
    public static List<Webpage> webPageList;
    public static Set<String> emails = new HashSet<String>();
    private final String keywords;
    private String regex;


    public SearchRunnable(String keywords) {
        this.keywords = keywords;
    }

    public SearchRunnable(String keywords, String regex) {
        this.keywords = keywords;
        this.regex = regex;
    }

    private static Map<String, Set<String>> fetchContent(String url, String regex, Integer times) {
        if (times == null) {
            times = 0;
        }
        if (times > 10) {
            return null;
        }
        Set<String> contents = FetchDataUtil.fectchDataByUrl(regex, url);
        Map<String, Set<String>> result = new HashMap<String, Set<String>>();
        if (contents != null) {
            result.put(url, contents);
        }
        Set<String> urls = new HashSet<String>();
        urls.add(url);

        Set<String> new_urls = FetchDataUtil.getUrls(url);
        for (String str : new_urls) {
            result.putAll(fetchContent(url, regex, times++));
            urls.add(url);
        }


        return null;
    }

    @Override
    public void run() {
        //搜索地址
        Searcher searcher = new JSoupBaiduSearcher();
        String[] keywordArr = keywords.split(",");
        Map<String, Set<String>> result = new HashMap<String, Set<String>>();

        //抓取文件
        for (String key : keywordArr) {
            SearchResult searchResult = searcher.search(key);
            webPageList = searchResult.getWebpages();
            for (Webpage w : webPageList) {
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

            StringBuffer sb = new StringBuffer();
            Set<Map.Entry<String, Set<String>>> maps = result.entrySet();
            Iterator<Map.Entry<String, Set<String>>> it = maps.iterator();
            while (it.hasNext()) {
                Map.Entry<String, Set<String>> entry = it.next();

                sb.append(entry.getKey() + "\n\r");

                for (String str : entry.getValue()) {
                    sb.append("\t\t" + entry.getKey() + "\n\r");
                }


            }


            //写入文件
            // String content = emails.toArray().toString();
            //String content = ArrayUtils.toString(emails,"\n");
            FileUtil.writeToFile(PropertiesUtil.getVaue(PropertyConstant.FILE_PATH_CONFIG, PropertyConstant.FETCH_OUTPUT_PATH), "email_" + System.currentTimeMillis() + ".txt", sb.toString());

        }
    }


}
