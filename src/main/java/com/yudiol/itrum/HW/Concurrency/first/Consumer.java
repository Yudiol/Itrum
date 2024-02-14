package com.yudiol.itrum.HW.Concurrency.first;

public class Consumer<T> implements Runnable {

    private final BlockingQueue<T> queue;

    public Consumer(BlockingQueue<T> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 100; i++) {
                queue.dequeue();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
