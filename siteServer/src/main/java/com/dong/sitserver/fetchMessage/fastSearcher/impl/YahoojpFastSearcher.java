package com.dong.sitserver.fetchMessage.fastSearcher.impl;

import com.dong.sitserver.fetchMessage.fastSearcher.FastSearcher;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by rxia on 2015/9/24.
 */
public class YahoojpFastSearcher extends FastSearcher {
    private static Log logger = LogFactory.getLog(YahoojpFastSearcher.class);


    @Override
    protected String getTitleCssQuery() {
        return "div#web ol li a";
    }

    @Override
    protected int getPageSize() {
        return 10;
    }

    @Override
    protected String getUrl(String keyword, int page) {
        String url = "http://search.yahoo.co.jp/search?p=emia&ei=UTF-8&fr=yfp-t-120&bct=0&xargs=0&b=" +( (page - 1) * pageSize +1) + "&p=" + keyword;
        return url;
    }

    @Override
    protected String getTotalString(Document document) {
        String cssQuery = "div#inf";
        logger.debug("total cssQuery: " + cssQuery);
        Element totalElement = document.select(cssQuery).first();
        if (totalElement == null) {
            return null;
        }
        String totalText = totalElement.text();
        logger.info("搜索结果文本：" + totalText);
        int totalIndex = totalText.indexOf("/");
        int totalEnd = totalText.indexOf("-", totalIndex);
        if (totalIndex <= 0 || totalEnd <= 0) {
            return null;

        }

        totalText = totalText.substring(totalIndex, totalEnd);
        return totalText;
    }
}
