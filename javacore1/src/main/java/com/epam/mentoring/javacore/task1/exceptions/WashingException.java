package com.epam.mentoring.javacore.task1.exceptions;

/**
 * @author Kaikenov Adilhan
 **/
public final class WashingException extends ApplianceException {

    public WashingException(Exception e) {
        super(e);
    }

    public WashingException(String message) {
        super(message);
    }

    public WashingException(String message, Throwable cause) {
        super(message, cause);
    }
}
