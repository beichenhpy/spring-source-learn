package cn.beichenhpy.dependency.lookup;

import cn.beichenhpy.annotation.Super;
import cn.beichenhpy.domain.SuperUser;
import cn.beichenhpy.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

/**
 * 依赖查找示例
 */
public class DependencyLookUpDemo {

    public static void main(String[] args) {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("META-INF/dependency-lookup-context.xml");
        lookupInRealTimeByName(beanFactory);
        lookupLazyByName(beanFactory);
        //通过类型查找
        lookupByType(beanFactory);
        //通过类型查找集合
        lookupCollectionByType(beanFactory);
        //通过注解查找
        lookupByAnnotation(beanFactory);
    }

    private static void lookupByAnnotation(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            Map<String, SuperUser> users = (Map)((ListableBeanFactory) beanFactory).getBeansWithAnnotation(Super.class);
            System.out.println("按注解实时查找：" + users);
        }
    }

    private static void lookupCollectionByType(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            Map<String, User> users = ((ListableBeanFactory) beanFactory).getBeansOfType(User.class);
            System.out.println("实时查找集合：" + users);
        }
    }

    private static void lookupByType(BeanFactory beanFactory) {
        User user = beanFactory.getBean(User.class);
        System.out.println("实时查找(按类型): " + user);
    }

    /**
     * 实时查找
     * @param beanFactory 工厂
     */
    private static void lookupInRealTimeByName(BeanFactory beanFactory) {
        User user = (User) beanFactory.getBean("user");
        System.out.println("实时查找(按名称)：" + user);
    }

    /**
     * 延迟查找
     * @param beanFactory 工厂
     */
    private static void lookupLazyByName(BeanFactory beanFactory) {
        ObjectFactory<User> userObjectFactory = (ObjectFactory<User>) beanFactory.getBean("userObjectFactory");
        User user = userObjectFactory.getObject();
        System.out.println("延迟查找(按名称)：" + user);
    }

}
