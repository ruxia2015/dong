package com.dong.sitserver.util;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by rxia on 2015/9/8.
 */
public class PropertiesUtil {
    private static Map<String, Properties> map = new HashMap<String, Properties>();
    private static Map<String, Properties> lastModifiedMap = new HashMap<String, Properties>();

    public static String getVaue(String fileName, String key) {
        Properties properties = new Properties();
        InputStream in = PropertiesUtil.class.getResourceAsStream(fileName);
        try {
            properties.load(in);
            map.put(fileName, properties);
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return properties.getProperty(key);
    }


}
