package com.epam.mentoring.javacore.task3.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;

// @ProdCode could be attached to methods only and should be accessed in Runtime
@Target({METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ProdCode {

}
