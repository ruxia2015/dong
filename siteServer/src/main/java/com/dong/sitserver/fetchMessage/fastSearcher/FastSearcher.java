package com.dong.sitserver.fetchMessage.fastSearcher;

import com.dong.sitserver.common.util.StringTools;
import com.dong.sitserver.fetchMessage.fastSearcher.impl.YahooFastSearcher;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by rxia on 2015/9/24.
 */
public abstract class FastSearcher {
    static Log logger = LogFactory.getLog(FastSearcher.class);
    //protected String titleCssQuery;
    protected int pageSize = 10;

    protected String charSet = "utf-8";

    public static void main(String[] args) {
        try {

            System.out.print(new YahooFastSearcher().searchSites("中国", 1).size());


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected abstract String getTitleCssQuery();

    protected abstract int getPageSize();

    protected abstract String getUrl(String key, int page);

    public List<String> searchSites(String keyword, int page) throws IOException {

        keyword = URLEncoder.encode(keyword, charSet);
        String url = getUrl(keyword, page);
        logger.info("the request url is --> " + url);
        Document document = Jsoup.connect(url).get();
        Integer totalCnt = getSearchResultCount(document);
        if (totalCnt != null && (page - 1) * getPageSize() > totalCnt) {
            return new ArrayList<String>();
        }
        Elements elements = document.select(getTitleCssQuery());

        Iterator<Element> it = elements.iterator();
        List<String> list = new ArrayList<String>();
        while (it.hasNext()) {
            String temp = it.next().attr("href");
            list.add(temp);
            logger.info("the url is ==>  " + temp);
        }
        return list;

    }

    protected abstract String getTotalString(Document document);

    private Integer getSearchResultCount(Document document) {
        String totalText = getTotalString(document);
        if (totalText == null) {
            return null;
        }
        if (StringTools.isEmptyOrNone(totalText)) {
            return null;
        }
        String regEx = "[^0-9]";
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(totalText);
        totalText = matcher.replaceAll("");
        if (StringTools.isEmptyOrNone(totalText)) {
            return null;
        }
        int total = Integer.parseInt(totalText);
        logger.info("搜索结果数：" + total);
        return total;
    }
}
