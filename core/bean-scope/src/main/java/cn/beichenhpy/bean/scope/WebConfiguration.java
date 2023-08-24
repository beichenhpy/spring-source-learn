package cn.beichenhpy.bean.scope;

import cn.beichenhpy.domain.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.annotation.ApplicationScope;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@Configuration
public class WebConfiguration {



    @Bean(value = "requestUser")
    @RequestScope
    public User requestUser() {
        return User.createUser("requestUser");
    }

    @Bean(value = "sessionUser")
    @SessionScope
    public User sessionUser() {
        return User.createUser("sessionUser");
    }

    @Bean(value = "applicationUser")
    @ApplicationScope
    public User applicationUser() {
        return User.createUser("applicationUser");
    }


    @Bean(value = "singletonUser")
    public User singletonUser(){
        return User.createUser("singletonUser");
    }

}
