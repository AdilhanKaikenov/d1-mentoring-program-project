package com.epam.mentoring.spring.intro.model;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

public class Employee {

    @NotNull
    @Size(min = 1, max = 30, message = "Size must be between 1 and 30")
    private String firstname;

    private String lastname;

    @Min(value = 18, message = "Must be greater than or equal to 18")
    @Max(value = 63, message = "Must be less than or equal to 63")
    private int age;

    @Min(value = 0, message = "The value must be positive")
    private int workExperience;

    @Valid
    private Salary salary;

    public String getFirstname() {
        return this.firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return this.lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getWorkExperience() {
        return this.workExperience;
    }

    public void setWorkExperience(int workExperience) {
        this.workExperience = workExperience;
    }

    public void setSalary(Salary salary) {
        this.salary = salary;
    }

    public void deposit(double money) {
        this.salary.setBalance(this.salary.getBalance() + money);
    }

    public double withdraw(double money) {
        if (money > this.salary.getBalance()) {
            throw new RuntimeException("Insufficient balance");
        }

        this.salary.setBalance(this.salary.getBalance() - money);
        return money;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return this.age == employee.age &&
                this.workExperience == employee.workExperience &&
                Objects.equals(this.firstname, employee.firstname) &&
                Objects.equals(this.lastname, employee.lastname) &&
                Objects.equals(this.salary, employee.salary);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.firstname, this.lastname, this.age, this.workExperience, this.salary);
    }
}
