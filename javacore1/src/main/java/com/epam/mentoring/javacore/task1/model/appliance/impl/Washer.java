package com.epam.mentoring.javacore.task1.model.appliance.impl;

import com.epam.mentoring.javacore.task1.exceptions.ApplianceException;
import com.epam.mentoring.javacore.task1.exceptions.WashingException;
import com.epam.mentoring.javacore.task1.model.Dimension;
import com.epam.mentoring.javacore.task1.model.appliance.HomeAppliance;
import com.epam.mentoring.javacore.task1.model.enums.ApplianceCategory;

/**
 * @author Kaikenov Adilhan
 **/
public class Washer extends HomeAppliance {

    private double maxLinenWeight;

    public Washer(String name, Dimension dimension, int powerConsumption, double maxLinenWeight) {
        super(name, ApplianceCategory.HOME_APPLIANCE, dimension, powerConsumption);
        this.maxLinenWeight = maxLinenWeight;
    }

    public double getMaxLinenWeight() {
        return this.maxLinenWeight;
    }

    public void setMaxLinenWeight(double maxLinenWeight) {
        this.maxLinenWeight = maxLinenWeight;
    }

    @Override
    public void on() throws ApplianceException {
        super.on();
        this.doLaundry();
    }

    private void doLaundry() throws WashingException {
        System.out.println("Things are washing...");
    }

    @Override
    public String toString() {
        return "Washer{" +
                "maxLinenWeight=" + this.maxLinenWeight +
                super.toString() +
                '}';
    }
}
