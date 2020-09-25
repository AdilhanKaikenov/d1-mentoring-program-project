package com.epam.mentoring.springmvc.listener;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 *  Example ApplicationListener of the ApplicationEvent
 *
 *  When a bean implements the ApplicationListener interface, every time an event is raised,
 * the bean gets about this information.
 *
 * @author Kaikenov Adilhan
**/
@Component
public class MyExampleEventListener implements ApplicationListener<ApplicationEvent> {

    /*
    Event handling in Spring is SINGLE-THREADED, which means that if the event is published, all processes will be blocked
    until all recipients receive the message. In other words, you need to be extremely careful when you plan to use
    event handling in your application.
     */
    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        // Event handling code
    }
}
