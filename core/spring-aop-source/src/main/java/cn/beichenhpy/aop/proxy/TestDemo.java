package cn.beichenhpy.aop.proxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class TestDemo {

    public static void main(String[] args) {
//       staticProxy();
//        javaDynamicProxy();
        cglibProxy();
    }


    public static void staticProxy() {
        EchoService echoService = new ProxyEchoService(new DefaultEchoService());
        echoService.echo("hello,world");
    }


    public static void javaDynamicProxy() {
        //保证创建动态代理的类加载器是当前类
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        Object proxyInstance = Proxy.newProxyInstance(contextClassLoader, new Class[]{EchoService.class, ExtendEchoService.class},
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        //保证是EchoService和其子类进入
                        if (method.getDeclaringClass().isAssignableFrom(EchoService.class)) {
                            System.out.println(args[0]);
                        }
                        return method.invoke(new DefaultEchoService(), args);
                    }
                });
        EchoService echoService = (EchoService) proxyInstance;
        EchoService echoService1 = (ExtendEchoService) proxyInstance;
        echoService.echo("hello,world");
        echoService1.echo("hello,world default");
    }

    public static void cglibProxy() {
        Enhancer enhancer = new Enhancer();
        enhancer.setInterfaces(new Class<?>[]{EchoService.class});
        enhancer.setSuperclass(DefaultEchoService.class);
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                System.out.println("before");
                Object invoke = methodProxy.invokeSuper(o, objects);
                System.out.println("after");
                return invoke;
            }
        });
        Object proxy = enhancer.create();

        DefaultEchoService o = (DefaultEchoService) proxy;
        EchoService e = (EchoService) proxy;
        e.echo("hello_echo");
        o.echo("hello_world");
    }

}
