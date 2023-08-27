package cn.beichenhpy.aop.proxy.interceptor;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public interface AfterThrowingInterceptor extends Interceptor{

    /**
     * 异常
     */
    Object afterThrowing(Object originTarget, Method method, Object[] args) throws InvocationTargetException, IllegalAccessException;
}
