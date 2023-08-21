package cn.beichenhpy.factory;

import org.springframework.beans.factory.serviceloader.ServiceLoaderFactoryBean;

public class UserFactoryServiceLoaderFactoryBean extends ServiceLoaderFactoryBean {

    @Override
    public Class<?> getServiceType() {
        return UserBeanFactory.class;
    }
}
