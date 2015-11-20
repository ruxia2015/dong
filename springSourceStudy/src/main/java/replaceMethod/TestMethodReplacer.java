package replaceMethod;

import org.springframework.beans.factory.support.MethodReplacer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.lang.reflect.Method;

/**
 * Created by rxia on 2015/11/12.
 */
public class TestMethodReplacer implements MethodReplacer {
    @Override
    public Object reimplement(Object obj, Method method, Object[] args) throws Throwable {

        System.out.println("obj   " + obj);
        System.out.println("method  " + method);
        System.out.println("args  " + args);


        System.out.println("我退换了原有的方法！");

        return null;
    }


    public static  void main(String[] args){
        ApplicationContext ap = new ClassPathXmlApplicationContext("methodReplacer.xml");
        TestChangeMethod testChangeMethod = (TestChangeMethod)ap.getBean("testChangeMethod");
        testChangeMethod.changeMe();

    }
}
