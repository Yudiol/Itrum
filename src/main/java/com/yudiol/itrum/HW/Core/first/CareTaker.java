package com.yudiol.itrum.HW.Core.first;

import java.util.Stack;

public class CareTaker {

    Stack<Memento> list = new Stack<>();

    public Memento getMemento() {
        return list.pop();
    }

    public void setMemento(Memento memento) {
        list.push(memento);
    }
}
