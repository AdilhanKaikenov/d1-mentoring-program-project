package com.epam.mentoring.jdbc.intro.task2.model;

/**
 * @author Kaikenov Adilhan
 */
public class Author extends BaseEntity {

    private String firstname;

    private String lastname;

    public String getFirstname() {
        return this.firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return this.lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
}
