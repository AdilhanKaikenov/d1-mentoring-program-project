package com.epam.mentoring.spring.intro.service;

import com.epam.mentoring.spring.intro.configuration.AppTestConfig;
import com.epam.mentoring.spring.intro.exceptions.InvalidFieldDataException;
import com.epam.mentoring.spring.intro.model.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppTestConfig.class)
public class PositionServiceTest {

    @Autowired
    private PositionService positionService;

    @Autowired
    private Company company;

    @Test(expected = InvalidFieldDataException.class)
    public void testCreatePosition_positionWithInvalidFieldData_CatchInvalidFieldDataException() {
//      given
        final Employee employee = new Employee();
        employee.setFirstname("Freddie");
        employee.setLastname("Williams");
        employee.setWorkExperience(-10);
        employee.setAge(90);
        employee.setSalary(new Salary());

        final Position position = new Position();
        position.setTitle(PositionTitle.SPECIALIST);
        position.setMonthlyRate(100000);
        position.setEmployee(employee);

//      when
        this.positionService.createPosition(position);
    }

    @Test
    public void testCreatePosition_positionWithValidFieldDataAndEmployee_PositionShouldBeAdded() {
//      given
        final Employee employee = new Employee();
        employee.setFirstname("Freddie");
        employee.setLastname("Williams");
        employee.setWorkExperience(10);
        employee.setAge(20);
        employee.setSalary(new Salary());

        final Position position = new Position();
        position.setTitle(PositionTitle.SPECIALIST);
        position.setMonthlyRate(100000);
        position.setEmployee(employee);

//      when
        this.positionService.createPosition(position);

//      then
        Position specialist = this.company.getPositions().get(2);
        assertThat(this.company.getPositions(), hasSize(3));
        assertEquals(specialist.getTitle(), PositionTitle.SPECIALIST);
    }


}