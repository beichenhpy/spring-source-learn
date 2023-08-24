package cn.beichenhpy.bean.lifecycle;


import cn.beichenhpy.domain.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;

/**
 * Bean元配置方式示例
 */
public class BeanMetaMetaConfigurationDemo {

    public static void main(String[] args) {
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        EncodedResource encodedResource = new EncodedResource(new ClassPathResource("META-INF/user.properties"), "utf-8");
        createBeanDefinitionByProperties(factory, encodedResource);
        User user = factory.getBean(User.class);
        System.out.println(user);
    }


    /**
     * Xml方式读取xml
     */
    public static void createBeanDefinitionByXml(DefaultListableBeanFactory context) {
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(context);
        reader.loadBeanDefinitions("classpath:/META-INF/dependency-inject-context.xml");
    }

    /**
     * properties  5.3已弃用
     */
    @SuppressWarnings("all")
    public static void createBeanDefinitionByProperties(DefaultListableBeanFactory factory, EncodedResource encodedResource) {
        PropertiesBeanDefinitionReader reader = new PropertiesBeanDefinitionReader(factory);
        reader.loadBeanDefinitions(encodedResource);
    }
}
