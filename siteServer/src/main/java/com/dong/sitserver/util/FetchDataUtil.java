package com.dong.sitserver.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by rxia on 2015/9/15.
 */
public class FetchDataUtil {

    public static Set<String> fectchDataByUrl(String regex, String url) {

        Set<String> datas = new HashSet<String>();
        try {
            Document doc = Jsoup.connect("http://www.csdn.net/").get();
            String body = doc.body().toString();

            String emailRegex = "/[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+/";
            Pattern p = Pattern.compile(emailRegex);

            Matcher m = p.matcher(body);

            while (m.find()) {
                String g = m.group();
                datas.add(g);
            }
        } catch (Exception e) {

           e.printStackTrace();
        }
        return datas;

    }

    public static Set<String> fetchEmail(String url) {
        String emailRegex = "/[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+/";
        return fectchDataByUrl(emailRegex, url);
    }

    public static void main(String[] args){
        String url = "http://www.baidu.com/link?url=_eq9F-74v6KNEN7X4zUpA44VW3q_3maXyIOrjXjabeQzz17VWbJ016XE1PHf11IHe5GqBrimWjQJidazqSRNlK";
        fetchEmail(url);
    }
}
