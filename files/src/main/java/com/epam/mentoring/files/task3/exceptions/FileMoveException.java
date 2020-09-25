package com.epam.mentoring.files.task3.exceptions;

/**
 * @author Kaikenov Adilhan
 **/
public class FileMoveException extends RuntimeException {

    public FileMoveException() {
    }

    public FileMoveException(String message) {
        super(message);
    }

    public FileMoveException(String message, Throwable cause) {
        super(message, cause);
    }
}
