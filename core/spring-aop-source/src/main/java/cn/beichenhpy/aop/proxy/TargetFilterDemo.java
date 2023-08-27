package cn.beichenhpy.aop.proxy;

import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;

public class TargetFilterDemo {

    public static void main(String[] args) throws ClassNotFoundException {
        //找到类中的名称为echo的方法
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        String targetClassName = "cn.beichenhpy.aop.proxy.ExtendEchoService";
        Class<?> clazz = classLoader.loadClass(targetClassName);
        //输入 类名 方法名 方法参数类型 支持向上查询
        Method method = ReflectionUtils.findMethod(clazz, "echo", String.class);
        System.out.println(method);
        //匹配某个条件 查询到具体的方法 支持多个 比如 方法上存在注解Deprecated
        ReflectionUtils.doWithMethods(clazz, new ReflectionUtils.MethodCallback() {
            @Override
            public void doWith(Method method) throws IllegalArgumentException, IllegalAccessException {
                System.out.println("命中了标记为Deprecated注解的echo");
            }
        }, new ReflectionUtils.MethodFilter() {
            @Override
            public boolean matches(Method method) {
                Deprecated annotation = method.getAnnotation(Deprecated.class);
                return annotation != null;
            }
        });
    }
}
