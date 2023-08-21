package cn.beichenhpy.dependency.injection;

import cn.beichenhpy.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;

/**
 * 注解constructor注入测试
 */
public class AnnotationDependenceFieldInjectionDemo {

    //会忽略静态字段
    @Autowired
    private UserHolder userHolder;

    @Resource
    private UserHolder userHolder2;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(AnnotationDependenceFieldInjectionDemo.class);

        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(applicationContext);
        //加载xml资源
        String xmlPath = "classpath:/META-INF/dependency-lookup-context.xml";
        reader.loadBeanDefinitions(xmlPath);

        //刷新
        applicationContext.refresh();

        //获取到当前类的实例
        AnnotationDependenceFieldInjectionDemo demo = applicationContext.getBean(AnnotationDependenceFieldInjectionDemo.class);
        //@autowired
        UserHolder userHolder = demo.userHolder;
        System.out.println(userHolder);
        //@resource
        UserHolder userHolder2 = demo.userHolder2;
        System.out.println(userHolder2);
        //true
        System.out.println(userHolder == userHolder2);


        applicationContext.close();
    }




    @Bean
    public UserHolder userHolder(User user) {
        return new UserHolder(user);
    }
}
