package com.epam.mentoring.javacore.task1.model.appliance.impl;

import com.epam.mentoring.javacore.task1.exceptions.ApplianceException;
import com.epam.mentoring.javacore.task1.exceptions.WashingException;
import com.epam.mentoring.javacore.task1.model.Dimension;
import com.epam.mentoring.javacore.task1.model.appliance.HomeAppliance;
import com.epam.mentoring.javacore.task1.model.enums.ApplianceCategory;
import com.epam.mentoring.javacore.task3.annotation.ProdCode;
import com.epam.mentoring.javacore.task3.annotation.ThisCodeSmells;
import com.epam.mentoring.javacore.task3.annotation.UseStackOnly;

/**
 * @author Kaikenov Adilhan
 **/
@ThisCodeSmells(reviewer = "Petya")
@ThisCodeSmells(reviewer = "Adilkhan")
public class Washer extends HomeAppliance {

    @UseStackOnly
    private double maxLinenWeight;

    public Washer() {
    }

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
    @ProdCode
    public void on() throws ApplianceException {
        super.on();
        this.doLaundry();
    }

    @ProdCode
    private void doLaundry() throws WashingException {
        System.out.println("Things are washing...");
    }

    @ProdCode
    @Override
    public String toString() {
        return "Washer{" +
                "maxLinenWeight=" + this.maxLinenWeight +
                super.toString() +
                '}';
    }
}
