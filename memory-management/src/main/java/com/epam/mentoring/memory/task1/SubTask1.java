package com.epam.mentoring.memory.task1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Kaikenov Adilhan
 **/
public class SubTask1 {

    private static final Logger log = LoggerFactory.getLogger(SubTask1.class);

//        1. java.lang.OutOfMemoryError: Java heap space. You can use different data structures. Do not tune heap size.
    public static void main(String[] args) {

//        I created an array of Double type objects.
//        The array is stored references(pointers) to objects in the Heap memory.
//        So, in the Heap memory will be created a large number of Double type objects
//        and it will fill all of the available memory in the Heap, as a result
//        the java.lang.OutOfMemoryError:Java heap space is thrown.
        try {
            Double[] array = new Double[1000000000];
        } catch (OutOfMemoryError error) {
            System.out.println(Runtime.getRuntime().freeMemory());
            log.error("OutOfMemoryError", error);
        }
    }
}
