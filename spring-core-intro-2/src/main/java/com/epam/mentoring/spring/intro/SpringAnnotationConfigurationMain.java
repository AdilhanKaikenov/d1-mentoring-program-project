package com.epam.mentoring.spring.intro;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringAnnotationConfigurationMain {

    public static void main(String[] args) {

//      Java based spring project only need to create a java class which annotated with
//      @org.springframework.context.annotation.Configuration as bean configuration manager.

//      Java based spring need to use org.springframework.context.annotation.AnnotationConfigApplicationContext to initialize
//      the bean context from a java bean configuration class file.
        AnnotationConfigApplicationContext ac =
                new AnnotationConfigApplicationContext("com.epam.mentoring.spring.intro");
//         ...
        ac.close();

    }
}
