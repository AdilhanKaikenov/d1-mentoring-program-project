package com.epam.mentoring.javacore.task1.exceptions;

/**
 * @author Kaikenov Adilhan
 **/
public class ApplianceBusinessException extends RuntimeException {

    public ApplianceBusinessException(Exception e) {
        super(e);
    }

    public ApplianceBusinessException(String message) {
        super(message);
    }

    public ApplianceBusinessException(String message, Throwable cause) {
        super(message, cause);
    }
}
