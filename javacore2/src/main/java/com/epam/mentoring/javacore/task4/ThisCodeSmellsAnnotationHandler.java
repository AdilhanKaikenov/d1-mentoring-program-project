package com.epam.mentoring.javacore.task4;

import com.epam.mentoring.javacore.task3.annotation.ThisCodeSmells;
import com.epam.mentoring.javacore.task4.exceptions.ThisCodeSmellsHandlerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Kaikenov Adilhan
 **/
public final class ThisCodeSmellsAnnotationHandler {

    private static final Logger log = LoggerFactory.getLogger(ThisCodeSmellsAnnotationHandler.class);

    private static final Class<ThisCodeSmells> THIS_CODE_SMELLS_CLASS = ThisCodeSmells.class;

    public void printSmeltCodeInfo(final Class<?> objectClass) throws ThisCodeSmellsHandlerException {
        try {
            log.info("Processing '{}' class...", objectClass.getSimpleName());
            int annotationCount = 0;

            annotationCount = proceed(annotationCount, objectClass.getAnnotationsByType(THIS_CODE_SMELLS_CLASS),
                    String.format("%s class", objectClass.getSimpleName()));

            for (final Field field : objectClass.getDeclaredFields()) {
                annotationCount = proceed(annotationCount, field.getAnnotationsByType(THIS_CODE_SMELLS_CLASS),
                        String.format("%s field", field.getName()));
            }

            Set<Method> methods = new HashSet<>(Arrays.asList(objectClass.getMethods()));
            methods.addAll(Arrays.asList(objectClass.getDeclaredMethods()));
            for (final Method method : methods) {
                annotationCount = proceed(annotationCount, method.getAnnotationsByType(THIS_CODE_SMELLS_CLASS),
                        String.format("%s method", method.toString()));
            }

            log.info("Amount of @ThisCodeSmells annotations = {}", annotationCount);

        } catch (final Exception e) {
            throw new ThisCodeSmellsHandlerException(e);
        }
    }

    private int proceed(int annotationCount, final ThisCodeSmells[] annotations, final String name) {
        for (final ThisCodeSmells annotation : annotations) {
            annotationCount++;
            log.info("{} count '{}' code smelt and vote for it.", annotation.reviewer(), name);
        }
        return annotationCount;
    }
}
