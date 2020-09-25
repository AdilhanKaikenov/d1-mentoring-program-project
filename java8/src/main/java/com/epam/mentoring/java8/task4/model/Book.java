package com.epam.mentoring.java8.task4.model;

import java.util.List;
import java.util.Objects;

/**
 * @author Kaikenov Adilhan
 **/
public class Book {

    private String title;

    private List<Author> authors;

    private int numberOfPages;

    public Book() {
    }

    public Book(final String title, final List<Author> authors, final int numberOfPages) {
        this.title = title;
        this.authors = authors;
        this.numberOfPages = numberOfPages;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public List<Author> getAuthors() {
        return this.authors;
    }

    public void setAuthors(final List<Author> authors) {
        this.authors = authors;
    }

    public int getNumberOfPages() {
        return this.numberOfPages;
    }

    public void setNumberOfPages(final int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Book book = (Book) o;
        return this.numberOfPages == book.numberOfPages &&
                Objects.equals(this.title, book.title) &&
                Objects.equals(this.authors, book.authors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.title, this.authors, this.numberOfPages);
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + this.title + '\'' +
                ", authors=" + this.authors +
                ", numberOfPages=" + this.numberOfPages +
                '}';
    }
}
