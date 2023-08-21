package cn.beichenhpy.factory;

import cn.beichenhpy.domain.User;

/**
 * 用户bean抽象工厂
 */
public interface UserBeanFactory {

    User createUser(String name);
}
