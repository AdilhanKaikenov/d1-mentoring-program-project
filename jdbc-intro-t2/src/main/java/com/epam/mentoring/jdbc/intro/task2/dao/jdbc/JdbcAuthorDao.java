package com.epam.mentoring.jdbc.intro.task2.dao.jdbc;

import com.epam.mentoring.jdbc.intro.task2.dao.AuthorDao;
import com.epam.mentoring.jdbc.intro.task2.exceptions.DaoException;
import com.epam.mentoring.jdbc.intro.task2.model.Author;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Kaikenov Adilhan
 * @see AuthorDao
 */
public class JdbcAuthorDao extends JdbcDao<Author> implements AuthorDao {

    private static final Logger log = LoggerFactory.getLogger(JdbcAuthorDao.class);

    public JdbcAuthorDao(Connection connection) {
        super(connection);
    }

    @Override
    public Author create(Author author) throws DaoException {
        log.debug("Entering JdbcAuthorDao class, create() method.");
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = this.connection.prepareStatement("INSERT INTO authors(firstname, lastname) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(FIRST_PARAMETER_INDEX, author.getFirstname());
            preparedStatement.setString(SECOND_PARAMETER_INDEX, author.getLastname());
            preparedStatement.execute();
            resultSet = preparedStatement.getGeneratedKeys();
            Integer id = this.getID(resultSet);
            if (id != null) {
                author.setId(id);
            }
            log.debug("New author successfully created, type = {}, id = {}", author.getId());
            log.debug("Leaving JdbcAuthorDao class, create() method.");
        } catch (Exception e) {
            throw new DaoException("Error: JdbcAuthorDao class, createAuthor() method.", e);
        } finally {
            this.close(preparedStatement, resultSet);
        }
        return author;
    }

    @Override
    public Author read(Author author) throws DaoException {
        log.debug("Entering JdbcAuthorDao class, read() method");
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = this.connection.prepareStatement("SELECT * FROM authors WHERE firstname = ? AND lastname = ?");
            preparedStatement.setString(FIRST_PARAMETER_INDEX, author.getFirstname());
            preparedStatement.setString(SECOND_PARAMETER_INDEX, author.getLastname());
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                author.setId(resultSet.getInt(ID_COLUMN_NAME));
            } else {
                return null;
            }
            log.debug("Leaving JdbcAuthorDao class, read() method.");
        } catch (Exception e) {
            throw new DaoException("Error: JdbcAuthorDao class read() method. I can not read author.", e);
        } finally {
            this.close(preparedStatement, resultSet);
        }
        return author;
    }

    @Override
    protected List<Author> createListFrom(ResultSet resultSet) throws DaoException {
        log.debug("Entering JdbcAuthorDao class, createListFrom() method");
        List<Author> result = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Author author = new Author();
                author.setId(resultSet.getInt(ID_COLUMN_NAME));
                author.setFirstname(resultSet.getString("FIRSTNAME"));
                author.setLastname(resultSet.getString("LASTNAME"));
                log.debug("Author successfully created in createListFrom() method. Author id = {}", author.getId());
                result.add(author);
            }
            log.debug("Leaving JdbcAuthorDao class, createListFrom() method. Amount of authors = {}", result.size());
        } catch (Exception e) {
            throw new DaoException("Error: JdbcAuthorDao class createListFrom() method. I can not create List of authors getFromValue resultSet.", e);
        }
        return result;
    }

    protected PreparedStatement setFieldsInDeleteByEntityStatement(PreparedStatement preparedStatement, Author author) throws DaoException {
        log.debug("Entering JdbcAuthorDao class, setFieldsInDeleteByEntityStatement() method.");
        try {
            preparedStatement.setString(FIRST_PARAMETER_INDEX, author.getFirstname());
            preparedStatement.setString(SECOND_PARAMETER_INDEX, author.getLastname());
            log.debug("Leaving JdbcAuthorDao class, setFieldsInDeleteByEntityStatement() method.");
        } catch (Exception e) {
            throw new DaoException("Error: JdbcAuthorDao class setFieldsInDeleteByEntityStatement() method. I can not set fields into statement.", e);
        }
        return preparedStatement;
    }

    @Override
    public Author read(int id) throws DaoException {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    protected String getReadByIdQuery() {
        return "SELECT * FROM authors WHERE id = ?";
    }

    @Override
    protected String getUpdateByEntityQuery() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    protected String getDeleteQuery() {
        return "DELETE FROM authors WHERE firstname = ? AND lastname = ?";
    }



    @Override
    protected PreparedStatement setFieldsInUpdateByEntityPreparedStatement(PreparedStatement preparedStatement, Author entity) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
