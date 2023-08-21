package cn.beichenhpy.bean;

import cn.beichenhpy.factory.DefaultUserBeanFactory;
import cn.beichenhpy.factory.UserBeanFactory;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ServiceLoader;

/**
 * Bean实例化特殊实现
 */
public class SpecialBeanInstantiationDemo {
    public static void main(String[] args) {
        ApplicationContext applicationContext =  new ClassPathXmlApplicationContext("classpath:/META-INF/special-dependency-instantiation-context.xml");
        AutowireCapableBeanFactory beanFactory = applicationContext.getAutowireCapableBeanFactory();

        ServiceLoader<UserBeanFactory> load = applicationContext.getBean("userFactoryServiceLoader", ServiceLoader.class);
        for (UserBeanFactory next : load) {
            System.out.println(next.createUser("1"));
        }

        UserBeanFactory bean = beanFactory.createBean(DefaultUserBeanFactory.class);
        System.out.println(bean.createUser("测试"));

    }

}
