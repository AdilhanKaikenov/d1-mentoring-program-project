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

    public static void printHomeAppliances(Apartment apartment, AppliancePrintFunction printFunction) {
        if (apartment == null) {
            throw new IllegalArgumentException("Argument can not be null.");
        }

        if (apartment.getElectricalAppliances().isEmpty()) {
            log.debug("The apartment has no electrical appliances.");
            return;
        }

        for (ElectricalAppliance electricalAppliance : apartment.getElectricalAppliances()) {
            printFunction.print((HomeAppliance) electricalAppliance);
        }
    }
}
