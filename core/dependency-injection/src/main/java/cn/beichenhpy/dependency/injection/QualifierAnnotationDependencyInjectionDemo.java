package cn.beichenhpy.dependency.injection;

import cn.beichenhpy.dependency.injection.annotation.UserGroup;
import cn.beichenhpy.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;
import java.util.List;

/**
 * 注解{@link Qualifier}注入测试
 */
public class QualifierAnnotationDependencyInjectionDemo {

    /**
     * 存在4个user的bean :
     * user superUser
     * user1 user2 (Qualifier)
     */

    @Autowired
    @Qualifier(value = "user")
    private User user;

    @Autowired
    @Qualifier(value = "superUser")
    private User sUser;

    @Autowired
    private List<User> allUsers; //user + superUser

    @Autowired
    @Qualifier
    private List<User> qualifiedUsers; // user1 + user2 + userMember1 + userMember2

    @Autowired
    @UserGroup
    private List<User> userGroupMembers;

    @Bean
    @Qualifier
    public User user1() {
        return new User(2, "user1");
    }

    @Bean
    @Qualifier
    public User user2() {
        return new User(1, "user2");
    }

    @Bean
    @UserGroup
    public User userMember1() {
        return new User(3, "userMember1");
    }

    @Bean
    @UserGroup
    public User userMember2() {
        return new User(4, "userMember2");
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(QualifierAnnotationDependencyInjectionDemo.class);

        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(applicationContext);
        //加载xml资源
        String xmlPath = "classpath:/META-INF/dependency-lookup-context.xml";
        reader.loadBeanDefinitions(xmlPath);

        //刷新
        applicationContext.refresh();

        QualifierAnnotationDependencyInjectionDemo bean = applicationContext.getBean(QualifierAnnotationDependencyInjectionDemo.class);
        User user = bean.user;
        User superUser = bean.sUser;
        System.out.println(user);
        System.out.println(superUser);

        List<User> allUsers = bean.allUsers;
        System.out.println("allUsers大小: " + allUsers.size() + allUsers);
        List<User> qualifiedUsers = bean.qualifiedUsers;
        System.out.println("qualifierUsers大小: " + qualifiedUsers.size() + qualifiedUsers);


        List<User> userGroupMembers = bean.userGroupMembers;
        System.out.println(userGroupMembers);
        applicationContext.close();
    }

}
