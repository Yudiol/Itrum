package com.yudiol.itrum.HW.Core.first;

public class Main {
    public static void main(String[] args) {


        StringBuilder stringBuilder = new StringBuilder();

        System.out.println("Append");
        stringBuilder.append("0");
        System.out.println("stringBuilder = " + stringBuilder);

        stringBuilder.append("1");
        System.out.println("stringBuilder = " + stringBuilder);

        stringBuilder.append("2");
        System.out.println("stringBuilder = " + stringBuilder);

        stringBuilder.append("3");
        System.out.println("stringBuilder = " + stringBuilder);

        stringBuilder.undo();
        System.out.println("Undo memento");
        System.out.println("stringBuilder = " + stringBuilder);

        System.out.println("Append");
        stringBuilder.append("3");
        System.out.println("stringBuilder = " + stringBuilder);

        stringBuilder.undo();
        System.out.println("Undo memento");
        System.out.println("stringBuilder = " + stringBuilder);

        stringBuilder.undo();
        System.out.println("Undo memento");
        System.out.println("stringBuilder = " + stringBuilder);

        stringBuilder.undo();
        System.out.println("Undo memento");
        System.out.println("stringBuilder = " + stringBuilder);

        stringBuilder.undo();
        System.out.println("Undo memento");
        System.out.println("stringBuilder = " + stringBuilder);

        stringBuilder.undo();
        System.out.println("Undo memento");
        System.out.println("stringBuilder = " + stringBuilder);
    }
}
