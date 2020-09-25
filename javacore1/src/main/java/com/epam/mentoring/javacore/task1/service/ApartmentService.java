package com.epam.mentoring.javacore.task1.service;

import com.epam.mentoring.javacore.task1.model.Apartment;
import com.epam.mentoring.javacore.task1.model.appliance.HomeAppliance;
import com.epam.mentoring.javacore.task1.model.enums.ApplianceCategory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Kaikenov Adilhan
 **/
public class ApartmentService {

    private static final Logger log = LoggerFactory.getLogger(ApartmentService.class);

    public List<HomeAppliance> findByParameters(Apartment apartment, ApplianceCategory category, int powerConsumption, boolean pluggedIn) {
        List<HomeAppliance> result = new ArrayList<>();

        if (apartment == null) {
            throw new IllegalArgumentException("Argument can not be null.");
        }

        if (apartment.getElectricalAppliances().isEmpty()) {
            log.debug("The apartment has no electrical appliances.");
            return result;
        }

        for (HomeAppliance appliance : apartment.getElectricalAppliances()) {
            if (appliance.getCategory().equals(category) && appliance.getPowerConsumption() == powerConsumption && appliance.isPluggedIn() == pluggedIn) {
                result.add(appliance);
            }
        }

        return result;
    }
}
