package cn.beichenhpy.dependency.injection;

import cn.beichenhpy.domain.User;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;

/**
 * UserHolder对象
 */
public class UserHolder implements InitializingBean {

    private User user;

    public UserHolder() {
    }

    public UserHolder(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        System.out.println("调用Setter方法注入");
        this.user = user;
    }


    @Override
    public String toString() {
        return "UserHolder{" +
                "user=" + user +
                '}';
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("userHolder初始化完成： " + user);
    }


    public void userPower(){
        System.out.println("user能力：" + user);
    }
}
