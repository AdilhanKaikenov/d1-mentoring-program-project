package com.epam.mentoring.spring.intro.configuration;

import com.epam.mentoring.spring.intro.model.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class AppConfig {

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

    @Bean(name = "employee2")
    public Employee getEmployeeTwo() {
        final Employee employee = new Employee();
        employee.setFirstname("Freddie");
        employee.setLastname("Williams");
        employee.setAge(20);
        employee.setWorkExperience(10);
        employee.setSalary(this.getSalary());
        return employee;
    }

    @Bean(name = "employee3")
    public Employee getEmployeeThree() {
        final Employee employee = new Employee();
        employee.setFirstname("George");
        employee.setLastname("Miller");
        employee.setAge(50);
        employee.setWorkExperience(15);
        employee.setSalary(this.getSalary());
        return employee;
    }

    @Bean(name = "employee4")
    public Employee getEmployeeFour() {
        final Employee employee = new Employee();
        employee.setFirstname("Jacob");
        employee.setLastname("Brown");
        employee.setAge(25);
        employee.setWorkExperience(5);
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

    @Bean(name = "position3")
    public Position getPositionThree() {
        final Position position = new Position();
        position.setTitle(PositionTitle.MANAGER);
        position.setMonthlyRate(110000);
        position.setEmployee(null);
        return position;
    }

    @Bean(name = "position4")
    public Position getPositionFour() {
        final Position position = new Position();
        position.setTitle(PositionTitle.ACCOUNTANT);
        position.setMonthlyRate(130000);
        position.setEmployee(null);
        return position;
    }

    @Bean(name = "position5")
    public Position getPositionFive() {
        final Position position = new Position();
        position.setTitle(PositionTitle.SPECIALIST);
        position.setMonthlyRate(100000);
        position.setEmployee(null);
        return position;
    }

    @Bean(name = "company")
    public Company getCompany() {
        final Company company = new Company();

        final List<Position> positions = new ArrayList<>();
        positions.add(this.getPositionOne());
        positions.add(this.getPositionTwo());
        positions.add(this.getPositionThree());
        positions.add(this.getPositionFour());
        positions.add(this.getPositionFive());

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

}
