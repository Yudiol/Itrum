package com.yudiol.itrum.HW.Concurrency.fourth;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Starter {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3, new Run());
        new Sportsman(cyclicBarrier);
        new Sportsman(cyclicBarrier);
        new Sportsman(cyclicBarrier);

    }

    public static class Run extends Thread {
        @Override
        public void run() {
            System.out.println("Побежали!!!");
        }
    }

    public static class Sportsman extends Thread {

        private final CyclicBarrier cyclicBarrier;

        public Sportsman(CyclicBarrier cyclicBarrier) {
            this.cyclicBarrier = cyclicBarrier;
            start();
        }

        @Override
        public void run() {
            try {
                System.out.println("Ждём");
                cyclicBarrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }
}
