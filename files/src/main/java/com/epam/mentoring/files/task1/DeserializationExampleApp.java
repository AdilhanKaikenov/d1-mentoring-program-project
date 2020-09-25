package com.epam.mentoring.files.task1;

import com.epam.mentoring.files.task1.model.Employee;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * @author Kaikenov Adilhan
 **/
public class DeserializationExampleApp {

    public static void main(String[] args) {

        try (FileInputStream fileInputStream = new FileInputStream("employee.data");
             ObjectInputStream inputStream = new ObjectInputStream(fileInputStream)) {

            Employee employee = (Employee) inputStream.readObject();

            System.out.println(employee);

        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
