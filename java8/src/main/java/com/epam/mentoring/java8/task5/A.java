package com.epam.mentoring.java8.task5;

import java.util.Objects;

/**
 * Define a complex class A with a few fields with different types. This class will be an item in collection.
 *
 * Methods which calculates some stat over the fields of the A class.
 *
 * @author Kaikenov Adilhan
 **/
public class A {

    private static final int INFORMATIVE_STR_LENGTH = 7;

    private int number;

    private String string;

    public A() {
    }

    public A(final int number, final String string) {
        this.number = number;
        this.string = string;
    }

    public void add(int number) {
        this.number += number;
    }

    public void cut(int beginIndex) {
        this.string = this.string.substring(beginIndex);
    }

    public int getNumber() {
        return this.number;
    }

    public String getString() {
        return this.string;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        final A a = (A) o;
        return this.number == a.number &&
                Objects.equals(this.string, a.string);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.number, this.string);
    }

    @Override
    public String toString() {
        return "A{" +
                "number=" + this.number +
                ", string='" + this.string + '\'' +
                '}';
    }
}
