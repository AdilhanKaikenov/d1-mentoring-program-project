package com.epam.mentoring.javacore.task1.exceptions;

/**
 * @author Kaikenov Adilhan
 **/
public final class ApplianceNotAllowedTemperatureException extends ApplianceBusinessException {

    public ApplianceNotAllowedTemperatureException(Exception e) {
        super(e);
    }

    public ApplianceNotAllowedTemperatureException(String message) {
        super(message);
    }

    public ApplianceNotAllowedTemperatureException(String message, Throwable cause) {
        super(message, cause);
    }
}
