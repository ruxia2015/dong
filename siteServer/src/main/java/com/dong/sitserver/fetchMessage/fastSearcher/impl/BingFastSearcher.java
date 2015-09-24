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
public class BingFastSearcher extends FastSearcher {
    private static Log logger = LogFactory.getLog(BingFastSearcher.class);


    @Override
    protected String getTitleCssQuery() {
        return "div#b_content ol li.b_algo a";
    }

    @Override
    protected int getPageSize() {
        return 8;
    }

    @Override
    protected String getUrl(String keyword, int page) {
        String url ="http://cn.bing.com/search?&qs=n&pq=1&sc=0-0&sp=-1&sk=&cvid=e80dcc193ac64f1e8534a7a0040ae238&first="+( (page - 1) * pageSize +1)+"&FORM=PERE&q=" + keyword;
        return url;
    }

    @Override
    protected String getTotalString(Document document) {
        String cssQuery = "div#b_content ol li.b_pag";
        logger.debug("total cssQuery: " + cssQuery);
        Element totalElement = document.select(cssQuery).first();
        if (totalElement == null) {
            return null;
        }
        String totalText = totalElement.text();
        logger.info("搜索结果文本：" + totalText);
        int totalIndex = totalText.indexOf("(");
        int totalEnd = totalText.indexOf(")", totalIndex);
        if (totalIndex <= 0 || totalEnd <= 0) {
            return null;

        }

        totalText = totalText.substring(totalIndex, totalEnd);
        return totalText;
    }
}
