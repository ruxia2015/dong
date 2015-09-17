package com.dong.sitserver.fetchMessage;


import com.dong.sitserver.common.annotation.util.FileUtil;
import com.dong.sitserver.util.FetchDataUtil;
import org.apache.commons.lang.ArrayUtils;
import org.apdplat.search.baiduSearcher.impl.JSoupBaiduSearcher;
import org.apdplat.search.bean.SearchResult;
import org.apdplat.search.Searcher;
import org.apdplat.search.bean.Webpage;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by rxia on 2015/9/16.
 */
public class SearchThread implements Runnable {
    public static List<Webpage> webPageList;
    public static Set<String> emails = new HashSet<String>();
    private final String keywords;
    private String regex;


    public SearchThread(String keywords) {
        this.keywords = keywords;
    }

    public SearchThread(String keywords, String regex) {
        this.keywords = keywords;
        this.regex = regex;
    }

    @Override
    public void run() {
        //搜索地址
        Searcher searcher = new JSoupBaiduSearcher();
        String[] keywordArr = keywords.split(",");

        //抓取文件
        for (String key : keywordArr) {
            SearchResult searchResult = searcher.search(key);
            webPageList = searchResult.getWebpages();
            for (Webpage w : webPageList) {
                if (regex == null || regex == "") {
                    emails.addAll(FetchDataUtil.fetchEmail(w.getUrl()));
                } else {
                    emails.addAll(FetchDataUtil.fectchDataByUrl(regex, w.getUrl()));
                }

            }
            //写入文件

            // String content = emails.toArray().toString();
            String content = ArrayUtils.toString(emails);
            FileUtil.writeToFile("E:\\aa\\", "email.txt", content);

        }


    }
}
