package com.epam.mentoring.spring.intro.service;

import com.epam.mentoring.spring.intro.exceptions.InvalidFieldDataException;
import com.epam.mentoring.spring.intro.model.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:/ApplicationTestContext.xml"})
public class PositionServiceTest {

    private ConfigurableApplicationContext testContext;

    @Before
    public void initApplicationTestContext() {
        this.testContext = new ClassPathXmlApplicationContext("classpath:/ApplicationTestContext.xml");
    }

    @Test(expected = InvalidFieldDataException.class)
    public void testCreatePosition_employeeWithInvalidFieldData_CatchInvalidFieldDataException() {
        PositionService positionService = this.testContext.getBean(PositionService.class);

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
        positionService.createPosition(position);
    }

    @Test
    public void testCreatePosition_positionWithValidFieldDataAndEmployee_PositionShouldBeAdded() {
        PositionService positionService = this.testContext.getBean(PositionService.class);
        final Company company = this.testContext.getBean(Company.class);

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
        positionService.createPosition(position);

//      then
        Position specialist = company.getPositions().get(2);
        assertThat(company.getPositions(), hasSize(3));
        assertEquals(specialist.getTitle(), PositionTitle.SPECIALIST);
    }


}