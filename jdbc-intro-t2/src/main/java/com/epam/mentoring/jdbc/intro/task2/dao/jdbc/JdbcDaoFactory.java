package com.epam.mentoring.jdbc.intro.task2.dao.jdbc;

import com.epam.mentoring.jdbc.intro.task2.dao.AuthorDao;
import com.epam.mentoring.jdbc.intro.task2.dao.BookDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author Kaikenov Adilhan
 * @see AutoCloseable
 */
public class JdbcDaoFactory implements AutoCloseable {

    private static final Logger log = LoggerFactory.getLogger(JdbcDaoFactory.class);

    private Connection connection;

    public JdbcDaoFactory(Connection connection) {
        this.connection = connection;
    }

    public BookDao getBookDao() {
        log.debug("JdbcDaoFactory class, JdbcBookDao created");
        return new JdbcBookDao(this.getConnection());
    }

    public AuthorDao getAuthorDao() {
        log.debug("JdbcDaoFactory class, JdbcAuthorDao created");
        return new JdbcAuthorDao(this.getConnection());
    }


    public Connection getConnection() {
        return this.connection;
    }

    /**
     * Start transaction.
     *
     * @throws SQLException
     */
    public void beginTransaction() throws SQLException {
        this.connection.setAutoCommit(false);
    }

    /**
     * RollBack transaction.
     *
     * @throws SQLException
     */
    public void rollbackTransaction() throws SQLException {
        this.connection.rollback();
        this.connection.setAutoCommit(true);
    }

    /**
     * Commit transaction to the DataBase.
     *
     * @throws SQLException
     */
    public void endTransaction() throws SQLException {
        this.connection.commit();
        this.connection.setAutoCommit(true);
    }

    /**
     * Return connection back to pool of connections.
     */
    public void closeTransaction() throws SQLException {
        if (this.connection != null) {
            this.connection.setAutoCommit(true);
            this.connection.close();
        }
    }

    @Override
    public void close() throws SQLException {
        this.closeTransaction();
    }

}