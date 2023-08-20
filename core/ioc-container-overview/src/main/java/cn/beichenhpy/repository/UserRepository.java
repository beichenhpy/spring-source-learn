package cn.beichenhpy.repository;

import cn.beichenhpy.domain.User;
import org.springframework.beans.factory.BeanFactory;

import java.util.List;

/**
 * 用户信息仓库
 */
public class UserRepository {

    private List<User> users;

    private BeanFactory beanFactory;
    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public BeanFactory getBeanFactory() {
        return beanFactory;
    }

    @Override
    public String toString() {
        return "UserRepository{" +
                "users=" + users +
                '}';
    }
}
