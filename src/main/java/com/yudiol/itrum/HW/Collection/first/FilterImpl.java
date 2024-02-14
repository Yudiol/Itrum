package com.yudiol.itrum.HW.Collection.first;

public class FilterImpl implements Filter {
    @Override
    public Object apply(Object object) {
        return object + "!";
    }
}
