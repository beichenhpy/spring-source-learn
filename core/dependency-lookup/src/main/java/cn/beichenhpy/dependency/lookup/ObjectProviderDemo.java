package cn.beichenhpy.dependency.lookup;

import cn.beichenhpy.domain.User;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

/**
 * ObjectProvider示例
 */
public class ObjectProviderDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //注册当前类为配置类
        applicationContext.register(ObjectProviderDemo.class);
        //启动容器
        applicationContext.refresh();

        getBeanByObjectProvider(applicationContext);
        lookupIfAvailable(applicationContext);
        lookupByStreamOps(applicationContext);
        //关闭容器
        applicationContext.close();
    }

    @Bean
    @Primary
    public User user() {
        return User.createUser("ObjectProviderUser");
    }

    @Bean
    public User objUser() {
        return User.createUser("objUser");
    }

    public static void getBeanByObjectProvider(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<User> beanProvider = applicationContext.getBeanProvider(User.class);
        System.out.println(beanProvider.getObject());
    }


    public static void lookupIfAvailable(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<User> beanProvider = applicationContext.getBeanProvider(User.class);
        User user = beanProvider.getIfAvailable(() -> User.createUser("ifAvailable"));
        System.out.println("获取当前对象" + user);
    }


    public static void lookupByStreamOps(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<User> beanProvider = applicationContext.getBeanProvider(User.class);
        beanProvider.stream()
                .forEach(System.out::println);
    }
}
