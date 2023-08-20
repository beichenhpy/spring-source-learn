package cn.beichenhpy.dependency.container;

import cn.beichenhpy.domain.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * Ioc 容器示例
 */
public class BeanFactoryAsIocContainerDemo {

    public static void main(String[] args) {
        //创建BeanFactory容器
        DefaultListableBeanFactory defaultListableBeanFactory = new DefaultListableBeanFactory();
        //加载配置
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(defaultListableBeanFactory);
        //xml文件路径 location
        String location = "classpath:/META-INF/dependency-lookup-context.xml";
        //加载配置文件
        int lookupBeanDefinitionCounts = reader.loadBeanDefinitions(location);
        System.out.println("bean加载数量: " + lookupBeanDefinitionCounts);
        //primary superUser
        User superUser = defaultListableBeanFactory.getBean(User.class);
        System.out.println(superUser);
    }
}
