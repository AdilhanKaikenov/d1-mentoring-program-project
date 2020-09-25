package com.epam.mentoring.javacore.task1.util;

import com.epam.mentoring.javacore.task1.model.appliance.HomeAppliance;

@FunctionalInterface
public interface AppliancePrintFunction {

    void print(HomeAppliance appliance);

}
