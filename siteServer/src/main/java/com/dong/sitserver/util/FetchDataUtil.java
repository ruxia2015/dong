package com.dong.sitserver.util;

import com.dong.sitserver.common.util.StringTools;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by rxia on 2015/9/15.
 */
public class FetchDataUtil {

    public static Set<String> getUrls(String url) {
        Document doc = HttpUtils.accessUrl(url);

        String content = doc.body().text();
        Set<String> urls = fetchFromContent(content,PropertiesUtil.getVaue(PropertyConstant.FILE_PATH_CONFIG,PropertyConstant.REGEX_HTTP));
        return urls;
    }

    public static Set<String> fectchDataByUrl(String regex, String url) {
        Document doc = HttpUtils.accessUrl(url);
        return fetchFromContent(doc.body().text(), regex);
    }

    public static Set<String> fetchEmail(String url) {
        String emailRegex = PropertiesUtil.getVaue(PropertyConstant.FILE_PATH_CONFIG, PropertyConstant.REGEX_EMAIL);
        return fectchDataByUrl(emailRegex, url);
    }


    public static Set<String> fetchFromContent(String content, String regex) {
        Set<String> datas = new HashSet<String>();
        if (!StringTools.isEmptyOrNone(regex)) {
            if (!regex.startsWith("/")) {
                regex = "/" + regex + "/";
            }
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
        String url = "http://www.baidu.com/link?url=_eq9F-74v6KNEN7X4zUpA44VW3q_3maXyIOrjXjabeQzz17VWbJ016XE1PHf11IHe5GqBrimWjQJidazqSRNlK";
        fetchEmail(url);
    }
}
