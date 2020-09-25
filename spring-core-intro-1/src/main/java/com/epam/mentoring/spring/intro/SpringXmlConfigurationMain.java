package com.epam.mentoring.spring.intro;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringXmlConfigurationMain {

    public static void main(String[] args) {

//      Xml based spring project need to create a xml file to define spring managed beans and relationship.

//      Xml based spring need to use org.springframework.context.support.ClassPathXmlApplicationContext
//      to initialize the bean context from xml file.
        ClassPathXmlApplicationContext  ac =
                new ClassPathXmlApplicationContext("**/ApplicationContext.xml");
//         ...
        ac.close();

    }
}
