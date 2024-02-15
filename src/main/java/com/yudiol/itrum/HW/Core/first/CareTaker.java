package com.yudiol.itrum.HW.Core.first;

import java.util.Stack;

public class CareTaker {

    Stack<Memento> list = new Stack<>();

    public Memento getMemento() {
        if (!list.isEmpty()) {
            return list.pop();
        } else {
            return new Memento("");
        }
    }

    public void addMemento(Memento memento) {
        list.push(memento);
    }
}
