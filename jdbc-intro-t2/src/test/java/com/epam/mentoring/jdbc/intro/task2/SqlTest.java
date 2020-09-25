package com.epam.mentoring.jdbc.intro.task2;

import com.epam.mentoring.jdbc.intro.task2.model.Author;
import com.epam.mentoring.jdbc.intro.task2.model.Book;
import com.epam.mentoring.jdbc.intro.task2.model.Genre;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.Year;

public abstract class SqlTest {

    protected Book getTestBook() {
        final Book testBook = new Book();
        testBook.setTitle("Test_title");
        testBook.setDescription("Test_description");
        testBook.setPublishYear(Year.of(2018));
        testBook.setTotalAmount(10);
        testBook.setGenre(Genre.COMPUTERS_AND_INTERNET);
        final Author author = new Author();
        author.setFirstname("Author_FirstName");
        author.setLastname("Author_LastName");
        testBook.setAuthor(author);
        return testBook;
    }

    protected String getSqlScriptFromResource(String scriptFileName) throws IOException {
        final ClassLoader classLoader = this.getClass().getClassLoader();
        final FileInputStream inputStream = new FileInputStream(classLoader.getResource(scriptFileName).getFile());
        final StringBuilder stringBuilder = new StringBuilder();
        final InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        final BufferedReader reader = new BufferedReader(inputStreamReader);

        String line = reader.readLine();
        while (null != line) {
            stringBuilder.append(' ');
            stringBuilder.append(line);
            line = reader.readLine();
        }

        return stringBuilder.toString();
    }

}
