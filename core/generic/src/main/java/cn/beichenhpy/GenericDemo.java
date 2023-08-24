package cn.beichenhpy;

import java.util.ArrayList;
import java.util.Collection;

public class GenericDemo {
    public static void main(String[] args) {

        Collection<String> list = new ArrayList<>();
        list.add("1");
        list.add("1");

        Collection tmp = list;
        //编译通过
        tmp.add(1);
        System.out.println(list);

    }
}
