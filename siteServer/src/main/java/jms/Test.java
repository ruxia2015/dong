package jms;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.net.URL;

/**
 * Created by rxia on 2015/11/6.
 */
public class Test {

    URL url = Class.class.getResource("META-INF/spring.handlers");

    public static void main(String[] args) throws IOException {
        ApplicationContext ap = new ClassPathXmlApplicationContext("applicationContext-jms.xml");

        SendTopicMessage sendMessage = (SendTopicMessage) ap.getBean("sendMessage");

        System.out.println("sendMessag " + sendMessage);
      for(int i=0;i<10;i++){
           System.out.println("sendMessage....");
           long time = System.currentTimeMillis();
            sendMessage.sendMessage("topic xiaoxi " + i);
         //  System.out.println("超时时间...." +(System.currentTimeMillis() -  time)/100.0 + "  s");
        }

        SendQueueMessage sendQueueMessage = (SendQueueMessage) ap.getBean("sendQueueMessage");

        System.out.println("*************************************" );
        for(int i=0;i<10;i++){
            System.out.println("sendQueueMessage....");
            sendQueueMessage.sendMessage("topic xiaoxi " + i);

        }

/*
        ArrayList list = new ArrayList();
        for(int i=0;i<105;i++){
            list.add(i);
        }
        int i = 0;
        while (i<=list.size()/20) {
            System.out.println(list);
            List lists = list.subList(i*20, Math.min(list.size(),(i+1)*20));
            System.out.println("====="+list);
            System.out.println("*****"+lists);
            i++;
        }*/

/*        //"META-INF/spring.handlers"
        String resourceName = "META-INF/spring.handlers";
        Enumeration<URL> urls = ClassLoader.getSystemResources(resourceName);
        while (urls.hasMoreElements()) {
            URL url = urls.nextElement();
            System.out.println(url.getPath());
        }
        URL url2 = Test.class.getResource("/META-INF/Spring-test.xsd");
        System.out.print("-----" + url2.getPath());*/

    }


}
