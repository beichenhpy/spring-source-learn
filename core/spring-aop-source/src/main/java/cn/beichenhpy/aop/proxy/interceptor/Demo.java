package cn.beichenhpy.aop.proxy.interceptor;

import cn.beichenhpy.aop.proxy.DefaultEchoService;
import cn.beichenhpy.aop.proxy.EchoService;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Demo {
    public static void main(String[] args) {

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        DefaultEchoService defaultEchoService = new DefaultEchoService();

        Object proxy = Proxy.newProxyInstance(classLoader, new Class[]{EchoService.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                try {
                    System.out.println(new BeforeInterceptor() {
                        @Override
                        public Object before(Object originTarget, Method method, Object[] args) throws InvocationTargetException, IllegalAccessException {

                            return "前置执行";
                        }
                    }.before(defaultEchoService, method, args));
                    return method.invoke(defaultEchoService, args);
                } catch (Throwable e) {
                    System.out.println(new AfterThrowingInterceptor() {
                        @Override
                        public Object afterThrowing(Object originTarget, Method method, Object[] args) throws InvocationTargetException, IllegalAccessException {
                            return "异常抛出";
                        }
                    }.afterThrowing(defaultEchoService, method, args));
                } finally {
                    System.out.println(new AfterInterceptor() {
                        @Override
                        public Object after(Object originTarget, Method method, Object[] args) throws InvocationTargetException, IllegalAccessException {
                            return "后置执行";
                        }
                    }.after(defaultEchoService, method, args));
                }
                return null;
            }
        });

        EchoService echoService = (EchoService) proxy;
        echoService.echo("hello_world");

    }
}
