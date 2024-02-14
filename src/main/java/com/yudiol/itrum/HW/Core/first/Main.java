package com.yudiol.itrum.HW.Core.first;

public class Main {
    public static void main(String[] args) {


        CareTaker careTaker = new CareTaker();
        java.lang.StringBuilder stringBuilder = new java.lang.StringBuilder();

        stringBuilder.append("0");
        System.out.println("stringBuilder = " + stringBuilder);

        stringBuilder.append("1");
        System.out.println("stringBuilder = " + stringBuilder);


        System.out.println("Save memento");
        careTaker.setMemento(stringBuilder.save());

        stringBuilder.append("2");
        System.out.println("stringBuilder = " + stringBuilder);

        careTaker.setMemento(stringBuilder.save());
        System.out.println("Save memento");

        stringBuilder.append("3");
        System.out.println("stringBuilder = " + stringBuilder);

        stringBuilder.undo(careTaker.getMemento());
        System.out.println("Undo memento");
        System.out.println("stringBuilder = " + stringBuilder);

        stringBuilder.undo(careTaker.getMemento());
        System.out.println("Undo memento");
        System.out.println("stringBuilder = " + stringBuilder);
    }
}
