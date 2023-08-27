package cn.beichenhpy.aop.proxy.interceptor;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public interface BeforeInterceptor extends Interceptor {

    /**
     * 前置方法
     */
    Object before(Object originTarget, Method method, Object[] args) throws InvocationTargetException, IllegalAccessException;
}
