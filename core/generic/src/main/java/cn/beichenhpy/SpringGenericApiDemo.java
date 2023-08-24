package cn.beichenhpy;


import org.springframework.core.GenericTypeResolver;
import org.springframework.core.ResolvableType;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.*;

/**
 * Spring {@link org.springframework.core.GenericTypeResolver} 示例
 */
public class SpringGenericApiDemo {

    public static void main(String[] args) throws NoSuchMethodException {

        Method getListMethod = SpringGenericApiDemo.class.getMethod("getList", List.class);

        Class<?> returnType = GenericTypeResolver.resolveReturnType(getListMethod, SpringGenericApiDemo.class);
        // interface java.util.List
        System.out.println("returnType: " + returnType);
        Class<?> returnTypeArgument = GenericTypeResolver.resolveReturnTypeArgument(getListMethod, List.class);
        // class java.lang.String
        System.out.println("returnTypeArgument: " + returnTypeArgument);

        Map<String, Integer> map = new HashMap<>();
        List<String> list = new ArrayList<>();

        Class<?>[] mapTypeArguments = GenericTypeResolver.resolveTypeArguments(list.getClass(), ArrayList.class);
        System.out.println(Arrays.toString(mapTypeArguments));

    }




    public static List<String> getList(List<String> list) {
        return null;
    }
}
