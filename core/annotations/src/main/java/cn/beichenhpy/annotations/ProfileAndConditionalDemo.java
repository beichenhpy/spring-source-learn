package cn.beichenhpy.annotations;

import org.springframework.context.annotation.*;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;

/**
 * {@link org.springframework.context.annotation.Profile} 演示
 * @see Environment#getActiveProfiles()
 */
@Configuration
public class ProfileAndConditionalDemo {

    @Bean("number")
    @Profile("odd")
    public Integer odd() {
        return 1;
    }

    @Bean("number")
//    @Profile("even")
    @Conditional(EvenNumberCondition.class)
    public Integer even() {
        return 2;
    }


    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //注册当前为配置类
        applicationContext.register(ProfileAndConditionalDemo.class);

        ConfigurableEnvironment environment = applicationContext.getEnvironment();
        //默认 profiles
        environment.setDefaultProfiles("odd");
        //增加 激活的profiles
        environment.setActiveProfiles("even");

        //容器实例化
        applicationContext.refresh();

        Integer number = applicationContext.getBean("number", Integer.class);
        System.out.println(number);

        //容器关闭
        applicationContext.close();

    }
}
