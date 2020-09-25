package com.epam.mentoring.javacore.task1.model.appliance.impl;

import com.epam.mentoring.javacore.task1.model.Dimension;
import com.epam.mentoring.javacore.task1.model.appliance.HomeAppliance;
import com.epam.mentoring.javacore.task1.model.enums.ApplianceCategory;

/**
 * @author Kaikenov Adilhan
 **/
public class HairDryer extends HomeAppliance {

    private int speedsNumber;

    private int temperatureLevelsNumber;

    public HairDryer(String name, Dimension dimension, int powerConsumption, int speedsNumber, int temperatureLevelsNumber) {
        super(name, ApplianceCategory.INDIVIDUAL_CARE, dimension, powerConsumption);
        this.speedsNumber = speedsNumber;
        this.temperatureLevelsNumber = temperatureLevelsNumber;
    }

    public int getSpeedsNumber() {
        return this.speedsNumber;
    }

    public void setSpeedsNumber(int speedsNumber) {
        this.speedsNumber = speedsNumber;
    }

    public int getTemperatureLevelsNumber() {
        return this.temperatureLevelsNumber;
    }

    public void setTemperatureLevelsNumber(int temperatureLevelsNumber) {
        this.temperatureLevelsNumber = temperatureLevelsNumber;
    }

    @Override
    public String toString() {
        return "HairDryer{" +
                "speedsNumber=" + this.speedsNumber +
                ", temperatureLevelsNumber=" + this.temperatureLevelsNumber +
                super.toString() +
                '}';
    }
}
