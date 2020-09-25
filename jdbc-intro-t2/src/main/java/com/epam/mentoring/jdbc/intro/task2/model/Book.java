package com.epam.mentoring.jdbc.intro.task2.model;

import java.time.Year;

/**
 * @author Kaikenov Adilhan
 */
public class Book extends BaseEntity {

    private String title;
    private Author author;
    private Year publishYear;
    private Genre genre;
    private String description;
    private int totalAmount;

    public Book() {
        this.author = new Author();
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return this.author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Year getPublishYear() {
        return this.publishYear;
    }

    public void setPublishYear(Year publishYear) {
        this.publishYear = publishYear;
    }

    public Genre getGenre() {
        return this.genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTotalAmount() {
        return this.totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

}
