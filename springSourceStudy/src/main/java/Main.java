import customTag.User;
import junit.framework.Assert;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by rxia on 2015/10/16.
 */
public class Main {
    LogFactory logFactory;

    @Test
    public void testSimpleLoad(){
     //   BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanFactoryTest.xml"));
        ApplicationContext ap =new ClassPathXmlApplicationContext("beanFactoryTest.xml");
        User bean = (User)ap.getBean("testBean");

      System.out.print(bean.getUserName() +"   " + bean.getEmail());
        Set<String> set = new HashSet<String>();
        System.out.println(set.add("4"));
        System.out.println(set.add("4"));
        System.out.println(set.add("8"));

    }

    public static void main(String[] args){


    }
}

/***
 * 打字学习中
 *
 * xmlbeanFactory继承自defaultListableBeanFactory,而defaultListableBeanFactory是整个bean加载的核心部分，
 * 是spring注册及加载bean的默认实现，而对于xmlbeanfactory与defaultlistablefacory不同的地方其实是在xmlbeanfactory中使用了自定义的xml读取器
 * xmlbeandefinitionReader，实现了个性化的beanDefinitionReader读取，defaultListableBeanFactory继承自AbstractAutowireCapableBeanFactory
 * 并实现了ConfigurableListableBeanFactory以及BeanDefinitionRegistry接口。以下是ConfigurationListableBeanFactory的层次结构图以及相关类图
 *
 * 从上面的类图以及层次结构图中，我们可以
 *
 *
 *
 * alaisRegistry  定义对alias的简单增删改等操作
 * SimpleAliasRegistry：主要使用map作为alias的缓存，并对接口aliasRegistry进行实现
 * SingletonBeanRegistry：定义对单例的注册以及获取
 * BeanFactory 定义获取bean及bean的各种属性
 *
 * defaultSingletonBeanRegistry 对接口SingletonBeanRegistry各函数的实现
 * HierarchicalBeanFactory：继承BeanFactory，也就是在BeanFactory定义的功能的基础上增加了对parentfactory的支持
 * beanDefinitionRegistry 定义对beanDefinition的各种增删改操作
 * FactoryBeanRegistrySupport 在defaultSingletonBeanRegistry基础上增加了对factorybean的特殊处理功能
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 **/