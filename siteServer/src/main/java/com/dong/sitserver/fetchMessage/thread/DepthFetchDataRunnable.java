package com.dong.sitserver.fetchMessage.thread;

import com.dong.sitserver.common.util.DomainUtil;
import com.dong.sitserver.common.util.FileUtil;
import com.dong.sitserver.util.FetchDataUtil;
import com.dong.sitserver.util.HttpUtils;
import com.dong.sitserver.util.PropertiesUtil;
import com.dong.sitserver.util.PropertyConstant;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.util.*;

/**
 * Created by rxia on 2015/10/9.
 */
public class DepthFetchDataRunnable implements Runnable {
    //已经查找的地址
    private Set<String> haveFetchLinks = new HashSet<String>();
    private Set<String> allLinks = new HashSet<String>();
    private Set<String> failLinks = new HashSet<String>();

    /**
     * 地址
     */
    private Collection<String> sites;

    private String regex;


    /**
     * 是否只搜索当前域名
     */
    private boolean onlySelfDomain = false;


    public DepthFetchDataRunnable(Collection<String> sites, boolean onlySelfDomain,String regex) {
        this.sites = sites;
        this.onlySelfDomain = onlySelfDomain;
        this.regex = regex;
    }

    public DepthFetchDataRunnable(Collection<String> sites,String regex) {
        this.sites = sites; this.regex = regex;
        this.onlySelfDomain =false;
    }

    @Override
    public void run() {
        depthFetchLinks(onlySelfDomain,sites,regex);

    }


    private void depthFetchLinks(boolean onlySelfDomain, Collection<String> links, String regex) {
        Set<String> newLinks = new HashSet<String>();
        Set<String> datas = new HashSet<String>();

        for (String link : links) {
            try {
                if (!haveFetchLinks.contains(link)) {
                    Map<String, Set<String>> maps = depthFetchSingleSite(onlySelfDomain, link, regex);
                    newLinks.addAll(maps.get("links"));
                    datas.addAll(maps.get("datas"));
                    haveFetchLinks.add(link);
                }
            } catch (Exception e) {

            }
        }

        //写入到文件中
        String filePath = PropertiesUtil.getConfigVaue(PropertyConstant.DEPTH_FETCH_OUTPUT_PATH);
        String linksFileName = filePath + File.separator + "AllLinks_" + Thread.currentThread().getName() + ".txt";
        String dataFileName = filePath + File.separator + "datas_" + Thread.currentThread().getName() + ".txt";

        FileUtil.writeCollectionToFile(dataFileName, datas, true);
        FileUtil.writeCollectionToFile(linksFileName, newLinks, true);


        //进入下一轮
        if(newLinks.size()>0) {
            depthFetchLinks(onlySelfDomain, newLinks, regex);
        }

    }


    private Map<String, Set<String>> depthFetchSingleSite(boolean onlySelfDomain, String site, String regex) {
        Set<String> links = new HashSet<String>();
        String domain = DomainUtil.getDomain(site, true);
        Document doc = HttpUtils.accessUrl(site);
        if (doc == null) {
            return null;
        }
        Elements els = doc.getElementsByTag("a");
        Iterator it = els.iterator();
        while (it.hasNext()) {
            Element e = (Element) it.next();
            String href = e.attr("href");
            href = DomainUtil.merginSite(site, href);

            //不本域名，且只处理本域名的地址，则不处理
            if (!href.contains(domain) && onlySelfDomain) {

            } else {
                //加入地址
                links.add(href);
                //将其主页加入
                links.add(domain);
            }
        }

        Set<String> datas = FetchDataUtil.fetchFromContent(doc.html(), regex);
        Map<String, Set<String>> results = new HashMap<String, Set<String>>();
        results.put("datas", datas);
        results.put("links", links);
        return results;
    }


}
