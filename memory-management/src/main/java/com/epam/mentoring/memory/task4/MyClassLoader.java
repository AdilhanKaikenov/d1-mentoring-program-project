package com.epam.mentoring.memory.task4;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author Kaikenov Adilhan
 **/
public class MyClassLoader extends ClassLoader {

    @Override
    protected Class<?> findClass(final String name) throws ClassNotFoundException {
        byte[] bt = this.loadClassData(name);
        return this.defineClass(name, bt, 0, bt.length);
    }

    private byte[] loadClassData(String className) {
        InputStream is =
                this.getClass()
                .getClassLoader()
                .getResourceAsStream(className.replace('.', File.separatorChar) + ".class");
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        int next;
        try {
            while((next = is.read()) != -1){
                outputStream.write(next);
            }
        } catch (IOException e) {
            throw new RuntimeException("");
        }
        return outputStream.toByteArray();
    }
}
