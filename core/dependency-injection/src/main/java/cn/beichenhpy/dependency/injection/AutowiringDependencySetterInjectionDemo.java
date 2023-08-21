package cn.beichenhpy.dependency.injection;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * 自动模式 byName setter注入演示
 */
public class AutowiringDependencySetterInjectionDemo {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        //加载xml资源
        String xmlPath = "classpath:/META-INF/dependency-autowiring-setter-context.xml";
        reader.loadBeanDefinitions(xmlPath);

        UserHolder userHolderByName = beanFactory.getBean("userHolderByName",UserHolder.class);
        UserHolder userHolderByType = beanFactory.getBean("userHolderByType",UserHolder.class);
        System.out.println(userHolderByName);
        System.out.println(userHolderByType);
    }
}
