package com.dong.sitserver.util;

import com.dong.sitserver.common.util.FileUtil.FileUtil;
import com.dong.sitserver.common.util.StringTools;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.File;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by rxia on 2015/9/15.
 */
public class FetchDataUtil {

    public static Set<String> getUrls(String url) {
        Document doc = HttpUtils.accessUrl(url);
        if (doc == null || doc.body() == null) {
            return new HashSet<String>();
        }

        String content = doc.body().html();

        Set<String> urls = fetchFromContent(content, PropertiesUtil.getVaue(PropertyConstant.FILE_PATH_CONFIG, PropertyConstant.REGEX_HTTP));
        return urls;
    }

    public static Set<String> fectchDataByUrl(String regex, String url) {
        String doc = null;
        try {
            if (url.startsWith("https")) {
                doc = HttpsUtils.post(url);
            } else {
                Document document = HttpUtils.accessUrl(url);
                doc = document.body().html();
            }


            System.out.print(".........." + doc);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return fetchFromContent(doc, regex);
    }

    public static Set<String> fetchEmail(String url) {
        String emailRegex = PropertiesUtil.getVaue(PropertyConstant.FILE_PATH_CONFIG, PropertyConstant.REGEX_EMAIL);
        return fectchDataByUrl(emailRegex, url);
    }

    public static Set<String> fetchUrl(String url) {
        String regex = PropertiesUtil.getVaue(PropertyConstant.FILE_PATH_CONFIG, PropertyConstant.REGEX_HTTP);
        return fectchDataByUrl(regex, url);
    }

//    public static Set<String> crawlingUtlFromHtml(String url) {
//
//        Document doc = HttpHelper.accessUrl(url);
//        Set<String> urls = new HashSet<String>();
//
//        org.jsoup.select.Elements em = doc.getElementsByTag("a");
//
//        String domain = HttpUtils.getDomain(url);
//
//        Iterator<Element> it = em.iterator();
//        while (it.hasNext()) {
//            Element element = it.next();
//            String href = element.attr("href");
//            if (StringUtils.isEmpty(href) || href.equals("null") || href.equals("#") || href.startsWith("javascript:") || href.equals("/")) {
//                continue;
//            }
//            String site = null;
//            if (href.startsWith("http")) {
//                site = href;
//            } else {
//                site = domain  + href;
//            }
//            System.out.println("====>"+site);
//            urls.add(site);
//        }
//        return urls;
//
//    }


    public static Set<String> fetchFromContent(String content, String regex) {
        Set<String> datas = new HashSet<String>();
        if (StringTools.isEmptyOrNone(content)) {
            return datas;
        }

        // String emailRegex = "/[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+/";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(content);

        while (m.find()) {
            String g = m.group();
            datas.add(g);
        }
        return datas;
    }

    public static void main(String[] args) {
        String url = "C:\\Users\\rxia\\Desktop\\2.txt";
        String emailRegex = "[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+";
        File file = new File(url);
        String content = FileUtil.readFile(url);
       /* try {
           // content = new String(content.getBytes("ISO-8859-1"),"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }*/
        System.out.print(content);
        Set<String> ste = fetchFromContent(content, emailRegex);
        System.out.println("\n\n=======================================");
        for (String str : ste) {
            System.out.println(str);
        }

    }
}
