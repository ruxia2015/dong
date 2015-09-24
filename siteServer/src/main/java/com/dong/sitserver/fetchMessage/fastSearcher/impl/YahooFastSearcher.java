package com.dong.sitserver.fetchMessage.fastSearcher.impl;

import com.dong.sitserver.fetchMessage.fastSearcher.FastSearcher;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.nodes.Document;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by rxia on 2015/9/24.
 */

/**
 * 雅虎
 */
public class YahooFastSearcher extends FastSearcher {
    private static Log logger = LogFactory.getLog(YahooFastSearcher.class);


    @Override
    protected String getTitleCssQuery() {
        return "div.result h3.title  a";
    }

    @Override
    protected int getPageSize() {
        return 10;
    }

    @Override
    protected String getUrl(String keyword, int page) {
        String url = "https://search.yahoo.com/search?&fr2=sb-top&fr=yfp-t-120&fp=1&b=" + ((page - 1) * pageSize + 1) + "&pz=" + pageSize + "&p=" + keyword;
        return url;
    }

    @Override
    protected String getTotalString(Document document) {
        return null;
//        String cssQuery = "div.compPagination  span";
//        logger.debug("total cssQuery: " + cssQuery);
//        Element totalElement = document.select(cssQuery).first();
//        if (totalElement == null) {
//            return null;
//        }
//        String totalText = totalElement.text();
//        logger.info("搜索结果文本：" + totalText);
//        return totalText;
    }
}
