package com.epam.mentoring.javacore.task1.exceptions;

/**
 * @author Kaikenov Adilhan
 **/
public final class ApplianceVolumeException extends ApplianceBusinessException {

    public ApplianceVolumeException(Exception e) {
        super(e);
    }

    public ApplianceVolumeException(String message) {
        super(message);
    }

    public ApplianceVolumeException(String message, Throwable cause) {
        super(message, cause);
    }
}
