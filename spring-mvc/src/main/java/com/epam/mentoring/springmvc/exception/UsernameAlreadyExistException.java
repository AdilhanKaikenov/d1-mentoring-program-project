package com.epam.mentoring.springmvc.exception;

/**
 * @author Kaikenov Adilhan
**/
public class UsernameAlreadyExistException extends Exception {

    public UsernameAlreadyExistException() {
    }

    public UsernameAlreadyExistException(final String message) {
        super(message);
    }

    public UsernameAlreadyExistException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
