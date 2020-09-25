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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:/ApplicationTestContext.xml"})
public class EmployeeServiceTest {

    private ConfigurableApplicationContext testContext;

    @Before
    public void initApplicationTestContext() {
        this.testContext = new ClassPathXmlApplicationContext("classpath:/ApplicationTestContext.xml");
    }

    @Test(expected = InvalidFieldDataException.class)
    public void testHire_employeeWithInvalidFieldData_CatchInvalidFieldDataException() {
        final EmployeeService employeeService = this.testContext.getBean(EmployeeService.class);

//      given
        final Employee employee = new Employee();
        employee.setFirstname("Freddie");
        employee.setLastname("Williams");
        employee.setWorkExperience(-10);
        employee.setAge(90);
        employee.setSalary(new Salary());

//      when
        employeeService.hireEmployee(employee, PositionTitle.DIRECTOR);
    }

    @Test
    public void testHire_employeeWithValidFieldDataAndVacantPosition_ShouldBeHired() {
        final EmployeeService employeeService = this.testContext.getBean(EmployeeService.class);
        final Company company = this.testContext.getBean(Company.class);

//      given
        final Employee employee = new Employee();
        employee.setFirstname("Freddie");
        employee.setLastname("Williams");
        employee.setWorkExperience(10);
        employee.setAge(20);
        employee.setSalary(new Salary());

//      when
        employeeService.hireEmployee(employee, PositionTitle.DIRECTOR);

//      then
        Position position = company.getPositions().get(0);
        assertNotNull(position.getEmployee());
        assertEquals(position.getEmployee(), employee);
    }
}