package com.epam.mentoring.javacore.task1.model;

import com.epam.mentoring.javacore.task1.model.appliance.HomeAppliance;
import com.epam.mentoring.javacore.task3.annotation.ThisCodeSmells;
import com.epam.mentoring.javacore.task3.annotation.UseArrayList;
import com.epam.mentoring.javacore.task3.annotation.UseStackOnly;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Kaikenov Adilhan
 **/
@ThisCodeSmells(reviewer = "Petya")
@ThisCodeSmells(reviewer = "Adilkhan")
public class Apartment {

    @ThisCodeSmells(reviewer = "Petya")
    @UseStackOnly
    private String address;

    @ThisCodeSmells(reviewer = "Adilkhan")
    @UseStackOnly
    private double area;

    @ThisCodeSmells(reviewer = "Petya")
    @UseStackOnly
    private List<HomeAppliance> electricalAppliances = new ArrayList<>();

    public Apartment() {
    }

    @ThisCodeSmells(reviewer = "Petya")
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

    @ThisCodeSmells(reviewer = "Adilkhan")
    @UseArrayList
    public List<HomeAppliance> getElectricalAppliances() {
        return this.electricalAppliances;
    }

    @UseArrayList
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
