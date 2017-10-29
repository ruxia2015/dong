package com.dong.sitserver.crawling;

import com.dong.sitserver.common.util.FileUtil.FileUtil;
import com.dong.sitserver.util.HttpHelper;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * 数据抓取入口
 *
 * @author XRX
 * @version [版本号, 2017/10/28]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class CrawlingMain {
    Log log = LogFactory.getLog(CrawlingMain.class);

    private static Set<String> urls = new HashSet<String>();
    private static Set<String> accessedUrls = new HashSet<String>();

    //分页url
    private static Set<String> pageUrl = new HashSet<String>();
    private static Set<String> accessedPageUrl = new HashSet<String>();
    //医院URL
    private static Set<String> hisUrl = new HashSet<String>();
    private static Set<String> accessedHisUrl = new HashSet<String>();

    private static Set<String> email = new HashSet<String>();

    private static String pageUrlPath = "d:\\aa\\info\\pageUrl.txt";
    private static String accessedPageUrlPath = "d:\\aa\\info\\accessedPageUrl.txt";
    private static String hisUrlPath = "d:\\aa\\info\\hisUrl.txt";
    private static String accessedHisPath = "d:\\aa\\info\\accessedHisUrl.txt";

    public static void main(String[] args) {
        //读取数据


        pageUrl = loadUrl(pageUrlPath);
        accessedPageUrl = loadUrl(accessedPageUrlPath);
        hisUrl = loadUrl(hisUrlPath);
        accessedHisUrl = loadUrl(accessedHisPath);


        for (int i = 0; i < 20; i++) {
            System.out.println("线程" + i + "启动");
            new MyThread("线程[" + i + "]").start();
        }


    }


    public static Set<String> loadUrl(String path) {
        Set<String> sets = new HashSet<String>();
        String content = FileUtil.readFile(path);
        if (StringUtils.isNotEmpty(content)) {
            String[] strs = content.split("\n");
            org.apache.commons.collections4.CollectionUtils.addAll(sets, strs);
        }

        return sets;

    }

    public static void fetchEmailAndUrl(String url, String outPath) {

        if (accessedPageUrl.contains(url)) {
            return;
        }
        accessedPageUrl.add(url);
        pageUrl.add(url);
        if (accessedPageUrl.size() % 100 == 0) {
            FileUtil.writeCollectionToFile(accessedPageUrlPath, accessedPageUrl, false);
        }


        Document document = null;
        try {
            document = HttpHelper.accessUrl(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Set<String> site = fetchSite(document);

        FileUtil.writeCollectionToFile(hisUrlPath, site, true);
        hisUrl.addAll(site);

        for (String s : site) {
            Document ds = null;
            try {
                ds = HttpHelper.accessUrl(s);
            } catch (Exception e) {
                e.printStackTrace();
            }
            String con = parseDoc(ds);
            email.add(con);
            FileUtil.writeToFile(outPath, con + "\n\r", true);
            accessedUrls.add(url);

        }
        FileUtil.writeCollectionToFile(accessedHisPath, site, true);

        hisUrl.addAll(site);
        accessedHisUrl.addAll(site);

    }


    public static String parseDoc(Document document) {
        String str = "";
        Elements element = document.getElementsByClass("basicFirst");
        Iterator<org.jsoup.nodes.Element> iterable = element.iterator();
        while (iterable.hasNext()) {
            org.jsoup.nodes.Element temp = iterable.next();
            Elements as = temp.getElementsByTag("a");
            Iterator<org.jsoup.nodes.Element> aIterator = as.iterator();
            while (aIterator.hasNext()) {
                org.jsoup.nodes.Element aEl = aIterator.next();
                str = str + aEl.html() + ", ";
            }
        }
        return str;

    }


    public static Set<String> fetchSite(Document document) {
        Set<String> set = new HashSet<String>();
        if (document == null) {
            return set;
        }

        Elements els = document.getElementsByTag("b");
        Iterator<Element> iterator = els.iterator();
        while (iterator.hasNext()) {
            Element t = iterator.next();
            String tel = t.html();

            tel = tel.replaceAll("-", "");
            if (!tel.matches("[0-9]*")) {
                continue;
            }
            String site = "http://nttbj.itp.ne.jp/" + tel + "/index.html";
            set.add(site);

            System.out.println(site);

        }
        return set;
    }

    static int page = 1;

    public static synchronized int getNext() {
        System.out.println("=================  " + page + "==========================");
        page = page + 10;
        return page - 10;
    }



    static class MyThread extends Thread {
        MyThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            while (true) {
                int i = getNext();
                for (; i <= (i + 100); i++) {
                    String url = "https://itp.ne.jp/genre_dir/dentistry/pg/" + i + "/?num=20";
                    System.out.println(url + "   " + i);
                    fetchEmailAndUrl(url, "D:\\aa\\" + this.getName() + ".txt");

                    if (i >= (250 + 1)) {
                        return;
                    }
                }
                FileUtil.writeCollectionToFile("D:\\aa\\all.txt", email, true);

            }

        }
    }


}
