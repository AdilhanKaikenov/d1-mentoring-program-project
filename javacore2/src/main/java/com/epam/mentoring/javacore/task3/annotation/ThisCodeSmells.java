package com.epam.mentoring.javacore.task3.annotation;

import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

// @ThisCodeSmells(reviewer=”ReviewerName”) could be attached everywhere and could be repeatable
@Target({TYPE, FIELD, METHOD, PARAMETER, CONSTRUCTOR, LOCAL_VARIABLE})
@Retention(RUNTIME)
@Repeatable(value = ThisCodeSmells.ReviewersContainer.class)
public @interface ThisCodeSmells {

    String reviewer() default "";

    @Retention(RUNTIME)
    @Target({TYPE, FIELD, METHOD, PARAMETER, CONSTRUCTOR, LOCAL_VARIABLE})
    @interface ReviewersContainer {

        ThisCodeSmells[] value();

    }
}
