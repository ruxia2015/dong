package common.util;

import java.io.InputStream;
import java.util.Properties;

/**
 * Created by rxia on 2015/9/8.
 */
public class PropertiesUtil {
   static Properties properties = new Properties();
    static{
          String fileName = "/config.properties";

        InputStream in =  PropertiesUtil.class.getResourceAsStream(fileName);
        try {
            properties.load(in);
            in.close();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public static String getVaue(String key){
       return properties.getProperty(key);
    }

    public static void main(String[] args){
        System.out.print(PropertiesUtil.getVaue("bean.waitGenerator.base"));

    }
}
