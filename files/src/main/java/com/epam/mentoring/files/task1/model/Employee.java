package com.epam.mentoring.files.task1.model;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author Kaikenov Adilhan
 **/
public class Employee implements Serializable {

    private String firstname;

    private String lastname;

    private transient int age;

    private transient int workExperience;

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

    @Override
    public String toString() {
        return "Employee{" +
                "firstname='" + this.firstname + '\'' +
                ", lastname='" + this.lastname + '\'' +
                ", age=" + this.age +
                ", workExperience=" + this.workExperience +
                ", salary=" + this.salary +
                '}';
    }
}
