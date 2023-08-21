package cn.beichenhpy.dependency.injection;

import cn.beichenhpy.domain.User;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

/**
 * 注解驱动的依赖注入解决方式
 */
public class AnnotationDependencyResolutionInjectionDemo {

    /**
     * DependencyDescriptor ->
     * 1. 实时注入  eager = true
     * 2. 类型(User.class)
     * 3. 字段名称("user")
     * 4. 是否必填 required = true
     * 5. 是否首要 primary = true
     */
    @Autowired
    private User user;

    public static void main(String[] args) {

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(AnnotationDependencyResolutionInjectionDemo.class);

        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(applicationContext);
        //加载xml资源
        String xmlPath = "classpath:/META-INF/dependency-lookup-context.xml";
        reader.loadBeanDefinitions(xmlPath);
        //刷新
        applicationContext.refresh();

        AnnotationDependencyResolutionInjectionDemo bean = applicationContext.getBean(AnnotationDependencyResolutionInjectionDemo.class);
        User user = bean.user;
        System.out.println(user);

    }
}
