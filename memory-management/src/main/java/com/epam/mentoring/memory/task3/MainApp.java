package com.epam.mentoring.memory.task3;

import com.epam.mentoring.memory.task4.Dog;

import java.util.ArrayList;

/**
 * @author Kaikenov Adilhan
 **/
public class MainApp {

    public static void main(String[] args) throws Exception {

        // -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=C:\Temp
        final ArrayList<Dog> dogs = new ArrayList<>();

        for (int i = 0; ; i++) {
            dogs.add(new Dog());
        }
    }
}
