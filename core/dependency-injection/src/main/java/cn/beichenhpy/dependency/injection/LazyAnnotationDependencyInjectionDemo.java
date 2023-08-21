package cn.beichenhpy.dependency.injection;

import cn.beichenhpy.domain.User;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

/**
 * 延迟注入
 * @see org.springframework.beans.factory.ObjectFactory
 * @see org.springframework.beans.factory.ObjectProvider
 */
public class LazyAnnotationDependencyInjectionDemo {

    @Autowired
    private ObjectProvider<User> userObjectProvider;

    @Autowired
    private ObjectFactory<List<User>> userObjectFactory;

    public static void main(String[] args) {

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(LazyAnnotationDependencyInjectionDemo.class);

        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(applicationContext);
        //加载xml资源
        String xmlPath = "classpath:/META-INF/dependency-lookup-context.xml";
        reader.loadBeanDefinitions(xmlPath);
        //刷新
        applicationContext.refresh();

        LazyAnnotationDependencyInjectionDemo bean = applicationContext.getBean(LazyAnnotationDependencyInjectionDemo.class);
        ObjectProvider<User> userObjectProvider = bean.userObjectProvider;
        System.out.println(userObjectProvider.getObject());
        userObjectProvider.stream()
                .forEach(System.out::println);

        ObjectFactory<List<User>> userObjectFactory = bean.userObjectFactory;
        System.out.println(userObjectFactory.getObject());

    }
}
