package com.epam.mentoring.spring.intro.service;

import com.epam.mentoring.spring.intro.model.Company;
import com.epam.mentoring.spring.intro.model.Employee;
import com.epam.mentoring.spring.intro.model.Position;

public class SalaryService {

    private static final double COEFFICIENT = 0.85;
    private static final double INFLATION_PREMIUM_COEFFICIENT = 1.1;
    private static final int SURCHARGE = 3000;

    private Company company;

    public SalaryService() {
    }

    public SalaryService(Company company) {
        this.company = company;
    }

    public void giveSalary() {
        for (Position position : this.company.getPositions()) {
            if (position.isVacant()) {
                continue;
            }

            Employee employee = position.getEmployee();
            double salary = position.getMonthlyRate() + employee.getWorkExperience() * COEFFICIENT * SURCHARGE;
            salary = salary * INFLATION_PREMIUM_COEFFICIENT;

            employee.deposit(salary);
        }
    }
}
