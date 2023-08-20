package cn.beichenhpy.dependency.container;


import cn.beichenhpy.domain.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 注解实现Ioc 容器示例
 */
public class AnnotationAsIocContainerDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(AnnotationAsIocContainerDemo.class);
        //启动
        applicationContext.refresh();
        User user = applicationContext.getBean(User.class);
        System.out.println(user);
        applicationContext.close();
    }


    @Bean
    public User user() {
        User user = new User();
        user.setId(1);
        user.setName("注解");
        return user;
    }
}
