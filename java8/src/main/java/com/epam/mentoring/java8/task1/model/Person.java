package com.epam.mentoring.java8.task1.model;

import java.util.Objects;

/**
 * Person class with name and age fields
 *
 * @author Kaikenov Adilhan
 **/
public class Person {

    private String name;

    private int age;

    public Person() {
    }

    public Person(final String name, final int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(final int age) {
        this.age = age;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Person person = (Person) o;
        return this.age == person.age &&
                Objects.equals(this.name, person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.name, this.age);
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + this.name + '\'' +
                ", age=" + this.age +
                '}';
    }
}
