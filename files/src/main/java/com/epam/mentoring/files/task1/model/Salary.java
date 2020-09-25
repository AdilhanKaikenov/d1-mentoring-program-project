package com.epam.mentoring.files.task1.model;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author Kaikenov Adilhan
 **/
public class Salary implements Serializable {

    private transient double balance;

    public Salary() {
    }

    public Salary(double balance) {
        this.balance = balance;
    }

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

    @Override
    public String toString() {
        return "Salary{" +
                "balance=" + this.balance +
                '}';
    }
}
