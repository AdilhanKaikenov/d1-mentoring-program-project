package com.epam.mentoring.memory.task1;

import javassist.CannotCompileException;
import javassist.ClassPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Kaikenov Adilhan
 **/
public class SubTask3 {

    private static ClassPool cp = ClassPool.getDefault();

    // 3. java.lang.OutOfMemoryError: Metaspace. Load classes continuously and make them stay in memory.
    public static void main(String[] args) throws CannotCompileException {

//        The java.lang.OutOfMemoryError: Metaspace message indicates that the Metaspace area in memory is exhausted.
//        Metaspace size requirements depend both upon the number of classes loaded as well as the size of such class declarations.
//        Either too many classes or too big classes being loaded to the Metaspace.
        for (int i = 0; ; i++) {
            Class<?> aClass = cp.makeClass("com.epam.mentoring.memory.task1.BigEntity").toClass();
        }
    }
}
