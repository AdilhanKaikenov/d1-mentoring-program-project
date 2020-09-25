package com.epam.mentoring.spring.intro.model;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Objects;

public class Position {

    @NotNull
    private PositionTitle title;

    @Min(value = 0, message="The value must be positive")
    private double monthlyRate;

    @Valid
    private Employee employee;

    public Position() {
    }

    public Position(PositionTitle title, double monthlyRate, Employee employee) {
        this.title = title;
        this.monthlyRate = monthlyRate;
        this.employee = employee;
    }

    public PositionTitle getTitle() {
        return this.title;
    }

    public void setTitle(PositionTitle title) {
        this.title = title;
    }

    public double getMonthlyRate() {
        return this.monthlyRate;
    }

    public void setMonthlyRate(double monthlyRate) {
        this.monthlyRate = monthlyRate;
    }

    public Employee getEmployee() {
        return this.employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public boolean isVacant() {
        return this.employee == null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return Double.compare(position.monthlyRate, this.monthlyRate) == 0 &&
                this.title == position.title &&
                Objects.equals(this.employee, position.employee);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.title, this.monthlyRate, this.employee);
    }
}
