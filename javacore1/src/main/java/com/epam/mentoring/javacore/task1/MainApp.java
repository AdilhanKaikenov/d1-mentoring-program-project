package com.epam.mentoring.javacore.task1;

import com.epam.mentoring.javacore.task1.exceptions.*;
import com.epam.mentoring.javacore.task1.model.*;
import com.epam.mentoring.javacore.task1.model.Dimension;
import com.epam.mentoring.javacore.task1.model.appliance.*;
import com.epam.mentoring.javacore.task1.model.appliance.impl.*;
import com.epam.mentoring.javacore.task1.model.enums.ApplianceCategory;
import com.epam.mentoring.javacore.task1.service.ApartmentService;
import com.epam.mentoring.javacore.task1.util.ApartmentUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.epam.mentoring.javacore.task1.model.Apartment.SORTER_BY_POWER_CONSUMPTION;

/**
 * @author Kaikenov Adilhan
 **/
public class MainApp {

    private static final Logger log = LoggerFactory.getLogger(MainApp.class);

    public static void main(String[] args) throws ApplianceException {

        // Hierarchy of HomeAppliance.
        HomeAppliance washer = new Washer("Washer", new Dimension(10, 10, 10), 450, 10);
        HomeAppliance stove = new ElectricStove("Stove", new Dimension(10, 10, 10), 350, 4);
        HomeAppliance fridge = new Fridge("Fridge", new Dimension(10, 10, 10), 150, 4, Collections.emptyList(), 30);
        HomeAppliance hairDryer = new HairDryer("Hair Dryer", new Dimension(10, 10, 10), 150, 3, 3);
        HomeAppliance vacuumCleaner = new VacuumCleaner("Vacuum Cleaner", new Dimension(10, 10, 10), 350, 3, 350, 3);
        HomeAppliance kettle = new ElectricKettle("Kettle", new Dimension(10, 10, 10), 150, 3);

        // Plug some into the outlet.
        kettle.plugIn();
        fridge.plugIn();
        washer.plugIn();
        hairDryer.plugIn();

        List<HomeAppliance> electricalAppliances = new ArrayList<>(Arrays.asList(
                kettle, stove, fridge, hairDryer, vacuumCleaner, washer
        ));

        ApartmentService service = new ApartmentService();
        final double area = 68.5;
        Apartment apartment = new Apartment("Street 38/3", area, electricalAppliances);

        // Calculate total power consumption.
        int totalPowerConsumption = ApartmentUtil.calculateTotalPowerConsumption(apartment);
        log.info("Total Power Consumption = {} Watt", totalPowerConsumption);

        // Sort by one of the parameters.
        log.info("Sorting...");
        Collections.sort(apartment.getElectricalAppliances(), SORTER_BY_POWER_CONSUMPTION);
        ApartmentUtil.printHomeAppliancesPowerConsumption(apartment);

        // Find a kitchen appliance in the apartment that matches the specified range of parameters.
        final int powerConsumption = 150;
        List<HomeAppliance> kitchenAppliance = service.findByParameters(apartment, ApplianceCategory.KITCHEN_APPLIANCE, powerConsumption, true);
        for (HomeAppliance appliance : kitchenAppliance) {
            log.info("{} - Power Consumption: {}; PluggedIn: {} ", appliance.getName(), appliance.getPowerConsumption(), appliance.isPluggedIn());
        }

        // Exception hierarchy for the designed object model.
        try {
            vacuumCleaner.on();
        } catch (ApplianceStateException e) {
            vacuumCleaner.plugIn();
        } catch (ApplianceException e) {
            throw new RuntimeException("Failed to invoke on() method.", e);
        } finally {
            vacuumCleaner.on();
        }

        final int weight = 100;
        final int volume = 30;
        Product product = new Product("Meat", weight, volume);
        try {
            ((Fridge) fridge).put(product);
        } catch (ApplianceVolumeException e) {
            log.error("It failed to put the product in the Fridge:", e);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("You passed an illegal argument. Please initialize the product correctly.", e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        try {
            final int temperatureC = 40;
            ((Fridge) fridge).setTemperature(temperatureC);
        } catch (ApplianceBusinessException e) {
            throw new RuntimeException("The temperature should be within the specified limits.", e);
        }

        try {
            washer.on();
        } catch (ApplianceStateException e) {
            washer.plugIn();
        } catch (WashingException e) {
            log.error("Error occurred during washing. Perhaps turned off the water in the apartment. Try later.", e);
            washer.off();
        } catch (ApplianceException e) {
            throw new RuntimeException("Error occurred during washing.", e);
        }
    }
}
