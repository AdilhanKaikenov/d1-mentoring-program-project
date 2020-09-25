package com.epam.mentoring.javacore.task1.exceptions;

/**
 * @author Kaikenov Adilhan
 **/
public class ApplianceException extends Exception {

    public ApplianceException(Exception e) {
    }

    public ApplianceException(String message) {
        super(message);
    }

    public ApplianceException(String message, Throwable cause) {
        super(message, cause);
    }
}
