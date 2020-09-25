package com.epam.mentoring.jdbc.intro.task2.dao.jdbc;

import com.epam.mentoring.jdbc.intro.task2.dao.Dao;
import com.epam.mentoring.jdbc.intro.task2.exceptions.DaoException;
import com.epam.mentoring.jdbc.intro.task2.model.BaseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public abstract class JdbcDao<T extends BaseEntity> implements Dao<T> {

    private static final Logger log = LoggerFactory.getLogger(JdbcDao.class);

    protected static final String ID_COLUMN_NAME = "ID";

    protected static final int FIRST_COLUMN_INDEX = 1;

    protected static final int FIRST_PARAMETER_INDEX = 1;
    protected static final int SECOND_PARAMETER_INDEX = 2;
    protected static final int THIRD_PARAMETER_INDEX = 3;
    protected static final int FOURTH_PARAMETER_INDEX = 4;
    protected static final int FIFTH_PARAMETER_INDEX = 5;
    protected static final int SIXTH_PARAMETER_INDEX = 6;
    protected static final int SEVENTH_PARAMETER_INDEX = 7;

    protected Connection connection;

    public JdbcDao(Connection connection) {
        this.connection = connection;
    }

    public Connection getConnection() {
        return this.connection;
    }

    @Override
    public T read(int id) throws DaoException {
        log.debug("Entering JdbcDao class, read(id) method");
        T result;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = this.connection.prepareStatement(this.getReadByIdQuery());
            preparedStatement = this.setFieldInReadByIdPreparedStatement(preparedStatement, id);
            resultSet = preparedStatement.executeQuery();
            result = this.createFrom(resultSet);
            log.debug("Leaving JdbcDao class, read(id) method.");
        } catch (Exception e) {
            throw new DaoException("Error: JdbcDao class read(id) method. I can not read entity by id getFromValue resultSet.", e);
        } finally {
            this.close(preparedStatement, resultSet);
        }
        return result;
    }

    protected T createFrom(ResultSet resultSet) throws DaoException {
        log.debug("Entering JdbcDao class, createFrom() method");
        T entity;
        List<T> entities = this.createListFrom(resultSet);
        entity = entities.stream().findFirst().orElse(null);
        log.debug("Leaving JdbcDao class, createFrom() method.");
        return entity;
    }

    @Override
    public void update(T entity) throws DaoException {
        log.debug("Entering JdbcDao class, update() method. ");
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = this.connection.prepareStatement(this.getUpdateByEntityQuery());
            preparedStatement = this.setFieldsInUpdateByEntityPreparedStatement(preparedStatement, entity);
            preparedStatement.execute();
            log.debug("Leaving JdbcDao class, update() method.");
        } catch (Exception e) {
            throw new DaoException("Error: JdbcDao class update() method. I can not update entity. ", e);
        } finally {
            this.closePreparedStatement(preparedStatement);
        }
    }

    @Override
    public void delete(T entity) throws DaoException {
        log.debug("Entering JdbcDao class, delete() method. Entity ID = {}", entity.getId());
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = this.connection.prepareStatement(this.getDeleteQuery());
            preparedStatement = this.setFieldsInDeleteByEntityStatement(preparedStatement, entity);
            preparedStatement.execute();
            log.debug("Leaving JdbcDao class, delete() method.");
        } catch (Exception e) {
            throw new DaoException("Error: JdbcDao class delete() method. I can not delete entity. ", e);
        } finally {
            this.closePreparedStatement(preparedStatement);
        }
    }

    protected Integer getID(ResultSet generatedKeys) throws DaoException {
        log.debug("Entering JdbcDao class, getID() method.");
        Integer id = null;
        try {
            while (generatedKeys.next()) {
                id = generatedKeys.getInt(FIRST_COLUMN_INDEX);
            }
            log.debug("Leaving JdbcDao class getID() method. result = {}", id);
        } catch (Exception e) {
            throw new DaoException("Error: I can not get the ID getFromValue generatedKeys, JdbcDao class, getID() method.", e);
        }
        return id;
    }

    protected void close(PreparedStatement preparedStatement, ResultSet resultSet) throws DaoException {
        this.closePreparedStatement(preparedStatement);
        this.closeResultSet(resultSet);
    }

    private void closePreparedStatement(PreparedStatement preparedStatement) throws DaoException {
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (Exception e) {
                throw new DaoException("Error: JdbcDao class, closePreparedStatement() method. Can not close preparedStatement.", e);
            }
        }
    }

    protected void closeResultSet(ResultSet resultSet) throws DaoException {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (Exception e) {
                throw new DaoException("Error: JdbcDao class, closeResultSet() method. Can not close resultSet.", e);
            }
        }
    }

    protected PreparedStatement setFieldInReadByIdPreparedStatement(PreparedStatement preparedStatement, int id) throws DaoException {
        log.debug("Entering JdbcDao class, setFieldInReadByIdPreparedStatement() method. ID = {}", id);
        try {
            preparedStatement.setInt(FIRST_PARAMETER_INDEX, id);
            log.debug("Leaving JdbcDao class, setFieldInReadByIdPreparedStatement() method.");
        } catch (Exception e) {
            throw new DaoException("Error: JdbcDao class setFieldInReadByIdPreparedStatement() method. I can not set field into statement.", e);
        }
        return preparedStatement;
    }

    protected PreparedStatement setFieldsInDeleteByEntityStatement(PreparedStatement preparedStatement, T entity) throws DaoException {
        log.debug("Entering JdbcDao class, setFieldsInDeleteByEntityStatement() method.");
        try {
            preparedStatement.setInt(FIRST_PARAMETER_INDEX, entity.getId());
            log.debug("Leaving JdbcDao class, setFieldsInDeleteByEntityStatement() method.");
        } catch (Exception e) {
            throw new DaoException("Error: JdbcDao class setFieldsInDeleteByEntityStatement() method. I can not set fields into statement.", e);
        }
        return preparedStatement;
    }

    protected abstract List<T> createListFrom(ResultSet resultSet) throws DaoException;

    protected abstract String getReadByIdQuery();

    protected abstract String getUpdateByEntityQuery();

    protected abstract String getDeleteQuery();

    protected abstract PreparedStatement setFieldsInUpdateByEntityPreparedStatement(PreparedStatement preparedStatement, T entity) throws DaoException;
}
