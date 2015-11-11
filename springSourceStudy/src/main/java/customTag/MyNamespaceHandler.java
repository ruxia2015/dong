package customTag;

import org.springframework.beans.factory.xml.BeanDefinitionDecorator;
import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * Created by rxia on 2015/10/21.
 */
public class MyNamespaceHandler  extends NamespaceHandlerSupport{


    @Override
    public void init() {
        registerBeanDefinitionParser("user",  new UserBeanDefinitionParser());
    }
}
