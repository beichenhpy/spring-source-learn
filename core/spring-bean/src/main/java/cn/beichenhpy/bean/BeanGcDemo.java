package cn.beichenhpy.bean;

import cn.beichenhpy.domain.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Spring bean 垃圾回收
 */
@Configuration
public class BeanGcDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(BeanGcDemo.class);
        applicationContext.refresh();
        applicationContext.close();
        System.out.println("spring 容器已关闭");
        System.gc();

    }



    @Bean
    public User user() {
        return User.createUser("gc-user");
    }
}
