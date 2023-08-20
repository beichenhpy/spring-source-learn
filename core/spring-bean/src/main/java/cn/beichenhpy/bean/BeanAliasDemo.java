package cn.beichenhpy.bean;

import cn.beichenhpy.domain.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Bean别名示例
 */
public class BeanAliasDemo {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/META-INF/dependency-bean-context.xml");
        User aliasUser = applicationContext.getBean("alias-user", User.class);
        User user = applicationContext.getBean("user", User.class);
        //true
        System.out.println(user == aliasUser);
    }
}
