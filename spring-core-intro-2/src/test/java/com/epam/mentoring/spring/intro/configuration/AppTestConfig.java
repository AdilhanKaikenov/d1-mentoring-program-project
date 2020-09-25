package com.epam.mentoring.spring.intro.configuration;

import com.epam.mentoring.spring.intro.model.*;
import com.epam.mentoring.spring.intro.service.EmployeeService;
import com.epam.mentoring.spring.intro.service.PositionService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class AppTestConfig {

    @Bean(name = "employee1")
    public Employee getEmployeeOne() {
        final Employee employee = new Employee();
        employee.setFirstname("Oscar");
        employee.setLastname("Smith");
        employee.setAge(50);
        employee.setWorkExperience(20);
        employee.setSalary(this.getSalary());
        return employee;
    }

    @Bean(name = "position1")
    public Position getPositionOne() {
        final Position position = new Position();
        position.setTitle(PositionTitle.DIRECTOR);
        position.setMonthlyRate(200000);
        position.setEmployee(null);
        return position;
    }

    @Bean(name = "position2")
    public Position getPositionTwo() {
        final Position position = new Position();
        position.setTitle(PositionTitle.ASSISTANT_DIRECTOR);
        position.setMonthlyRate(150000);
        position.setEmployee(null);
        return position;
    }

    @Bean(name = "company")
    public Company getCompany() {
        final Company company = new Company();

        final List<Position> positions = new ArrayList<>();
        positions.add(this.getPositionOne());
        positions.add(this.getPositionTwo());

        company.setPositions(positions);

        return company;
    }

    @Bean
    @Scope("prototype")
    public Salary getSalary() {
        return new Salary();
    }

    @Bean
    public LocalValidatorFactoryBean validator() {
        return new LocalValidatorFactoryBean();
    }

    @Bean
    public EmployeeService employeeService() {
        final EmployeeService employeeService = new EmployeeService();
        employeeService.setCompany(this.getCompany());
        employeeService.setValidator(this.validator());
        return employeeService;
    }

    @Bean
    public PositionService positionService() {
        final PositionService positionService = new PositionService();
        positionService.setCompany(this.getCompany());
        positionService.setValidator(this.validator());
        return positionService;
    }
}
