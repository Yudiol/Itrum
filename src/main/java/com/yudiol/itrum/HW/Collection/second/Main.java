package com.yudiol.itrum.HW.Collection.second;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Integer[] str = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 3, 5, 7, 9};
        System.out.println(getMAp(str));
    }

    public static Map<Object, Integer> getMAp(Object[] objects) {
        Map<Object, Integer> map = new HashMap<>();
        for (Object object : objects) {
            map.put(object, map.getOrDefault(object, 0) + 1);
        }
        return map;
    }
}
