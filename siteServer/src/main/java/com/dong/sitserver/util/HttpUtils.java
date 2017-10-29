package com.dong.sitserver.util;


import com.dong.sitserver.common.ServiceException;
import com.dong.sitserver.common.util.StringTools;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class HttpUtils {
    private static Log logger = LogFactory.getLog(HttpUtils.class);
    public static Document accessUrl(String url) {
        return accessUrl(url, "utf-8");
    }

    public static Document accessUrl(String url, String charset) {
        if(StringTools.isEmptyOrNone(url)){
            return null;
        }

        Set<String> datas = new HashSet<String>();
        Document doc = null;
        try {

            HttpClient httpClient = new HttpClient();
            //设置访问编码
            httpClient.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, charset);
            //设置 HttpClient 接收 Cookie,用与浏览器一样的策略
            httpClient.getParams().setCookiePolicy(CookiePolicy.BROWSER_COMPATIBILITY);
            //让服务器知道访问源为浏览器
            httpClient.getParams().setParameter(HttpMethodParams.USER_AGENT, "Mozilla/5.0 (Windows NT 6.1; rv:8.0.1) Gecko/20100101 Firefox/8.0.1");
            List<Header> headers = new ArrayList<Header>();
            headers.add(new Header("Cookie", "lang=CH; load_balancer=dcd47a65-d505-4de2-a521-d9ca7145da5a; JSESSIONID=981453C8B9516E0DFCA183C1F7F15E0C.node16"));
            headers.add(new Header("Connection", "keep-alive"));
            httpClient.getHostConfiguration().getParams().setParameter(
                    "http.default-headers", headers);


          //  httpClient.getHostConfiguration().setProxy("192.168.101.1", 5608);
           //  httpClient.getParams().setAuthenticationPreemptive(true);
            //如果代理需要密码验证，这里设置用户名密码
            // httpClient.getState().setProxyCredentials(AuthScope.ANY, new UsernamePasswordCredentials("llying.iteye.com","llying"));


            GetMethod method = new GetMethod(url);
            int statusCode = httpClient.executeMethod(method);
            ;
            InputStream ins = null;
            String result = "";
            StringBuilder sb = new StringBuilder();
            // log.info("the result status code is " + statusCode);
            if (statusCode == HttpStatus.SC_OK) {
                ins = method.getResponseBodyAsStream();
                byte[] b = new byte[1024];
                int r_len = 0;
                while ((r_len = ins.read(b)) > 0) {
                    sb.append(new String(b, 0, r_len, method
                            .getResponseCharSet()));
                }
            }

            //  result = new String(sb.toString().getBytes("ISO-8859-1"), "UTF-8");
            result = sb.toString();
            doc = Jsoup.parse(result);


        } catch (Exception e) {
            logger.error("访问出错 == > "+url,e);
           throw new ServiceException("访问出错",e);

        }
        return doc;

    }


    public static String getDomain(String url) {
        url = url+"/";
       int in =  url.indexOf("/",10);

        return url.substring(0, in);
    }


}
