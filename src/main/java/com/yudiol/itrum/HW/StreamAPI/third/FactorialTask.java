package com.yudiol.itrum.HW.StreamAPI.third;

import java.util.concurrent.RecursiveTask;

public class FactorialTask extends RecursiveTask<Long> {

    private final int number;

    public FactorialTask(Integer number) {
        this.number = number;
    }

    @Override
    protected Long compute() {
        if (number <= 1) return 1L;
        FactorialTask ft = new FactorialTask(number - 1);
        ft.fork();
        return number * ft.join();
    }
}
