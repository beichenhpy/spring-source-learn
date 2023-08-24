package cn.beichenhpy.bean.lifecycle;


import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotatedBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 解析注解为BeanDefinition示例
 */
public class AnnotationBeanDefinitionParserDemo {

    public static void main(String[] args) {
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        //解析注解为BeanDefinition
        AnnotatedBeanDefinitionReader reader = new AnnotatedBeanDefinitionReader(factory);
        int beforeRegisterCount = factory.getBeanDefinitionCount();
        //注册
        reader.register(AnnotationBeanDefinitionParserDemo.class);

        int afterRegisterCount = factory.getBeanDefinitionCount();
        System.out.println("注册了" + (afterRegisterCount - beforeRegisterCount) + "个bean");
        AnnotationBeanDefinitionParserDemo demo = factory.getBean("annotationBeanDefinitionParserDemo",
                AnnotationBeanDefinitionParserDemo.class);
        System.out.println(demo);



    }
}
