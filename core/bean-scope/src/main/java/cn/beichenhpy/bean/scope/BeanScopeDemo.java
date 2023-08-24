package cn.beichenhpy.bean.scope;


import cn.beichenhpy.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.List;

/**
 * Bean作用域示例
 */
@Configuration
public class BeanScopeDemo {



    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
    public static User singletonUser() {
        return User.createUser("singletonUser");
    }


    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public static User prototypeUser() {
        return User.createUser("prototypeUser");
    }

    @Autowired
    @Qualifier("singletonUser")
    private User singletonUser;

    @Autowired
    @Qualifier("prototypeUser")
    private User prototypeUser;

    @Autowired
    @Qualifier("prototypeUser")
    private User prototypeUser1;

    @Autowired
    private List<User> users;


    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(BeanScopeDemo.class);

        applicationContext.addBeanFactoryPostProcessor(beanFactory -> {
            beanFactory.addBeanPostProcessor(new BeanPostProcessor() {
                @Override
                public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
                    return bean;
                }
            });
        });


        applicationContext.refresh();

        //依赖查找
        System.out.println("===================依赖查找===================");
        for (int i = 0; i < 3; i++) {
            User singletonUser = applicationContext.getBean("singletonUser", User.class);
            User prototypeUser = applicationContext.getBean("prototypeUser", User.class);
            System.out.println("singleton: " + singletonUser);
            System.out.println("prototype: " + prototypeUser);
        }
        //依赖注入
        System.out.println("===================依赖注入===================");
        BeanScopeDemo bean = applicationContext.getBean(BeanScopeDemo.class);
        User singletonUser1 = bean.singletonUser;
        User prototypeUser1 = bean.prototypeUser;
        User prototypeUser2 = bean.prototypeUser1;
        System.out.println(singletonUser1);
        System.out.println(prototypeUser1);
        System.out.println(prototypeUser2);

        //集合注入
        System.out.println("===================集合依赖注入===================");
        List<User> users1 = bean.users;
        System.out.println(users1);



        applicationContext.close();

    }
}
