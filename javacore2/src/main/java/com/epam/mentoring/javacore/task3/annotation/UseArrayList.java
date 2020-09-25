package com.epam.mentoring.javacore.task3.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.SOURCE;

// @UseArrayList could be attached to methods and could not be found in the bytecode
@Target({METHOD})
@Retention(SOURCE)
public @interface UseArrayList {

}
