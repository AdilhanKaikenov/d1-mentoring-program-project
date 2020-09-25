package com.epam.mentoring.javacore.task1;

import com.epam.mentoring.javacore.task1.exceptions.ApplianceException;
import com.epam.mentoring.javacore.task1.model.Apartment;
import com.epam.mentoring.javacore.task1.model.Dimension;
import com.epam.mentoring.javacore.task1.model.Product;
import com.epam.mentoring.javacore.task1.model.appliance.HomeAppliance;
import com.epam.mentoring.javacore.task1.model.appliance.impl.Fridge;
import com.epam.mentoring.javacore.task1.model.appliance.impl.Washer;
import com.epam.mentoring.javacore.task1.util.ApartmentUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * @author Kaikenov Adilhan
 **/
public class MainApp {

    private static final Logger log = LoggerFactory.getLogger(MainApp.class);

    // I want to say the compiler not to worry about the old (deprecated) methods called.
    @SuppressWarnings("deprecation")
    public static void main(String[] args) throws ApplianceException {

        Fridge fridge = new Fridge("Fridge", new Dimension(10, 10, 10), 150, 4, new ArrayList<>(), 30);
        Washer washer = new Washer("Washer", new Dimension(10, 10, 10), 450, 10);

        List<HomeAppliance> electricalAppliances = new ArrayList<>(Arrays.asList(
                fridge, washer
        ));

        final double area = 68.5;
        Apartment apartment = new Apartment("Street 38/3", area, electricalAppliances);

        // @Override example:
        // I want to indicate that the method was redefined from the parent class.
        // Compilation or interpretation error will be occurred if the method is not found in the parent class or interface.
        fridge.on();

        final int weight = 100;
        final int volume = 30;
        Product product = new Product("Meat", weight, volume);
        // @Deprecated example:
        // I want to encourage users of the API to migrate to the new API
        // Most likely, this method is planned to be removed in the future.
        fridge.putProduct(product);

        // @FunctionalInterface example:
        // An interface with exactly one non-default abstract method becomes Functional Interface.
        // See: com.epam.mentoring.javacore.task1.util.AppliancePrintFunction
        ApartmentUtil.printHomeAppliances(apartment, appliance -> log.info("{} - {} Watt", appliance.getName(), appliance.getPowerConsumption()));
        ApartmentUtil.printHomeAppliances(apartment, appliance -> log.info("Appliance Name : {}", appliance.getName()));

    }
}
