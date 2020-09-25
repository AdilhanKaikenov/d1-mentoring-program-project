package com.epam.mentoring.spring.intro.model;

import javax.validation.constraints.Min;
import java.util.Objects;

public class Salary {

    @Min(value = 0, message = "The value must be positive")
    private double balance;

    public double getBalance() {
        return this.balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        Salary salary = (Salary) o;
        return Double.compare(salary.balance, this.balance) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.balance);
    }
}
