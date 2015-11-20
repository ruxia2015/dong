package proxy.dynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by rxia on 2015/11/20.
 */
public class BookFaceProxy implements InvocationHandler {
    private Object target;

    public BookFaceProxy(Object target) {
        this.target = target;
    }

    public Object bind(Object target) {
        this.target = target;
        System.out.println(target.getClass().getInterfaces());
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);//要绑定接口(这是一个缺陷，cglib弥补了这一缺陷)
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = null;
        System.out.println("事物开始");
        result = method.invoke(proxy, args);
        System.out.println("事物结束");
        return result;
    }
}
