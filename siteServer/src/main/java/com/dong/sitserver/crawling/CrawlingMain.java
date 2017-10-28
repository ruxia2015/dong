package com.dong.sitserver.crawling;

import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.dong.sitserver.common.util.FileUtil.FileUtil;
import com.dong.sitserver.util.FetchDataUtil;

/**
 * 数据抓取入口
 *
 * @author XRX
 * @version [版本号, 2017/10/28]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class CrawlingMain {
    Log log = LogFactory.getLog(CrawlingMain.class);
    
    public static void main(String[] args) {
        //1、分析网页
        Set<String> emailSet = FetchDataUtil.fetchEmail("http://www.csdn.net/");
        Set<String> httpSet = FetchDataUtil.fetchEmail("http://www.csdn.net/");


        FileUtil.writeCollectionToFile("D:\\aa\\1.txt", emailSet, true);
    }
    
}
