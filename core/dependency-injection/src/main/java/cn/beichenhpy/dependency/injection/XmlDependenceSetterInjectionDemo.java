package cn.beichenhpy.dependency.injection;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * xml方式setter依赖注入
 */
public class XmlDependenceSetterInjectionDemo {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        //加载xml资源
        String xmlPath = "classpath:/META-INF/dependency-injection-setter-context.xml";
        reader.loadBeanDefinitions(xmlPath);

        /*
         * 手动依赖查找UserHolder 使user注入到UserHolder中
         */
        UserHolder userHolder = beanFactory.getBean(UserHolder.class);
        userHolder.userPower();
        System.out.println(userHolder);
    }
}
