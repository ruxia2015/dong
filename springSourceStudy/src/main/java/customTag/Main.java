package customTag;

import java.net.URL;

/**
 * Created by rxia on 2015/10/29.
 */
public class Main {

    public static void main(String[] args){
        URL url2 = Main.class.getResource("/META-INF/Spring.handlers");
        System.out.print("-----" + url2.getPath());
    }
}
