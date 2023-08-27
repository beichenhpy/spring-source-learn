package cn.beichenhpy.aop.proxy.interceptor;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public interface AfterInterceptor extends Interceptor{

    /**
     * 后置方法
     */
    Object after(Object originTarget, Method method, Object[] args) throws InvocationTargetException, IllegalAccessException;
}
