package cn.beichenhpy.event;

import cn.beichenhpy.domain.User;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

/**
 * spring 事件监听 注解实现
 *
 * @see org.springframework.context.ApplicationContext
 * @see ApplicationListener
 */
@EnableAsync
public class ApplicationEventByAnnotationDemo implements ApplicationEventPublisherAware {


    private static ApplicationEventPublisher applicationEventPublisher;

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        context.register(ApplicationEventByAnnotationDemo.class);
        context.register(UserApplicationListener.class);
        //启动 Spring 应用上下文
        context.refresh();

        User user = User.createUser("测试");
        applicationEventPublisher.publishEvent(new UserApplicationEvent(user));
//        context.publishEvent(new UserApplicationEvent(user));


        System.out.println(user);
        //销毁 Spring 上下文
        context.close();
    }


    @Async
    @EventListener
    public void updateUserApplicationListener(UserApplicationEvent event) {
        User user = (User) event.getSource();
        user.setName("更新用户名称");
        System.out.println(Thread.currentThread().getName() + "  事件更新  " + user);
    }

    @Order(3)
    @EventListener
    public void applicationListener1(ContextRefreshedEvent event) {
        System.out.println("程序刷新1 " + event.getTimestamp());
    }

    @Order(2)
    @EventListener
    public void applicationListener2(ContextRefreshedEvent event) {
        System.out.println("程序刷新2 " + event.getTimestamp());
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        ApplicationEventByAnnotationDemo.applicationEventPublisher = applicationEventPublisher;
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
