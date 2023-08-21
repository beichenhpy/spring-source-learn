package cn.beichenhpy.factory;

import cn.beichenhpy.domain.User;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Random;

/**
 * 用户工厂默认实现
 */
public class DefaultUserBeanFactory implements UserBeanFactory, InitializingBean, DisposableBean {


    /**
     * 使用@PostConstruct进行初始化
     */

    @PostConstruct
    public void postConstructInit() {
        System.out.println("PostConstruct方法进行初始化...");
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("preDestroy...");
    }


    public void beanAnnotationInit() {
        System.out.println("bean注解initMethod初始化....");
    }
    public void beanAnnotationDestroy() {
        System.out.println("bean注解destroy初始化....");
    }

    @Override
    public User createUser(String name) {
        return new User(new Random().nextInt(), name);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("InitializingBean#AfterPropertiesSet初始化。。。");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("DisposableBean#destroy。。。");

    }
}
