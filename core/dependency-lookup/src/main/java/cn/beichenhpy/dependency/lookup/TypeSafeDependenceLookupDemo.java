package cn.beichenhpy.dependency.lookup;

import cn.beichenhpy.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 类型安全的查找示例
 */
public class TypeSafeDependenceLookupDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //注册当前类为配置类
        applicationContext.register(TypeSafeDependenceLookupDemo.class);
        //启动容器
        applicationContext.refresh();

        displayBeanFactoryGetBean(applicationContext);
        displayObjectFactoryGetObject(applicationContext);
        displayObjectProviderGetIfAvailable(applicationContext);

        //集合依赖查找
        displayListableBeanFactoryGetNamesOfType(applicationContext);
        displayObjectProviderStreamOps(applicationContext);

        //关闭容器
        applicationContext.close();
    }

    private static void displayObjectFactoryGetObject(AnnotationConfigApplicationContext applicationContext) {
        printException(
                "displayObjectFactoryGetObject",
                () -> {
                    ObjectFactory<User> userObjectFactory = applicationContext.getBeanProvider(User.class);
                    User object = userObjectFactory.getObject();
                    System.out.println(object);
                }
        );
    }

    private static void displayObjectProviderGetIfAvailable(AnnotationConfigApplicationContext applicationContext) {
        printException(
                "displayObjectProviderGetIfAvailable",
                () -> {
                    ObjectProvider<User> userObjectProvider = applicationContext.getBeanProvider(User.class);
                    User object = userObjectProvider.getIfAvailable();
                }
        );
    }


    public static void displayBeanFactoryGetBean(BeanFactory beanFactory) {
        printException("displayBeanFactoryGetBean" ,() -> beanFactory.getBean(User.class));
    }


    public static void displayListableBeanFactoryGetNamesOfType(ListableBeanFactory listableBeanFactory) {
        printException(
                "displayListableBeanFactoryGetNamesOfType",
                () -> {
                    String[] beanNamesForType = listableBeanFactory.getBeanNamesForType(User.class);
                }
        );
    }

    public static void displayObjectProviderStreamOps(ListableBeanFactory listableBeanFactory) {
        printException(
                "displayStreamOps",
                () -> {
                    ObjectProvider<User> beanProvider = listableBeanFactory.getBeanProvider(User.class);
                    beanProvider.stream()
                            .forEach(System.out::println);
                }
        );
    }

    public static void printException(String source, Runnable runnable) {
        System.err.println("source from " + source);
        try {
            runnable.run();
        } catch (BeansException e) {
            e.printStackTrace();
        }
    }
}
