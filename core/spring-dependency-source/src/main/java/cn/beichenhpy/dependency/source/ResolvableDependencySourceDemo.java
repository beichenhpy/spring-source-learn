package cn.beichenhpy.dependency.source;


import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.ResourceLoader;

import javax.annotation.PostConstruct;

/**
 * ResolvableDependency依赖来源示例
 */
public class ResolvableDependencySourceDemo {

    @Autowired
    private String value;


    @PostConstruct
    public void init() {
        System.out.println(value);
    }

    public static void main(String[] args) {

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //注册
        applicationContext.register(ResolvableDependencySourceDemo.class);

        //添加BeanFactoryPostProcessor
        applicationContext.addBeanFactoryPostProcessor(beanFactory -> {
            beanFactory.registerResolvableDependency(String.class, "hello_world");
        });
        //刷新
        applicationContext.refresh();

        //关闭
        applicationContext.close();


    }
}
