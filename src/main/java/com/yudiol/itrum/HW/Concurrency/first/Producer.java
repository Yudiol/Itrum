package com.yudiol.itrum.HW.Concurrency.first;

public class Producer<T> implements Runnable {
    private T t;
    private final BlockingQueue<T> queue;

    public Producer(BlockingQueue<T> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 100; i++) {
                queue.enqueue(t);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
