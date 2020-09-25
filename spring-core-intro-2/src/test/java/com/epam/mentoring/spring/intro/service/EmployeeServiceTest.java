package com.epam.mentoring.spring.intro.service;

import com.epam.mentoring.spring.intro.configuration.AppTestConfig;
import com.epam.mentoring.spring.intro.exceptions.InvalidFieldDataException;
import com.epam.mentoring.spring.intro.model.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppTestConfig.class)
public class EmployeeServiceTest {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private Company company;

    @Test(expected = InvalidFieldDataException.class)
    public void testHire_employeeWithInvalidFieldData_CatchInvalidFieldDataException() {
//      given
        final Employee employee = new Employee();
        employee.setFirstname("Freddie");
        employee.setLastname("Williams");
        employee.setWorkExperience(-10);
        employee.setAge(90);
        employee.setSalary(new Salary());

//      when
        this.employeeService.hireEmployee(employee, PositionTitle.DIRECTOR);
    }

    @Test
    public void testHire_employeeWithValidFieldDataAndVacantPosition_ShouldBeHired() {
//      given
        final Employee employee = new Employee();
        employee.setFirstname("Freddie");
        employee.setLastname("Williams");
        employee.setWorkExperience(10);
        employee.setAge(20);
        employee.setSalary(new Salary());

//      when
        this.employeeService.hireEmployee(employee, PositionTitle.DIRECTOR);

//      then
        Position position = this.company.getPositions().get(0);
        assertNotNull(position.getEmployee());
        assertEquals(position.getEmployee(), employee);
    }
}