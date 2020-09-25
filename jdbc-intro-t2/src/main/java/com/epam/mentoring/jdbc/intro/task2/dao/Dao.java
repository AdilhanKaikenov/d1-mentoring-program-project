package com.epam.mentoring.jdbc.intro.task2.dao;

import com.epam.mentoring.jdbc.intro.task2.exceptions.DaoException;
import com.epam.mentoring.jdbc.intro.task2.model.BaseEntity;

/**
 * @author Kaikenov Adilhan
 */
public interface Dao<T extends BaseEntity> {

    T create(T entity) throws DaoException;

    T read(int id) throws DaoException;

    T read(T entity) throws DaoException;

    void update(T entity) throws DaoException;

    void delete(T entity) throws DaoException;

}