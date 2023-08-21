package cn.beichenhpy.dependency.injection;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * 自动模式 constructor 注入演示
 */
public class AutowiringDependencyConstructorInjectionDemo {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        //加载xml资源
        String xmlPath = "classpath:/META-INF/dependency-autowiring-constructor-context.xml";
        reader.loadBeanDefinitions(xmlPath);

        UserHolder userHolder = beanFactory.getBean("userHolder",UserHolder.class);
        System.out.println(userHolder);
    }
}
