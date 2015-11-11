package com.dong.sitserver.fetchMessage.thread;


import com.dong.sitserver.common.util.DateUtil;
import com.dong.sitserver.common.util.FileUtil.FileUtil;
import com.dong.sitserver.fetchMessage.fastSearcher.DataFetcher;
import com.dong.sitserver.fetchMessage.fastSearcher.FastSearcher;
import com.dong.sitserver.fetchMessage.fastSearcher.impl.BingFastSearcher;
import com.dong.sitserver.fetchMessage.fastSearcher.impl.YahooFastSearcher;
import com.dong.sitserver.fetchMessage.fastSearcher.impl.YahoojpFastSearcher;
import com.dong.sitserver.util.PropertiesUtil;
import com.dong.sitserver.util.PropertyConstant;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apdplat.search.bean.Webpage;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by rxia on 2015/9/16.
 */
public class FastSearchRunnable implements Runnable {
    public static List<Webpage> webPageList;
    public static Set<String> emails = new HashSet<String>();
    static Log logger = LogFactory.getLog(FastSearchRunnable.class);
    private String keywords;
    private String regex;
    private String searcherType;


    public FastSearchRunnable(String keywords) {
        this.keywords = keywords;
    }

    public FastSearchRunnable(String keywords, String regex, String searcherType) {
        this.keywords = keywords;
        this.regex = regex;
        this.searcherType = searcherType;
    }


    @Override
    public void run() {
        logger.info("=================查询===================");
        FastSearcher fastSearcher = new YahoojpFastSearcher();

        if ("bing".equals(searcherType)) {
            fastSearcher = new BingFastSearcher();
        } else if ("yahoojp".equals(searcherType)) {
            fastSearcher = new YahoojpFastSearcher();
        } else if ("google".equals(searcherType)) {
            //  fastSearcher = new GoogleAjaxSearcher();
        } else if ("yahoo".equals(searcherType)) {
            fastSearcher = new YahooFastSearcher();
        } else {
            return;
        }

        String[] keys = keywords.split(",\n");
        for(String keyword:keys){
            int page = 0;
            while (page < 11) {
                try {
                    List<String> sites = fastSearcher.searchSites(keyword, ++page);
                    if (sites == null || sites.size() == 0) {
                        break;
                    }
                    fetchdatasTofile(sites, searcherType);
                    try {
                        Thread.sleep(1000l);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }




    }

    /**
     * 写数据到文件
     *
     *
     *
     * @param sites
     */
    private void fetchdatasTofile(List<String> sites, String searcherType) {
        for (String site : sites) {
            Map<Integer, List<String>> datas = new DataFetcher().getDatas(site, PropertiesUtil.getConfigVaue(PropertyConstant.REGEX_EMAIL), PropertiesUtil.getConfigVaue(PropertyConstant.REGEX_HTTP));
            if (datas == null || datas.size() == 0) {
                continue;
            }
            List<String> emailList = datas.get(1);

            if (emailList != null && emailList.size() > 0) {
                logger.info("找到数据！  " + site);
                String content = site + "\r\n";
                for (String email : emailList) {
                    content = content + "\t\t" + email;

                }
                String filePath = PropertiesUtil.getConfigVaue(PropertyConstant.FETCH_OUTPUT_PATH) + "/" + searcherType + "_" + DateUtil.nowDate("yyyyMMdd");
                FileUtil.writeToFile(filePath +"\\"+ searcherType + "_email_" + System.currentTimeMillis() + ".txt", content,false);
            } else {
                logger.info("没有数据！  " + site);
            }

            List<String> urlList = datas.get(2);
            if (urlList != null && urlList.size() > 0) {
                fetchdatasTofile(urlList,searcherType);
            }
        }
    }
}
