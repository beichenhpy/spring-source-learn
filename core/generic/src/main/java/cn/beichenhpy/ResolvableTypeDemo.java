package cn.beichenhpy;

import org.springframework.core.ResolvableType;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResolvableTypeDemo {

    private static Map<Integer, HashMap<String, List<String>>> map;

    public static void main(String[] args) throws NoSuchFieldException {

        ResolvableType resolvableType = ResolvableType.forField(ResolvableTypeDemo.class.getDeclaredField("map"));

        //Integer
        System.out.println(resolvableType.getGeneric(0));
        //Map<String, List<String>>
        System.out.println(resolvableType.getGeneric(1).asMap());
        //java.lang.String
        System.out.println(resolvableType.getGeneric(1, 0));
        //java.util.List<java.lang.String>
        System.out.println(resolvableType.getGeneric(1, 1));
        //java.lang.String
        System.out.println(resolvableType.getGeneric(1, 1, 0));
    }
}
