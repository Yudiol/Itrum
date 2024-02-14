package com.yudiol.itrum.HW.Collection.first;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        Filter filter = new FilterImpl();
        String[] str = {"0","1","2"};
        System.out.println(Arrays.toString(filter(str,filter)));

    }

    public static Object[] filter(Object[] objects, Filter filter) {
        Object[] processedObjects = new Object[objects.length];
        for (int i = 0; i < processedObjects.length; i++) {
            processedObjects[i] = filter.apply(objects[i]);
        }
        return processedObjects;
    }
}
