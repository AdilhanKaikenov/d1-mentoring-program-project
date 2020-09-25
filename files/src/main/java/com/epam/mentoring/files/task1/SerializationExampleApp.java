package com.epam.mentoring.files.task1;

import com.epam.mentoring.files.task1.model.Employee;
import com.epam.mentoring.files.task1.model.Salary;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * @author Kaikenov Adilhan
 **/
public class SerializationExampleApp {

    public static void main(String[] args) {

        try (FileOutputStream fileOutputStream = new FileOutputStream("employee.data");
             ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream)) {

            Employee employee = new Employee();
            employee.setFirstname("Jackie");
            employee.setLastname("Chan");
            employee.setAge(64);
            employee.setWorkExperience(45);
            employee.setSalary(new Salary(1_000_000D));

            outputStream.writeObject(employee);
            outputStream.flush();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
