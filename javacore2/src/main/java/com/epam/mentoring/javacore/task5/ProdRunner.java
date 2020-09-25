package com.epam.mentoring.javacore.task5;

import com.epam.mentoring.javacore.task3.annotation.ProdCode;

import java.lang.reflect.Method;
import java.math.BigDecimal;

/**
 * @author Kaikenov Adilhan
 **/
public final class ProdRunner {

    private static final int ZERO = 0;

    private static final Class<ProdCode> PROD_CODE_CLASS = ProdCode.class;

    public static void run(final Object object) {
        if (object == null) {
            throw new IllegalArgumentException("Argument can not be null.");
        }

        final Class<?> objectClass = object.getClass();

        for (final Method method : objectClass.getMethods()) {
            if (method.isAnnotationPresent(PROD_CODE_CLASS)) {
                try {
                    if (method.getParameterCount() == ZERO) {
                        method.invoke(object); // invoking methods without parameters
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
