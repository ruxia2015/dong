import org.apdplat.search.baiduSearcher.impl.JSoupBaiduSearcher;
import org.apdplat.search.bean.SearchResult;
import org.apdplat.search.Searcher;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by rxia on 2015/9/15.
 */
public class parseHtml {

    public static void main(String[] args) {

        Searcher searcher = new JSoupBaiduSearcher();

        SearchResult searchResult = searcher.search("baidu.com");

        System.out.print(searchResult.getPageSize());


        try {

            Set<String> emails = new HashSet<String>();


            Document doc = Jsoup.connect("http://www.csdn.net/").get();
            String body = doc.body().toString();

            String emailRegex = "/[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+/";
            Pattern p = Pattern.compile(emailRegex);

            Matcher m = p.matcher(body);

            while (m.find()) {
                String g = m.group();
                emails.add(g);
                System.out.println(g);

            }


            //  System.out.print(body);

        } catch (Exception e) {

        }

    }

}
