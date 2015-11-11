package jms;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by rxia on 2015/11/6.
 */
public class Test {

    public static  void main(String[] args){
        ApplicationContext ap =new ClassPathXmlApplicationContext("applicationContext-jms.xml");

        SendMessage sendMessage = (SendMessage) ap.getBean("sendMessage");

        System.out.println("sendMessage");
       for(int i=0;i<1001;i++){
           System.out.println("sendMessage....");
           long time = System.currentTimeMillis();
            sendMessage.sendMessage("message send " + i);
           System.out.println("超时时间...." +(System.currentTimeMillis() -  time)/100.0 + "  s");
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

    }



}
