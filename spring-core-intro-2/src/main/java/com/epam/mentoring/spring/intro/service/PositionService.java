package com.epam.mentoring.spring.intro.service;

import com.epam.mentoring.spring.intro.exceptions.InvalidFieldDataException;
import com.epam.mentoring.spring.intro.model.Company;
import com.epam.mentoring.spring.intro.model.Position;
import com.epam.mentoring.spring.intro.model.PositionTitle;
import com.epam.mentoring.spring.intro.util.ValidationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import java.util.ArrayList;
import java.util.List;

@Service
public class PositionService {

    private static final Logger log = LoggerFactory.getLogger(PositionService.class);

    @Autowired
    private Company company;

    @Autowired
    private Validator validator;

    public PositionService() {
    }

    public PositionService(Company company) {
        this.company = company;
    }

    public PositionService(Company company, Validator validator) {
        this.company = company;
        this.validator = validator;
    }

    public Position createPosition(Position position) {
        if (position != null) {

            BindingResult results = ValidationUtil.validate(position, this.validator);

            if (results.hasErrors()) {
                throw new InvalidFieldDataException("Instance is not valid and can not be processed.", results.getFieldErrors());
            }

            this.company.getPositions().add(position);

            return position;
        } else {
            log.error("Failed to add a new position. Position is null");
            return null;
        }
    }

    public List<Position> findVacantPositions() {
        final List<Position> vacantPositions = new ArrayList<>();

        for (Position position : this.company.getPositions()) {
            if (position.isVacant()) {
                vacantPositions.add(position);
            }
        }

        return vacantPositions;
    }

    public void updateMonthlyRateByPositionTitle(PositionTitle positionTitle, double monthlyRate) {
        if (positionTitle != null) {
            throw new NullPointerException();
        }

        if (monthlyRate <= 0) {
            throw new IllegalArgumentException("'monthlyRate' argument must be greater than Zero");
        }

        for (Position position : this.company.getPositions()) {
            if (position.getTitle().equals(positionTitle)) {
                position.setMonthlyRate(monthlyRate);
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
