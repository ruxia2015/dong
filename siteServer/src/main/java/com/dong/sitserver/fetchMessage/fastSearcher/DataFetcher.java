package com.dong.sitserver.fetchMessage.fastSearcher;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by rxia on 2015/9/24.
 */
public class DataFetcher {

    public Map<Integer, List<String>> getDatas(String url, String... regexs) {
        Map<Integer, List<String>> result = new HashMap<Integer, List<String>>();
        try {
            Document document = Jsoup.connect(url).get();
            if (document == null || document.body() == null) {
                return null;
            }
            String content = document.body().html();

            int i = 0;
            for (String regex : regexs) {
                List<String> datas = new ArrayList<String>();
                Pattern p = Pattern.compile(regex);
                Matcher m = p.matcher(content);

                while (m.find()) {
                    String g = m.group();
                    datas.add(g);
                }

                result.put(i++ , datas);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;

    }
}
