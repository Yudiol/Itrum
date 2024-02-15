package com.yudiol.itrum.HW.Core.first;

public class StringBuilder {

    public String str = "";

    private final CareTaker careTaker = new CareTaker();

    public void append(String str) {
        save();
        this.str += str;
    }

    private void save() {
         careTaker.addMemento(new Memento(str));
    }

    public void undo() {
        this.str = careTaker.getMemento().str;
    }

    @Override
    public String toString() {
        return str;
    }
}
