package com.epam.mentoring.javacore.task4.exceptions;

/**
 * @author Kaikenov Adilhan
 **/
public class ThisCodeSmellsHandlerException extends Exception {

    public ThisCodeSmellsHandlerException() {
    }

    public ThisCodeSmellsHandlerException(final String message) {
        super(message);
    }

    public ThisCodeSmellsHandlerException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public ThisCodeSmellsHandlerException(final Throwable cause) {
        super(cause);
    }
}
