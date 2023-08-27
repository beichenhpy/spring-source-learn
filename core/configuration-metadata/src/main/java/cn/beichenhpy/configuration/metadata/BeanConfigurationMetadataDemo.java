package cn.beichenhpy.configuration.metadata;


import cn.beichenhpy.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.util.ObjectUtils;

/**
 * Bean元信息配置demo
 */
public class BeanConfigurationMetadataDemo {

    public static void main(String[] args) {
        //BeanDefinition声明
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        //属性赋值
        beanDefinitionBuilder.addPropertyValue("name", "propertyValue");
        AbstractBeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();

        //设置attribute
        beanDefinition.setAttribute("name", "beichenhpy");
        beanDefinition.setSource(BeanConfigurationMetadataDemo.class);

        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        //设置BeanPostProcessor利用 attribute 和 source 扩展
        factory.addBeanPostProcessor(new BeanPostProcessor() {
            @Override
            public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
                if (ObjectUtils.nullSafeEquals(beanName, "user") && User.class.equals(bean.getClass())) {
                    BeanDefinition bd = factory.getBeanDefinition("user");
                    if (ObjectUtils.nullSafeEquals(BeanConfigurationMetadataDemo.class, bd.getSource())) {
                        User user = (User) bean;
                        user.setName(String.valueOf(bd.getAttribute("name")));
                    }
                }
                return bean;
            }
        });
        //注册BeanDefinition
        factory.registerBeanDefinition("user", beanDefinition);
        User user = factory.getBean("user", User.class);
        System.out.println(user);


    }
}
