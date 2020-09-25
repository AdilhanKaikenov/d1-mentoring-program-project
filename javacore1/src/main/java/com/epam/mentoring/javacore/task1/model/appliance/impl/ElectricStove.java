package com.epam.mentoring.javacore.task1.model.appliance.impl;

import com.epam.mentoring.javacore.task1.model.Dimension;
import com.epam.mentoring.javacore.task1.model.appliance.HomeAppliance;
import com.epam.mentoring.javacore.task1.model.enums.ApplianceCategory;

/**
 * @author Kaikenov Adilhan
 **/
public class ElectricStove extends HomeAppliance {

    private int numberOfTiles;

    public ElectricStove(String name, Dimension dimension, int powerConsumption, int numberOfTiles) {
        super(name, ApplianceCategory.KITCHEN_APPLIANCE, dimension, powerConsumption);
        this.numberOfTiles = numberOfTiles;
    }

    public int getNumberOfTiles() {
        return this.numberOfTiles;
    }

    public void setNumberOfTiles(int numberOfTiles) {
        this.numberOfTiles = numberOfTiles;
    }

    @Override
    public String toString() {
        return "ElectricStove{" +
                "numberOfTiles=" + this.numberOfTiles +
                super.toString() +
                '}';
    }
}
