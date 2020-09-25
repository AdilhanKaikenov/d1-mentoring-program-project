package com.epam.mentoring.javacore.task2;

import com.epam.mentoring.javacore.task1.MainApp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Kaikenov Adilhan
 **/
public class DynamicDoubleArrayTest {

    private static final Logger log = LoggerFactory.getLogger(DynamicDoubleArrayTest.class);

    public static void main(String[] args) {

        DynamicDoubleArray doubleArray = new DynamicDoubleArray();

        // add
        doubleArray.add(2.1D);
        doubleArray.add(2.2D);
        doubleArray.add(2.3D);
        doubleArray.add(2.4D);
        doubleArray.add(2.5D);
        doubleArray.add(2.6D);
        doubleArray.add(2.7D);
        doubleArray.add(2.8D);
        doubleArray.add(2.9D);

        // get
        final double item1 = doubleArray.get(0);
        final double item6 = doubleArray.get(5);

        log.info("item 1 = {}", item1);
        log.info("item 6 = {}", item6);

        // remove
        log.info("Before remove(5) : {}; size = {}", doubleArray.toString(), doubleArray.getSize());
        doubleArray.remove(5);
        log.info("After remove(5) : {}; size = {}", doubleArray.toString(), doubleArray.getSize());

        // initial capacity
        DynamicDoubleArray arrayWithCapacity = new DynamicDoubleArray(15);
        log.info("Array capacity = {}", arrayWithCapacity.getCapacity());

    }
}
