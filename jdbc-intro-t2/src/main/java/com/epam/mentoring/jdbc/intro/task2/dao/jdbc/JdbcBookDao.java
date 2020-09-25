package com.epam.mentoring.jdbc.intro.task2.dao.jdbc;

import com.epam.mentoring.jdbc.intro.task2.dao.BookDao;
import com.epam.mentoring.jdbc.intro.task2.exceptions.DaoException;
import com.epam.mentoring.jdbc.intro.task2.model.Author;
import com.epam.mentoring.jdbc.intro.task2.model.Book;
import com.epam.mentoring.jdbc.intro.task2.model.Genre;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Kaikenov Adilhan
 * @see BookDao
 */
public class JdbcBookDao extends JdbcDao<Book> implements BookDao {

    private static final Logger log = LoggerFactory.getLogger(JdbcBookDao.class);

    private static final String ID_COLUMN_NAME = "ID";
    private static final String GENRE_COLUMN_NAME = "GENRE";
    private static final String TITLE_COLUMN_NAME = "TITLE";
    private static final String AUTHOR_ID_COLUMN_NAME = "AUTHOR_ID";
    private static final String AUTHOR_FIRSTNAME_COLUMN_NAME = "AUTHOR_FIRSTNAME";
    private static final String AUTHOR_LASTNAME_COLUMN_NAME = "AUTHOR_LASTNAME";
    private static final String DESCRIPTION_COLUMN_NAME = "DESCRIPTION";
    private static final String TOTAL_AMOUNT_COLUMN_NAME = "TOTAL_AMOUNT";
    private static final String PUBLISH_YEAR_COLUMN_NAME = "PUBLISH_YEAR";

    public JdbcBookDao(Connection connection) {
        super(connection);
    }

