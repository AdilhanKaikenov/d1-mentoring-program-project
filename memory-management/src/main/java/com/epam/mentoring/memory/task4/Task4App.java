package com.epam.mentoring.memory.task4;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Kaikenov Adilhan
 **/
public class Task4App {

    public static void main(String[] args) throws Exception {

        final List<Animal> animals = new ArrayList<>();

        MyClassLoader myClassLoader = new MyClassLoader();
        Class<?> catClass = myClassLoader.findClass("com.epam.mentoring.memory.task4.Cat");
        Class<?> dogClass = myClassLoader.findClass("com.epam.mentoring.memory.task4.Dog");
        Object cat = catClass.newInstance();
        Object dog = dogClass.newInstance();

        animals.add((Animal) cat);
        animals.add((Animal) dog);

        for (final Animal animal : animals) {
            animal.play();
            animal.voice();
        }

    }
}
