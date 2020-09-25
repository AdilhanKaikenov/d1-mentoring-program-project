package com.epam.mentoring.java8.task2;

@FunctionalInterface
public interface MyCustomInterface {

    // abstract method
    int apply(int arg);

    // default method
    // we can override them in the implementation classes
    default void info() {
        System.out.println("Info of MyCustomInterface");
    }

    // default method
    default void show() {
        System.out.println("Default Method Executed");
    }

    // static method
    // we can’t override them in the implementation classes
    static boolean isNull(String sourceStr) {
        return sourceStr == null || ("".equals(sourceStr));
    }
}
