package cn.beichenhpy.bean.lifecycle;

import cn.beichenhpy.domain.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * BeanDefinition合并示例
 */
public class MergeBeanDefinitionDemo {


    public static void main(String[] args) {

        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinitions("classpath:/META-INF/dependency-lookup-context.xml");

        User superUser = factory.getBean("superUser", User.class);
        System.out.println(superUser);
        User user = factory.getBean("user", User.class);
        System.out.println(user);



    }
}
