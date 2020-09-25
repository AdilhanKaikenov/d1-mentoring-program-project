package com.epam.mentoring.javacore.task1.model;

import com.epam.mentoring.javacore.task1.comparators.SorterByPowerConsumption;
import com.epam.mentoring.javacore.task1.model.appliance.HomeAppliance;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author Kaikenov Adilhan
 **/
public class Apartment {

    public static final Comparator<HomeAppliance> SORTER_BY_POWER_CONSUMPTION = new SorterByPowerConsumption();

    private String address;

    private double area;

    private List<HomeAppliance> electricalAppliances = new ArrayList<>();

    public Apartment() {
    }

    public Apartment(String address, double area, List<HomeAppliance> electricalAppliances) {
        this.address = address;
        this.area = area;
        this.electricalAppliances = electricalAppliances;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getArea() {
        return this.area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public List<HomeAppliance> getElectricalAppliances() {
        return this.electricalAppliances;
    }

    public void setElectricalAppliances(List<HomeAppliance> electricalAppliances) {
        this.electricalAppliances = electricalAppliances;
    }

    @Override
    public String toString() {
        return "Apartment{" +
                "address='" + this.address + '\'' +
                ", area=" + this.area +
                ", electricalAppliances=" + this.electricalAppliances +
                '}';
    }
}
