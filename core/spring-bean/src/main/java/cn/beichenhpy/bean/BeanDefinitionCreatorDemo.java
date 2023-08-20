package cn.beichenhpy.bean;


import cn.beichenhpy.domain.User;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.GenericBeanDefinition;

/**
 * 构建BeanDefinition
 */
public class BeanDefinitionCreatorDemo {

    public static void main(String[] args) {
        //1 通过BeanDefinitionBuilder创建
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        // 通过属性配置
        beanDefinitionBuilder.addPropertyValue("id", 1);
        beanDefinitionBuilder.addPropertyValue("name", "测试");
        // 获取实例
        BeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();
        //BeanDefinition并非终态，可以修改
        //2 通过AbstractBeanDefinition以及派生类
        AbstractBeanDefinition genericBeanDefinition = new GenericBeanDefinition();
        genericBeanDefinition.setBeanClass(User.class);
        MutablePropertyValues mutablePropertyValues = new MutablePropertyValues();
        mutablePropertyValues.addPropertyValue("id", 2);
        mutablePropertyValues.addPropertyValue("name", "GenericBeanDefinition");
        genericBeanDefinition.setPropertyValues(mutablePropertyValues);

    }
}
