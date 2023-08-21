package cn.beichenhpy.factory;

import cn.beichenhpy.domain.User;
import org.springframework.beans.factory.FactoryBean;

import java.util.Random;

/**
 * FactoryBean实例
 */
public class UserFactoryBean implements FactoryBean<User> {
    @Override
    public User getObject() throws Exception {
        return new User(new Random().nextInt(), "user-factory-bean");
    }

    @Override
    public Class<?> getObjectType() {
        return User.class;
    }
}
