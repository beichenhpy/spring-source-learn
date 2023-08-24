package cn.beichenhpy.event;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.event.ApplicationContextEvent;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.SimpleApplicationEventMulticaster;

/**
 * 错误处理
 */
public class ErrorEventDemo {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        context.register(ErrorEventDemo.class);


        //启动 Spring 应用上下文
        context.refresh();

        SimpleApplicationEventMulticaster applicationEventMulticaster = (SimpleApplicationEventMulticaster) context.getBean("applicationEventMulticaster", ApplicationEventMulticaster.class);

        applicationEventMulticaster.setErrorHandler(System.err::println);


        applicationEventMulticaster.addApplicationListener(new ApplicationListener<ApplicationContextEvent>() {
            @Override
            public void onApplicationEvent(ApplicationContextEvent event) {
                int i = 1 / 0;
            }
        });

        context.close();
    }
}
