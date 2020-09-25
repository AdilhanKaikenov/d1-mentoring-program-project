package com.epam.mentoring.javacore.task1.util;

import com.epam.mentoring.javacore.task1.model.Apartment;
import com.epam.mentoring.javacore.task1.model.appliance.ElectricalAppliance;
import com.epam.mentoring.javacore.task1.model.appliance.HomeAppliance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Kaikenov Adilhan
 **/
public final class ApartmentUtil {

    private static final Logger log = LoggerFactory.getLogger(ApartmentUtil.class);

    public static int calculateTotalPowerConsumption(Apartment apartment) {
        int totalPowerConsumption = 0;

        if (apartment == null) {
            throw new IllegalArgumentException("Argument can not be null.");
        }

        for (ElectricalAppliance electricalAppliance : apartment.getElectricalAppliances()) {
            totalPowerConsumption += ((HomeAppliance) electricalAppliance).getPowerConsumption();
        }
        return totalPowerConsumption;
    }

    public static void printHomeAppliancesPowerConsumption(Apartment apartment) {
        if (apartment == null) {
            throw new IllegalArgumentException("Argument can not be null.");
        }

        if (apartment.getElectricalAppliances().isEmpty()) {
            log.debug("The apartment has no electrical appliances.");
            return;
        }

        for (ElectricalAppliance electricalAppliance : apartment.getElectricalAppliances()) {
            HomeAppliance appliance = (HomeAppliance) electricalAppliance;
            log.info("{} - {} Watt", appliance.getName(), appliance.getPowerConsumption());
        }
    }
}
