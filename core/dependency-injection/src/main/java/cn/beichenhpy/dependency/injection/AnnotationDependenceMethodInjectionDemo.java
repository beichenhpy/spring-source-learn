package cn.beichenhpy.dependency.injection;

import cn.beichenhpy.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;

/**
 * 注解Autowired方法注入测试
 */
public class AnnotationDependenceMethodInjectionDemo {

    private UserHolder userHolder1;

    private UserHolder userHolder2;

    @Autowired
    public void setUserHolder1(UserHolder userHolder1) {
        this.userHolder1 = userHolder1;
    }


    @Resource
    public void init2(UserHolder userHolder) {
        this.userHolder2 = userHolder;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(AnnotationDependenceMethodInjectionDemo.class);

        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(applicationContext);
        //加载xml资源
        String xmlPath = "classpath:/META-INF/dependency-lookup-context.xml";
        reader.loadBeanDefinitions(xmlPath);

        //刷新
        applicationContext.refresh();

        //获取到当前类的实例
        AnnotationDependenceMethodInjectionDemo demo = applicationContext.getBean(AnnotationDependenceMethodInjectionDemo.class);
        //@autowired
        UserHolder userHolder = demo.userHolder1;
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
