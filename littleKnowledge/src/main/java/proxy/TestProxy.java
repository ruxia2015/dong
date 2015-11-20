package proxy;

import org.junit.Test;
import proxy.dynamicProxy.BookFacadeCglib;
import proxy.dynamicProxy.BookFacadeImpl;
import proxy.dynamicProxy.BookFace;
import proxy.dynamicProxy.BookFaceProxy;
import proxy.staticProxy.CountImpl;
import proxy.staticProxy.CountProxy;

import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by rxia on 2015/11/20.
 */
public class TestProxy {

    public static void main(String[] args) {
        Map li = new TestProxy().B();
        System.out.println(li);

        List list = new ArrayList();
        new TestProxy().a(list);
        System.out.println(list);
    }

    /**
     * 测试静态代理（代理模式）
     */
    @Test
    public void testStaticProxy() {
        CountImpl countImpl = new CountImpl();
        CountProxy countProxy = new CountProxy(countImpl);
        countProxy.updateCount();
        countProxy.queryCount();
    }

    /**
     * java动态代理
     * 堆栈溢出
     */
    @Test
    public void testJavaDynamicProxy() {
        BookFacadeImpl c = new BookFacadeImpl();//元对象(被代理对象)
        BookFaceProxy ih = new BookFaceProxy(c);//代理实例的调用处理程序。
        //创建一个实现业务接口的代理类,用于访问业务类(见代理模式)。
        //返回一个指定接口的代理类实例，该接口可以将方法调用指派到指定的调用处理程序，如ProxyHandler。
        BookFace targetInterface =
                (BookFace) Proxy.newProxyInstance(c.getClass().getClassLoader(), c.getClass().getInterfaces(), ih);
        //调用代理类方法,Java执行InvocationHandler接口的方法.
        targetInterface.addBook();


    }

    @Test
    public void testCglib(){
            BookFacadeCglib cglib=new BookFacadeCglib();
            BookFacadeImpl bookCglib=(BookFacadeImpl)cglib.getInstance(new BookFacadeImpl());
            bookCglib.addBook();
    }

    public void A(Map<String, String> list) {
        //list = new HashMap<String, String>();
        list.put("a", "a");
    }

    public Map B() {
        Map li = new HashMap();
        A(li);
        return li;
    }

    public void a(List list) {
        list.add("a");
    }


}
