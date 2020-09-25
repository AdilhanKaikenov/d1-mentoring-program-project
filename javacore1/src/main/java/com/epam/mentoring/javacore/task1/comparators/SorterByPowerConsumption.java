package com.epam.mentoring.javacore.task1.comparators;

import com.epam.mentoring.javacore.task1.model.appliance.HomeAppliance;

import java.util.Comparator;

/**
 * @author Kaikenov Adilhan
 **/
public final class SorterByPowerConsumption implements Comparator<HomeAppliance> {

    @Override
    public int compare(HomeAppliance homeAppliance1, HomeAppliance homeAppliance2) {

        Integer powerConsumption1 = homeAppliance1.getPowerConsumption();
        Integer powerConsumption2 = homeAppliance2.getPowerConsumption();

        return powerConsumption1.compareTo(powerConsumption2);
    }
}
