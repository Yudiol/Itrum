package com.yudiol.itrum.HW.Core.first;

public class StringBuilder {

    public String str = "";

    public void append(String str) {
        this.str += str;
    }

    public Memento save() {
        return new Memento(str);
    }

    public void undo(Memento memento) {
        str = memento.str;
    }

    @Override
    public String toString() {
        return str;
    }
}