    @Override
    public Book create(Book entity) throws DaoException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = this.connection.prepareStatement("INSERT INTO book (title, author, publish_year, genre, description, total_amount) VALUES (?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement = this.setFieldsInCreatePreparedStatement(preparedStatement, entity);
            preparedStatement.execute();
            resultSet = preparedStatement.getGeneratedKeys();
            Integer id = this.getID(resultSet);
            if (id != null) {
                entity.setId(id);
            }
            log.debug("New entity successfully created, type = {}, id = {}", entity.getClass().getSimpleName(), entity.getId());
            log.debug("Leaving JdbcBookDao class, create() method.");
        } catch (Exception e) {
            throw new DaoException("Error: JdbcBookDao class, create() method.", e);
        } finally {
            this.close(preparedStatement, resultSet);
        }
        return entity;
    }

    @Override
    public int countBookByAuthor(Author author) throws DaoException {
        log.debug("Entering JdbcBookDao class, countBookByAuthor(id) method");
        int count = 0;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = this.connection.prepareStatement(
                    "SELECT COUNT(*) " +
                    "FROM book " +
                    "INNER JOIN authors ON book.author = authors.id " +
                    "WHERE authors.firstname = ? AND authors.lastname = ?");

            preparedStatement.setString(FIRST_PARAMETER_INDEX, author.getFirstname());
            preparedStatement.setString(SECOND_PARAMETER_INDEX, author.getLastname());

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                count = resultSet.getInt(FIRST_COLUMN_INDEX);
            }
            log.debug("Leaving JdbcBookDao class, countBookByAuthor(id) method.");
        } catch (Exception e) {
            throw new DaoException("Error: JdbcBookDao class countBookByAuthor(id) method.", e);
        } finally {
            this.close(preparedStatement, resultSet);
        }
        return count;
    }

    @Override
    public Book read(Book entity) throws DaoException {
        throw new UnsupportedOperationException("Not implemented yet");
    }


    private PreparedStatement setFieldsInCreatePreparedStatement(PreparedStatement preparedStatement, Book book) throws DaoException {
        log.debug("Entering JdbcBookDao class, setFieldsInCreatePreparedStatement() method.");
        try {
            preparedStatement.setString(FIRST_PARAMETER_INDEX, book.getTitle());
            preparedStatement.setInt(SECOND_PARAMETER_INDEX, book.getAuthor().getId());
            preparedStatement.setInt(THIRD_PARAMETER_INDEX, book.getPublishYear().getValue());
            preparedStatement.setInt(FOURTH_PARAMETER_INDEX, book.getGenre().ordinal());
            preparedStatement.setString(FIFTH_PARAMETER_INDEX, book.getDescription());
            preparedStatement.setInt(SIXTH_PARAMETER_INDEX, book.getTotalAmount());
            log.debug("Leaving JdbcBookDao class, setFieldsInCreatePreparedStatement() method.");
        } catch (Exception e) {
            throw new DaoException("Error: JdbcBookDao class setFieldsInCreatePreparedStatement() method. I can not set fields into statement.", e);
        }
        return preparedStatement;
    }

    protected PreparedStatement setFieldsInUpdateByEntityPreparedStatement(PreparedStatement preparedStatement, Book book) throws DaoException {
        log.debug("Entering JdbcBookDao class, setFieldsInUpdateByEntityPreparedStatement() method.");
        try {
            preparedStatement.setString(FIRST_PARAMETER_INDEX, book.getTitle());
            preparedStatement.setInt(SECOND_PARAMETER_INDEX, book.getAuthor().getId());
            preparedStatement.setInt(THIRD_PARAMETER_INDEX, book.getPublishYear().getValue());
            preparedStatement.setInt(FOURTH_PARAMETER_INDEX, book.getGenre().ordinal());
            preparedStatement.setString(FIFTH_PARAMETER_INDEX, book.getDescription());
            preparedStatement.setInt(SIXTH_PARAMETER_INDEX, book.getTotalAmount());
            preparedStatement.setInt(SEVENTH_PARAMETER_INDEX, book.getId());
            log.debug("Leaving JdbcBookDao class, setFieldsInUpdateByEntityPreparedStatement() method.");
        } catch (Exception e) {
            throw new DaoException("Error: JdbcBookDao class setFieldsInUpdateByEntityPreparedStatement() method. I can not set fields into statement.", e);
        }
        return preparedStatement;
    }

    @Override
    protected List<Book> createListFrom(ResultSet resultSet) throws DaoException {
        log.debug("Entering JdbcBookDao class, createListFrom() method");
        List<Book> result = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Book book = new Book();
                log.debug("Creating book getFromValue resultSet");
                book.setId(resultSet.getInt(ID_COLUMN_NAME));
                book.setTitle(resultSet.getString(TITLE_COLUMN_NAME));
                Author authorInstance = new Author();
                authorInstance.setId(resultSet.getInt(AUTHOR_ID_COLUMN_NAME));
                authorInstance.setFirstname(resultSet.getString(AUTHOR_FIRSTNAME_COLUMN_NAME));
                authorInstance.setLastname(resultSet.getString(AUTHOR_LASTNAME_COLUMN_NAME));
                book.setAuthor(authorInstance);
                book.setPublishYear(Year.of(resultSet.getInt(PUBLISH_YEAR_COLUMN_NAME)));
                book.setGenre(Genre.getFromValue(resultSet.getString(GENRE_COLUMN_NAME)));
                book.setDescription(resultSet.getString(DESCRIPTION_COLUMN_NAME));
                book.setTotalAmount(resultSet.getInt(TOTAL_AMOUNT_COLUMN_NAME));
                log.debug("Book successfully created in createListFrom() method. Book id = {}", book.getId());
                result.add(book);
            }
            log.debug("Leaving JdbcBookDao class, createListFrom() method. Amount of books = {}", result.size());
        } catch (Exception e) {
            throw new DaoException("Error: JdbcBookDao class createListFrom() method. I can not create List of books getFromValue resultSet.", e);
        }
        return result;
    }

    @Override
    protected String getReadByIdQuery() {
        return "SELECT " +
                "book.id, " +
                "book.title, " +
                "authors.firstname as author_firstname, " +
                "authors.id as author_id, " +
                "authors.lastname as author_lastname, " +
                "book.publish_year, " +
                "genre.type AS genre, " +
                "book.description," +
                " book.total_amount " +
                "FROM book " +
                "INNER JOIN authors ON book.author = authors.id " +
                "INNER JOIN genre ON book.genre = genre.id " +
                "WHERE book.id = ?";
    }

    @Override
    protected String getUpdateByEntityQuery() {
        return "UPDATE book SET title = ?, author = ?, publish_year = ?, genre = ?, description = ?, total_amount = ? WHERE id = ?";
    }

    @Override
    protected String getDeleteQuery() {
        return "DELETE FROM book WHERE id = ?";
    }
}
