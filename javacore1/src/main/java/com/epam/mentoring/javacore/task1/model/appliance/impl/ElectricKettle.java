package com.epam.mentoring.javacore.task1.model.appliance.impl;

import com.epam.mentoring.javacore.task1.model.Dimension;
import com.epam.mentoring.javacore.task1.model.appliance.HomeAppliance;
import com.epam.mentoring.javacore.task1.model.enums.ApplianceCategory;

/**
 * @author Kaikenov Adilhan
 **/
public class ElectricKettle extends HomeAppliance {

    private int capacity;

    public ElectricKettle(String name, Dimension dimension, int powerConsumption, int capacity) {
        super(name, ApplianceCategory.KITCHEN_APPLIANCE, dimension, powerConsumption);
        this.capacity = capacity;
    }

    public int getCapacity() {
        return this.capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return "ElectricKettle{" +
                "capacity=" + this.capacity +
                super.toString() +
                '}';
    }
}
