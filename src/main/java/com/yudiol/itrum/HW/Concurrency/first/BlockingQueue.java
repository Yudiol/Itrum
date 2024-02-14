package com.yudiol.itrum.HW.Concurrency.first;

import java.util.Stack;

public class BlockingQueue<T> {

    Stack<T> stack = new Stack<>();

    public synchronized void enqueue(T t) throws InterruptedException {
        while (stack.size() >= 20) {
            wait();
        }
        System.out.println("Add - 1 size = " + stack.size());
        stack.push(t);
        notifyAll();
    }

    public synchronized T dequeue() throws InterruptedException {
        while (stack.size() <= 0) {
            wait();
        }
        System.out.println("Remove - 1 size = " + stack.size());
        T t = stack.pop();
        notifyAll();

        return t;
    }
}
