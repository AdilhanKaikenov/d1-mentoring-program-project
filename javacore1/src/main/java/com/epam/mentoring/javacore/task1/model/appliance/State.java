package com.epam.mentoring.javacore.task1.model.appliance;

import com.epam.mentoring.javacore.task1.exceptions.ApplianceStateException;

/**
 * @author Kaikenov Adilhan
 */
public interface State {

    void on() throws Exception;

    void off();

}
