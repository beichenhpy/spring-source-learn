package cn.beichenhpy.dependency.source;


import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.core.io.ResourceLoader;

import javax.annotation.PostConstruct;

/**
 * 依赖来源示例
 */
public class DependencySourceDemo {

    @Autowired
    private BeanFactory beanFactory;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    private ResourceLoader resourceLoader;


    /**
     * 在初始化阶段，是依赖注入的后面执行的 如果出现循环依赖此处获取到的bean是否是半成品？
     */
    @PostConstruct
    public void init() {
        System.out.println("初始化中。。。");
        System.out.println("beanFactory == applicationContext: " + (beanFactory == applicationContext));
        System.out.println("beanFactory == applicationContext.getBeanFactory: " + (beanFactory == applicationContext.getAutowireCapableBeanFactory()));
        System.out.println("resourceLoader == applicationContext: " + (resourceLoader == applicationContext));
        System.out.println("applicationEventPublisher == applicationContext: " + (applicationEventPublisher == applicationContext));
    }

    @PostConstruct
    public void initByLookup() {
        getBean(BeanFactory.class);
        getBean(ApplicationContext.class);
        getBean(ResourceLoader.class);
        getBean(ApplicationEventPublisher.class);
    }


    public <T> T getBean(Class<T> requiredType) throws BeansException {
        try {
            return beanFactory.getBean(requiredType);
        } catch (NoSuchBeanDefinitionException e) {
            System.err.println("current type [ " + requiredType.getName() + " ] does not have BeanDefinition in BeanFactory");
        }
        return null;
    }

    public static void main(String[] args) {

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //注册
        applicationContext.register(DependencySourceDemo.class);
        //刷新
        applicationContext.refresh();


        //关闭
        applicationContext.close();


    }
}
