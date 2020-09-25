package com.epam.mentoring.jdbc.intro.task2.exceptions;

/**
 * @author Kaikenov Adilhan
 */
public class DaoException extends Exception {

    public DaoException(String message) {
        super(message);
    }

    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }
}
