package com.epam.mentoring.java8.task4.model;

import java.util.List;
import java.util.Objects;

/**
 * @author Kaikenov Adilhan
 **/
public class Author {

    private String name;

    private int age;

    private List<Book> books;

    public Author() {
    }

    public Author(final String name, final int age, final List<Book> books) {
        this.name = name;
        this.age = age;
        this.books = books;
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

    public List<Book> getBooks() {
        return this.books;
    }

    public void setBooks(final List<Book> books) {
        this.books = books;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Author author = (Author) o;
        return this.age == author.age &&
                Objects.equals(this.name, author.name) &&
                Objects.equals(this.books, author.books);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.name, this.age, this.books);
    }

    @Override
    public String toString() {
        return "Author{" +
                "name='" + this.name + '\'' +
                ", age=" + this.age +
                ", books=" + this.books +
                '}';
    }
}
