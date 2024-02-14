package com.yudiol.itrum.HW.Concurrency.fourth;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class ComplexTask implements Runnable {
    private final CyclicBarrier cyclicBarrier;

    public ComplexTask(CyclicBarrier cyclicBarrier) {
        this.cyclicBarrier = cyclicBarrier;
    }

    public void execute() {
        System.out.println("Задача выполнилась и ждёт " + Thread.currentThread().getName());
        try {
            cyclicBarrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
        System.out.println("Все задачи выполнились");
    }

    @Override
    public void run() {
        execute();
    }
}
