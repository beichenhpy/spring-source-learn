package cn.beichenhpy.event;

import cn.beichenhpy.domain.User;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.GenericApplicationContext;

/**
 * spring 事件监听 接口实现
 *
 * @see org.springframework.context.ApplicationContext
 * @see org.springframework.context.ApplicationListener
 */
public class ApplicationEventByInterfaceDemo {


    public static void main(String[] args) {

        GenericApplicationContext genericApplicationContext = new GenericApplicationContext();

        genericApplicationContext.addApplicationListener(new UserApplicationListener());

        //启动 Spring 应用上下文
        genericApplicationContext.refresh();

        genericApplicationContext.publishEvent(new UserApplicationEvent(User.createUser("事件测试")));
        //销毁 Spring 上下文
        genericApplicationContext.close();
    }


    static class UserApplicationListener implements ApplicationListener<UserApplicationEvent> {

        @Override
        public void onApplicationEvent(UserApplicationEvent event) {
            System.out.println("执行事件时间戳： " + event.getTimestamp());
            System.out.println(event.getSource());
        }
    }


    static class UserApplicationEvent extends ApplicationEvent {

        public UserApplicationEvent(User source) {
            super(source);
        }
    }
}
