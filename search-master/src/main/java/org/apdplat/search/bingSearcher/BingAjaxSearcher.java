package org.apdplat.search.bingSearcher;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apdplat.search.bean.SearchResult;
import org.apdplat.search.bean.Webpage;
import org.apdplat.search.tools.Tools;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rxia on 2015/9/16.
 */
public class BingAjaxSearcher implements IBingSearcher {
    private static final Logger LOG = LoggerFactory.getLogger(BingAjaxSearcher.class);


    public static  void main(String[] args){
        new BingAjaxSearcher().search("张三");
    }


    @Override
    public SearchResult search(String keyword) {
        return  search(keyword, 1);
    }

    @Override
    public SearchResult search(String keyword, int page) {
        int pageSize = 8;
        //谷歌搜索结果每页大小为8，start参数代表的是返回结果的开始数
        //如获取第一页则start=0，第二页则start=10，第三页则start=20，以此类推，抽象出模式：(page-1)*pageSize

        String url = "http://cn.bing.com/search?q="+keyword+"&first="+(page-1)*pageSize;
        SearchResult searchResult = new SearchResult();
        searchResult.setPage(page);
        List<Webpage> webpages = new ArrayList<>();
        try {
            HttpClient httpClient = new HttpClient();
            GetMethod getMethod = new GetMethod(url);

            httpClient.executeMethod(getMethod);
            getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
                    new DefaultHttpMethodRetryHandler());

            int statusCode = httpClient.executeMethod(getMethod);
            if (statusCode != HttpStatus.SC_OK) {
                LOG.error("搜索失败: " + getMethod.getStatusLine());
                return null;
            }
            InputStream in = getMethod.getResponseBodyAsStream();
            byte[] responseBody = Tools.readAll(in);
            String response = new String(responseBody, "UTF-8");
            LOG.debug("搜索返回数据：" + response);
            JSONObject json = new JSONObject(response);
            String totalResult = json.getJSONObject("responseData").getJSONObject("cursor").getString("estimatedResultCount");
            int totalResultCount = Integer.parseInt(totalResult);
            LOG.info("搜索返回记录数： " + totalResultCount);
            searchResult.setTotal(totalResultCount);

            JSONArray results = json.getJSONObject("responseData").getJSONArray("results");

            LOG.debug("搜索结果:");
            for (int i = 0; i < results.length(); i++) {
                Webpage webpage = new Webpage();
                JSONObject result = results.getJSONObject(i);
                //提取标题
                String title = result.getString("titleNoFormatting");
                LOG.debug("标题：" + title);
                webpage.setTitle(title);
                //提取摘要
                String summary = result.get("content").toString();
                summary = summary.replaceAll("<b>", "");
                summary = summary.replaceAll("</b>", "");
                summary = summary.replaceAll("\\.\\.\\.", "");
                LOG.debug("摘要：" + summary);
                webpage.setSummary(summary);
                //从URL中提取正文
                String _url = result.get("url").toString();
                webpage.setUrl(_url);
                String content = Tools.getHTMLContent(_url);
                LOG.debug("正文：" + content);
                webpage.setContent(content);
                webpages.add(webpage);
            }
        } catch (IOException | JSONException | NumberFormatException e) {
            LOG.error("执行搜索失败：", e);
        }
        searchResult.setWebpages(webpages);
        return searchResult;
    }
}
