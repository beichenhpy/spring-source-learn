package cn.beichenhpy.bean;

import cn.beichenhpy.factory.DefaultUserBeanFactory;
import cn.beichenhpy.factory.UserBeanFactory;
import cn.beichenhpy.factory.UserFactoryServiceLoaderFactoryBean;
import org.springframework.beans.factory.serviceloader.ServiceLoaderFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.support.AbstractApplicationContext;

/**
 * Bean初始化demo
 */
@Configuration
public class BeanInitializationDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(BeanInitializationDemo.class);
        System.out.println("spring 应用上下文准备初始化");
        applicationContext.refresh();
        System.out.println("spring 应用上下文准备完毕");

        /*
         * PostConstruct方法进行初始化...
         * InitializingBean#AfterPropertiesSet初始化。。。
         * bean注解initMethod初始化....
         */
//        UserBeanFactory userBeanFactory = applicationContext.getBean("defaultUserBeanFactory", UserBeanFactory.class);
        System.out.println("spring 应用上下文准备关闭");
        applicationContext.close();
        System.out.println("spring 应用上下文已关闭");
    }


    @Bean(initMethod = "beanAnnotationInit", destroyMethod = "beanAnnotationDestroy")
    public DefaultUserBeanFactory defaultUserBeanFactory() {
        return new DefaultUserBeanFactory();
    }



}
