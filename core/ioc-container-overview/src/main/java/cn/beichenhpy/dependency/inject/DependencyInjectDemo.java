package cn.beichenhpy.dependency.inject;

import cn.beichenhpy.repository.UserRepository;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 依赖注入示例
 */
public class DependencyInjectDemo {

    public static void main(String[] args) {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("META-INF/dependency-inject-context.xml");
        UserRepository userRepository = beanFactory.getBean(UserRepository.class);
        System.out.println(userRepository);
        System.out.println(userRepository.getBeanFactory());
        System.out.println(userRepository.getBeanFactory() == beanFactory);

    }


}
