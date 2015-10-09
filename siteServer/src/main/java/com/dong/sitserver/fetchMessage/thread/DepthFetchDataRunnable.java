package com.dong.sitserver.fetchMessage.thread;

import com.dong.sitserver.util.HttpUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by rxia on 2015/10/9.
 */
public class DepthFetchDataRunnable implements Runnable {

    /**
     * 地址
     */
    private Set<String> sites;
    /**
     * 是否只搜索当前域名
     */
    private boolean onlySelfDomain = false;


    public DepthFetchDataRunnable(Set<String> sites, boolean onlySelfDomain) {
        this.sites = sites;
        this.onlySelfDomain = onlySelfDomain;
    }

    public DepthFetchDataRunnable(Set<String> sites) {
        this.sites = sites;
    }

    @Override
    public void run() {

    }


    private Map<String, Set<String>> depthFetchSingleSite(String site, boolean onlySelfDomain) {
        Document doc = HttpUtils.accessUrl(site);
        if (doc == null) {
            return null;
        }
        Elements els = doc.getElementsByTag("a");
        Iterator it = els.iterator();
        while (it.hasNext()) {
            Element e = (Element) it.next();

            String content = e.attr("href");

        }
        return null;

    }



}
