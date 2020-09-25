package com.epam.mentoring.javacore.task1.exceptions;

/**
 * @author Kaikenov Adilhan
 **/
public final class ApplianceStateException extends ApplianceException {

    public ApplianceStateException(Exception e) {
        super(e);
    }

    public ApplianceStateException(String message) {
        super(message);
    }

    public ApplianceStateException(String message, Throwable cause) {
        super(message, cause);
    }
}
