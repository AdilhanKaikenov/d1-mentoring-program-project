package com.epam.mentoring.javacore.task4;

import com.epam.mentoring.javacore.task1.model.Apartment;
import com.epam.mentoring.javacore.task4.exceptions.ThisCodeSmellsHandlerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Kaikenov Adilhan
 **/
public class MainApp {

    private static final Logger log = LoggerFactory.getLogger(MainApp.class);

    public static void main(String[] args) {

        // for LOCAL_VARIABLE and PARAMETER, there is no such possibility at runtime yet

        ThisCodeSmellsAnnotationHandler handler = new ThisCodeSmellsAnnotationHandler();

        try {
            handler.printSmeltCodeInfo(Apartment.class);
        } catch (ThisCodeSmellsHandlerException e) {
            throw new RuntimeException("Failed during printSmeltCodeInfo() method call.", e);
        }

    }
}
