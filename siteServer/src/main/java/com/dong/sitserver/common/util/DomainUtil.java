package com.dong.sitserver.common.util;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by rxia on 2015/10/9.
 */
public class DomainUtil {

    //顶级域名为两级如  com.cn
    private static Set<String> topDomians = new HashSet<String>();

    static {
        topDomians.add("com.cn");
    }


    public static String getDomain(String site, boolean needHttp) {
        if (!needHttp) {
            site = site.replaceFirst("^http[s]?:(//|\\\\)", "");
        }
        site = site.replaceFirst("[/?\\\\]+.*", "");

        if (needHttp && !site.startsWith("http")) {
            site = "http://" + site;

        }
        return site;
    }

    public static String getMainDomain(String site, boolean needHttp) {
        site = getDomain(site, needHttp);

        String newSite = site;
        String[] arr = site.split("\\.");
        if (arr.length > 2) {
            newSite = arr[arr.length - 2] + "." + arr[arr.length - 1];
            if (topDomians.contains(newSite)) {
                newSite = arr[arr.length - 3] + "." + arr[arr.length - 2] + "." + arr[arr.length - 1];
            }
        }

        if (needHttp) {
            newSite = site.substring(0, site.indexOf("/") + 1) + newSite;
        }


        return newSite;
    }

    public static String toNormalSite(String site) {
        if (!site.startsWith("http")) {
            site = "http://" + site;
        }
        return site;
    }


    public static void main(String[] args) {
        System.out.println(getMainDomain("gmail.google.com.cn\\/??/http", true));
    }

}
