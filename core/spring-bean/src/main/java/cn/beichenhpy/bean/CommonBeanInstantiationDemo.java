package cn.beichenhpy.bean;

import cn.beichenhpy.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Bean实例化普通实现
 */
public class CommonBeanInstantiationDemo {
    public static void main(String[] args) {

        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/dependency-instantiation-context.xml");

        User staticCreateUser = beanFactory.getBean("static-create-user", User.class);
        User beanFactoryUser = beanFactory.getBean("bean-factory-user", User.class);
        User factoryBeanUser = beanFactory.getBean("factory-bean-user", User.class);
        System.out.println(staticCreateUser);
        System.out.println(beanFactoryUser);
        System.out.println(factoryBeanUser);
        //false
        System.out.println(factoryBeanUser == staticCreateUser);
        //false
        System.out.println(factoryBeanUser == beanFactoryUser);

    }
}
