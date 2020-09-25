package com.epam.mentoring.javacore.task3.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.CLASS;

// @UseStackOnly could be attached to fields and could not be accessed in Runtime
@Target({FIELD})
@Retention(CLASS) // RetentionPolicy.CLASS is the default behavior.
public @interface UseStackOnly {

}
