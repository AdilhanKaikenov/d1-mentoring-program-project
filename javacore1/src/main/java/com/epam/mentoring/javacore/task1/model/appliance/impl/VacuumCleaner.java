package com.epam.mentoring.javacore.task1.model.appliance.impl;

import com.epam.mentoring.javacore.task1.model.Dimension;
import com.epam.mentoring.javacore.task1.model.appliance.HomeAppliance;
import com.epam.mentoring.javacore.task1.model.enums.ApplianceCategory;

/**
 * @author Kaikenov Adilhan
 **/
public class VacuumCleaner extends HomeAppliance {

    private double tankVolume;

    private int suctionPower;

    private int noiseLevel;

    public VacuumCleaner(String name, Dimension dimension, int powerConsumption, double tankVolume, int suctionPower, int noiseLevel) {
        super(name, ApplianceCategory.HOME_APPLIANCE, dimension, powerConsumption);
        this.tankVolume = tankVolume;
        this.suctionPower = suctionPower;
        this.noiseLevel = noiseLevel;
    }

    public double getTankVolume() {
        return this.tankVolume;
    }

    public void setTankVolume(double tankVolume) {
        this.tankVolume = tankVolume;
    }

    public int getSuctionPower() {
        return this.suctionPower;
    }

    public void setSuctionPower(int suctionPower) {
        this.suctionPower = suctionPower;
    }

    public int getNoiseLevel() {
        return this.noiseLevel;
    }

    public void setNoiseLevel(int noiseLevel) {
        this.noiseLevel = noiseLevel;
    }

    @Override
    public String toString() {
        return "VacuumCleaner{" +
                "tankVolume=" + this.tankVolume +
                ", suctionPower=" + this.suctionPower +
                ", noiseLevel=" + this.noiseLevel +
                super.toString() +
                '}';
    }
}
