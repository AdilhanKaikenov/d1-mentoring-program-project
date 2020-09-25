package com.epam.mentoring.jdbc.intro.task2.dao;

import com.epam.mentoring.jdbc.intro.task2.exceptions.DaoException;
import com.epam.mentoring.jdbc.intro.task2.model.Author;
import com.epam.mentoring.jdbc.intro.task2.model.Book;

/**
 * @author Kaikenov Adilhan
 * @see Dao
 */
public interface BookDao extends Dao<Book> {

    int countBookByAuthor(Author author) throws DaoException;

}
