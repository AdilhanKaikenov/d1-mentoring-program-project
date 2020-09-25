package com.epam.mentoring.spring.intro.service;

import com.epam.mentoring.spring.intro.exceptions.InvalidFieldDataException;
import com.epam.mentoring.spring.intro.model.Company;
import com.epam.mentoring.spring.intro.model.Employee;
import com.epam.mentoring.spring.intro.model.Position;
import com.epam.mentoring.spring.intro.model.PositionTitle;
import com.epam.mentoring.spring.intro.util.ValidationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

public class EmployeeService {

    private static final Logger log = LoggerFactory.getLogger(EmployeeService.class);

    private Company company;

    private Validator validator;

    public EmployeeService() {
    }

    public EmployeeService(Company company) {
        this.company = company;
    }

    public void hireEmployee(Employee employee, PositionTitle positionTitle) {
        if (employee != null) {
            BindingResult results = ValidationUtil.validate(employee, this.validator);

            if (results.hasErrors()) {
                throw new InvalidFieldDataException("Instance is not valid and can not be processed.", results.getFieldErrors());
            }

            for (Position position : this.company.getPositions()) {
                if (position.isVacant() && position.getTitle().equals(positionTitle)) {
                    position.setEmployee(employee);
                }
            }
        } else {
            log.error("Failed to hire employee. Employee is null.");
        }
    }

    public void fireEmployee(Employee employee) {
        if (employee == null) {
            throw new NullPointerException();
        }

        for (Position position : this.company.getPositions()) {
            if (position.isVacant() && position.getEmployee().equals(employee)) {
                position.setEmployee(null);
            }
        }
    }

    public Company getCompany() {
        return this.company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Validator getValidator() {
        return this.validator;
    }

    public void setValidator(Validator validator) {
        this.validator = validator;
    }
}
