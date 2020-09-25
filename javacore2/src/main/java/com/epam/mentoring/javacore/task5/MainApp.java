package com.epam.mentoring.javacore.task5;

import com.epam.mentoring.javacore.task1.model.Dimension;
import com.epam.mentoring.javacore.task1.model.appliance.impl.Fridge;
import com.epam.mentoring.javacore.task1.model.appliance.impl.Washer;

import java.util.ArrayList;

/**
 * @author Kaikenov Adilhan
 **/
public class MainApp {
    public static void main(String[] args) {

        Fridge fridge = new Fridge("Fridge", new Dimension(10, 10, 10), 150, 4, new ArrayList<>(), 30);
        Washer washer = new Washer("Washer", new Dimension(10, 10, 10), 450, 10);

        ProdRunner.run(fridge);
        ProdRunner.run(washer);
    }
}
