package com.yudiol.itrum.HW.Concurrency.first;

public class Main {
    public static void main(String[] args) {

        BlockingQueue<Integer> queue = new BlockingQueue<>();
        Consumer<Integer> consumer = new Consumer<>(queue);
        Producer<Integer> producer = new Producer<>(queue);
        Thread thread1 = new Thread(consumer);
        Thread thread2 = new Thread(producer);

        thread1.start();
        thread2.start();

    }
}
