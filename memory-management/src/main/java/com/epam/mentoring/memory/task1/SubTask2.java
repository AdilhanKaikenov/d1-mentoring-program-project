package com.epam.mentoring.memory.task1;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author Kaikenov Adilhan
 **/
public class SubTask2 {

//        2. java.lang.OutOfMemoryError: Java heap space. Create big objects continuously and make them stay in memory.
//          Do not use arrays or collections.
    public static void main(String[] args) throws Exception {

        URL url = new URL("https://drive.google.com/file/d/1TO25Ax34t8HvcdVKKyAsPJrXeGIiLTOj/view?usp=sharing");
        try (InputStream is = url.openStream();
             BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
        }
    }
}
