package cn.beichenhpy.event;


import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.event.ApplicationContextEvent;
import org.springframework.context.event.ContextRefreshedEvent;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * spring 事件上下文传播
 */
public class HierarchicalSpringEventPropagateDemo {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext parentContext = new AnnotationConfigApplicationContext();
        parentContext.setId("parent-context");
        AnnotationConfigApplicationContext currentContext = new AnnotationConfigApplicationContext();
        currentContext.setId("current-context");
        currentContext.setParent(parentContext);

        currentContext.register(MyApplicationListener.class);
        parentContext.register(MyApplicationListener.class);

        parentContext.refresh();
        currentContext.refresh();


    }



    static class MyApplicationListener implements ApplicationListener<ApplicationContextEvent> {

        private static final Set<String> PROCESS_SET = new LinkedHashSet<>();

        @Override
        public void onApplicationEvent(ApplicationContextEvent event) {
            if (PROCESS_SET.add(event.getApplicationContext().getId())) {
                System.out.printf("context: [ %s ]\n", event.getApplicationContext().getId());
            }
        }
    }
}
