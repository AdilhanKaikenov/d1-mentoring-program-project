package com.epam.mentoring.javacore.task1.model.appliance;

import com.epam.mentoring.javacore.task1.exceptions.ApplianceException;
import com.epam.mentoring.javacore.task1.exceptions.ApplianceStateException;
import com.epam.mentoring.javacore.task1.model.Dimension;
import com.epam.mentoring.javacore.task1.model.enums.ApplianceCategory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Kaikenov Adilhan
 **/
public abstract class HomeAppliance implements ElectricalAppliance {

    private static final Logger log = LoggerFactory.getLogger(HomeAppliance.class);

    private String name;

    private ApplianceCategory category;

    private Dimension dimension;

    private int powerConsumption;

    private boolean pluggedIn;

    private boolean state;

    public HomeAppliance(String name, ApplianceCategory category, Dimension dimension, int powerConsumption) {
        this.name = name;
        this.category = category;
        this.dimension = dimension;
        this.powerConsumption = powerConsumption;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ApplianceCategory getCategory() {
        return this.category;
    }

    public void setCategory(ApplianceCategory category) {
        this.category = category;
    }

    public Dimension getDimension() {
        return this.dimension;
    }

    public void setDimension(Dimension dimension) {
        this.dimension = dimension;
    }

    public int getPowerConsumption() {
        return this.powerConsumption;
    }

    public void setPowerConsumption(int powerConsumption) {
        this.powerConsumption = powerConsumption;
    }

    public boolean isPluggedIn() {
        return this.pluggedIn;
    }

    public boolean isState() {
        return this.state;
    }

    @Override
    public void plugIn() {
        this.pluggedIn = true;
        log.info("{} is connected", this.name);
    }

    @Override
    public void unplug() {
        this.pluggedIn = false;
        this.state = false;
        log.info("{} disconnected", this.name);
    }

    @Override
    public void on() throws ApplianceException {
        this.state = true;
        log.info("{} is on", this.name);
    }

    @Override
    public void off() {
        this.state = false;
        log.info("{} is off", this.name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HomeAppliance that = (HomeAppliance) o;

        if (this.powerConsumption != that.powerConsumption) return false;
        if (this.name != null ? !this.name.equals(that.name) : that.name != null) return false;
        if (this.category != that.category) return false;
        return this.dimension != null ? this.dimension.equals(that.dimension) : that.dimension == null;
    }

    @Override
    public int hashCode() {
        int result = this.name != null ? this.name.hashCode() : 0;
        result = 31 * result + (this.category != null ? this.category.hashCode() : 0);
        result = 31 * result + (this.dimension != null ? this.dimension.hashCode() : 0);
        result = 31 * result + this.powerConsumption;
        return result;
    }

    @Override
    public String toString() {
        return " HomeAppliance{" +
                "name='" + this.name + '\'' +
                ", category=" + this.category +
                ", dimension=" + this.dimension +
                ", powerConsumption=" + this.powerConsumption +
                ", pluggedIn=" + this.pluggedIn +
                ", state=" + this.state +
                '}';
    }
}
