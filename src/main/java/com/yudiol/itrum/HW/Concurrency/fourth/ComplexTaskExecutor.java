package com.yudiol.itrum.HW.Concurrency.fourth;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ComplexTaskExecutor {

    private final int counter;

    public ComplexTaskExecutor(int counter) {
        this.counter = counter;

    }

    public void executeTasks(int i) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(counter);
        ExecutorService executorService = Executors.newFixedThreadPool(i);

        for (int j = 0; j < i; j++) {
            executorService.submit(new ComplexTask(cyclicBarrier));
        }
        executorService.shutdown();
    }
}
