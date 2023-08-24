package cn.beichenhpy;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * 泛型api demo
 */
public class GenericApiDemo {

    public static void main(String[] args) {

        // 原生类型 int long float
        Class intClass = int.class;

        // 数组类型 int[] object[]
        Class objArrClass = Object[].class;

        // 原始类型 raw type string
        Class rawClass = String.class;

        //泛型参数类型 ParameterizedType ---> java.util.AbstractList<E>
        ParameterizedType parameterizedType = (ParameterizedType) ArrayList.class.getGenericSuperclass();
        System.out.println(parameterizedType);
        // parameterizedType.getRawType() ---> class java.util.AbstractList
        Type rawType = parameterizedType.getRawType();
        System.out.println(rawType);
        //getActualTypeArguments --->  E TypeVariable
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        System.out.println(Arrays.toString(actualTypeArguments));

    }
}
