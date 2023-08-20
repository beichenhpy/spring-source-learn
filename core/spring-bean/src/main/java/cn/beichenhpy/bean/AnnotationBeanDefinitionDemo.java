package cn.beichenhpy.bean;

import cn.beichenhpy.domain.User;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Arrays;

/**
 * 注解beanDefinition注册
 */

//这里使用import注入的bean，如果未设置beanName则默认为全限定类名 FullyQualifiedAnnotationBeanNameGenerator
@Import({AnnotationBeanDefinitionDemo.InnerConfiguration.class})
public class AnnotationBeanDefinitionDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //注册configuration class
        applicationContext.register(AnnotationBeanDefinitionDemo.class);
        //1. 有beanName 使用Api
        registryBeanDefinition(applicationContext, "apiUser", User.class);
        //2. 无beanName 使用api
        registryBeanDefinition(applicationContext, null, User.class);
        applicationContext.refresh();
        //1. 通过@Bean方式
        //2. 通过@Component方式
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        System.out.println(Arrays.toString(beanDefinitionNames));
        applicationContext.close();

    }


    public static void registryBeanDefinition(BeanDefinitionRegistry registry, String beanName, Class<?> beanClass) {
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(beanClass);
        beanDefinitionBuilder
                .addPropertyValue("id", 2)
                .addPropertyValue("name", "api注册");
        //获取beanDefinition
        AbstractBeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();
        if (StringUtils.hasText(beanName)) {
            registry.registerBeanDefinition(beanName, beanDefinition);
        } else {
            BeanDefinitionReaderUtils.registerWithGeneratedName(beanDefinition, registry);
        }
    }


    @Component
    public static class InnerConfiguration {

        /**
         * 在component注解下的Bean是lite mode 无法使用cglib代理
         *
         * @return
         */
        @Bean(name = {"user", "anno-user"})
        public User user() {
            User user = new User();
            user.setId(2);
            user.setName("bean方式");
            return user;
        }
    }


}
