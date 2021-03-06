package com.epam.mentoring.jdbc.intro.task2.model;

/**
 * @author Kaikenov Adilhan
 */
public enum Genre {

    POETRY("Poetry"),
    DOCUMENTAL_LITERATURE("Documental literature"),
    DETECTIVE_AND_THRILLERS("Detectives and thrillers"),
    COMPUTERS_AND_INTERNET("Computers and Internet"),
    SCIENCE_AND_EDUCATION("Science and education");

    private String value;

    Genre(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public static Genre getFromValue(String value) {
        for (Genre genre : Genre.values()) {
            if (genre.getValue().equalsIgnoreCase(value)) {
                return genre;
            }
        }
        throw new IllegalArgumentException("Error: Genre enum class, getFromValue() method: illegal argument.");
    }
}
