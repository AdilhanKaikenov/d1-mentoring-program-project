package com.epam.mentoring.javacore.task1.model.appliance.impl;

import com.epam.mentoring.javacore.task1.exceptions.ApplianceNotAllowedTemperatureException;
import com.epam.mentoring.javacore.task1.exceptions.ApplianceVolumeException;
import com.epam.mentoring.javacore.task1.model.Dimension;
import com.epam.mentoring.javacore.task1.model.Product;
import com.epam.mentoring.javacore.task1.model.appliance.HomeAppliance;
import com.epam.mentoring.javacore.task1.model.enums.ApplianceCategory;
import com.epam.mentoring.javacore.task3.annotation.ProdCode;
import com.epam.mentoring.javacore.task3.annotation.ThisCodeSmells;
import com.epam.mentoring.javacore.task3.annotation.UseArrayList;
import com.epam.mentoring.javacore.task3.annotation.UseStackOnly;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.MessageFormat;
import java.util.List;

/**
 * @author Kaikenov Adilhan
 **/
@ThisCodeSmells(reviewer = "Petya")
@ThisCodeSmells(reviewer = "Adilkhan")
public class Fridge extends HomeAppliance {

    private static final Logger log = LoggerFactory.getLogger(Fridge.class);

    private static final double ROOM_TEMPERATURE_C = 25;
    private static final double MINIMUM_TEMPERATURE_C = -20;

    @UseStackOnly
    private int temperature;

    @UseStackOnly
    private List<Product> products;

    @UseStackOnly
    private double volume;

    public Fridge(String name, Dimension dimension, int powerConsumption, int temperature, List<Product> products, double volume) {
        super(name, ApplianceCategory.KITCHEN_APPLIANCE, dimension, powerConsumption);
        this.temperature = temperature;
        this.products = products;
        this.volume = volume;
    }

    public int getTemperature() {
        return this.temperature;
    }

    @ProdCode
    public void setTemperature(int temperature) throws ApplianceNotAllowedTemperatureException {
        if (temperature > ROOM_TEMPERATURE_C || temperature < MINIMUM_TEMPERATURE_C) {
            throw new ApplianceNotAllowedTemperatureException(MessageFormat.format("The temperature should be in the range from {0} to {1}", ROOM_TEMPERATURE_C, MINIMUM_TEMPERATURE_C));
        }

        this.temperature = temperature;
        log.info("Temperature is set : {} degrees Celsius", this.temperature);
    }

    @UseArrayList
    public List<Product> getProducts() {
        return this.products;
    }

    @ProdCode
    public void put(Product product) throws ApplianceVolumeException {
        double productsVolume = 0.0D;

        if (product == null) {
            throw new IllegalArgumentException("Argument can not be null.");
        }

        if (!this.products.isEmpty()) {
            for (Product p : this.products) {
                productsVolume += p.getVolume();
            }
        }

        productsVolume += product.getVolume();
        if (this.volume < productsVolume) {
            throw new ApplianceVolumeException("Not enough space in the fridge.");
        }

        this.getProducts().add(product);
        log.info("Product was added : {}", product.getName());
    }

    /**
     * Deprecated.
     * Don't use this.
     *
     * Puts the product to the fridge.
     *
     * Use Fridge.put() instead.
     *
     * @param product
     */
    @Deprecated
    public void putProduct(Product product) {
        this.getProducts().add(product);
        log.info("Product was added : {}", product.getName());
    }

    @Override
    public String toString() {
        return "Fridge{" +
                "temperature=" + this.temperature +
                ", products=" + this.products +
                ", volume=" + this.volume +
                super.toString() +
                '}';
    }
}
