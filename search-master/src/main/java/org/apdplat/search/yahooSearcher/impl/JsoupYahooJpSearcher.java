package org.apdplat.search.yahooSearcher.impl;

import org.apdplat.search.Searcher;
import org.apdplat.search.bean.SearchResult;
import org.apdplat.search.bean.Webpage;
import org.apdplat.search.tools.Tools;
import org.apdplat.search.yahooSearcher.YahooSearcher;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by rxia on 2015/9/23.
 */
public class JsoupYahooJpSearcher implements YahooSearcher {
    private static final Logger LOG = LoggerFactory.getLogger(JsoupYahooJpSearcher.class);
    private String yahooUrl = "https://search.yahoo.com/search;_ylt=A2KK_KrYYgJWC08B8bObvZx4?p=emia&toggle=1&cop=mss&ei=UTF-8&fr=yfp-t-120&fp=1";
    //" https://search.yahoo.com/search?p=emia&ei=UTF-8&fr=yfp-t-120&fp=1&b=11&pz=10&bct=0&xargs=0"

    public static void main(String args[]) {
        String keyword = "杨尚川";
        try {
            keyword = URLEncoder.encode(keyword, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            LOG.error("url构造失败", e);
            return;
        }

        Searcher searcher = new JsoupYahooJpSearcher();
        SearchResult searchResult = searcher.search(keyword, 1);
        List<Webpage> webpages = searchResult.getWebpages();
        if (webpages != null) {
            int i = 1;
            LOG.info("搜索结果 当前第 " + searchResult.getPage() + " 页，页面大小为：" + searchResult.getPageSize() + " 共有结果数：" + searchResult.getTotal());
            for (Webpage webpage : webpages) {
                LOG.info("搜索结果 " + (i++) + " ：");
                LOG.info("标题：" + webpage.getTitle());
                LOG.info("URL：" + webpage.getUrl());
                LOG.info("摘要：" + webpage.getSummary());
                LOG.info("正文：" + webpage.getContent());
                LOG.info("");
            }
        } else {
            LOG.error("没有搜索到结果");
        }
    }

    @Override
    public SearchResult search(String keyword) {
        return search(keyword, 1);
    }

    @Override
    public SearchResult search(String keyword, int page) {
        int pageSize = 10;


        //百度搜索结果每页大小为10，pn参数代表的不是页数，而是返回结果的开始数
        //如获取第一页则pn=0，第二页则pn=10，第三页则pn=20，以此类推，抽象出模式：(page-1)*pageSize
        String url = "http://search.yahoo.co.jp/search?p=emia&ei=UTF-8&fr=yfp-t-120&bct=0&xargs=0&b=" + (page - 1) * pageSize + "&p=" + keyword;

        SearchResult searchResult = new SearchResult();
        searchResult.setPage(page);
        List<Webpage> webpages = new ArrayList<>();
        try {
            Document document = Jsoup.connect(url).get();

            //获取搜索结果数目
            int total = getYahooJpSearchResultCount(document);
            searchResult.setTotal(total);
            int len = 10;
            if (total < 1) {
                return null;
            }
            //如果搜索到的结果不足一页
            if (total < 10) {
                len = total;
            }
            for (int i = 0; i < len; i++) {
                String titleCssQuery = "div#web ol li a";
                String summaryCssQuery = "div#web ol li div";
                String hrefCssQuery = "div#web ol li em";
                LOG.debug("titleCssQuery:" + titleCssQuery);
                LOG.debug("summaryCssQuery:" + summaryCssQuery);
                  Elements elements = document.select(titleCssQuery);
                  System.out.print(elements.size());
                Element titleElement = elements.get(i);
                String href = "";
                String titleText = "";
                if (titleElement != null) {
                    titleText = titleElement.text();
                    href = titleElement.attr("href");
                }
                LOG.debug(titleText);
                Element summaryElement = document.select(summaryCssQuery).get(i);

                String summaryText = "";
                if (summaryElement != null) {
                    summaryText = summaryElement.text();
                }
                LOG.debug(summaryText);

                if (titleText != null && !"".equals(titleText.trim()) && summaryText != null && !"".equals(summaryText.trim())) {
                    Webpage webpage = new Webpage();
                    webpage.setTitle(titleText);
                    webpage.setUrl(href);
                    webpage.setSummary(summaryText);
                    if (href != null) {
                        String content = Tools.getHTMLContent(href);
                        webpage.setContent(content);
                    } else {
                        LOG.info("页面正确提取失败");
                    }
                    webpages.add(webpage);
                } else {
                    LOG.error("获取搜索结果列表项出错:" + titleText + " - " + summaryText);
                }
            }


        } catch (IOException ex) {
            LOG.error("搜索出错", ex);
        }
        searchResult.setWebpages(webpages);
        ;
        return searchResult;
    }

    private int getYahooJpSearchResultCount(Document document) {
        String cssQuery = "div#inf";
        LOG.debug("total cssQuery: " + cssQuery);
        Element totalElement = document.select(cssQuery).first();
        if (totalElement == null) {
            return 0;
        }
        String totalText = totalElement.text();
        LOG.info("搜索结果文本：" + totalText);
        if (totalText.indexOf("/") < 0 || totalText.indexOf("-") < 0) {
            return 0;
        }

        totalText = totalText.substring(totalText.indexOf("/"), totalText.indexOf("-"));

        String regEx = "[^0-9]";
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(totalText);
        totalText = matcher.replaceAll("");
        int total = Integer.parseInt(totalText);
        LOG.info("搜索结果数：" + total);
        return total;
    }
}
