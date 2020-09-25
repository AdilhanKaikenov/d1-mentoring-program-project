package com.epam.mentoring.jdbc.intro.task2;

import com.epam.mentoring.jdbc.intro.task2.model.Author;
import com.epam.mentoring.jdbc.intro.task2.model.Book;
import com.epam.mentoring.jdbc.intro.task2.model.Genre;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.database.IDatabaseConnection;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.sql.*;
import java.time.Year;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class BookStoredFunctionTest extends SqlTest {

    private IDatabaseConnection connection = null;
    private JdbcDatabaseTester jdbcDatabaseTester = null;

    private static String schemaSql = null;
    private static String dataSql = null;
    private static String storedFunctions = null;

    @Before
    public void init() throws Exception {
        this.jdbcDatabaseTester =
                new JdbcDatabaseTester("org.postgresql.Driver",
                        "jdbc:postgresql://localhost:5432/jdbc_intro_task2_library",
                        "postgres", "admin");
        this.connection = this.jdbcDatabaseTester.getConnection();
        final Connection conn = this.connection.getConnection();
        final Statement statement = conn.createStatement();

        if (schemaSql == null) {
            schemaSql = this.getSqlScriptFromResource("schema.sql");
            statement.executeUpdate(schemaSql);
        }

        if (dataSql == null) {
            dataSql = this.getSqlScriptFromResource("init-data.sql");
            statement.executeUpdate(dataSql);
        }

        if (storedFunctions == null) {
            storedFunctions = this.getSqlScriptFromResource("stored-functions.sql");
            statement.executeUpdate(storedFunctions);
        }

        statement.close();
    }

    @Test
    public void testAddBook_NewBook_ShouldAddBook() throws Exception {
        // given
        Book testBook = this.getTestBook();

        // when
        Connection conn = null;
        CallableStatement callableStatement = null;

        int id = 0;
        try {
            conn = this.connection.getConnection();
            callableStatement = conn.prepareCall("{call add_book(?, ?, ?, ?, ?, ?, ?)}");
            callableStatement.setString(1, testBook.getTitle());
            callableStatement.setInt(2, testBook.getPublishYear().getValue());
            callableStatement.setInt(3, testBook.getGenre().ordinal());
            callableStatement.setString(4, testBook.getDescription());
            callableStatement.setInt(5, testBook.getTotalAmount());
            callableStatement.setString(6, testBook.getAuthor().getFirstname());
            callableStatement.setString(7, testBook.getAuthor().getLastname());

            ResultSet resultSet = callableStatement.executeQuery();

            if (resultSet.next()) {
                id = resultSet.getInt(1);
            }

        } catch (Exception e) {
            throw new RuntimeException("Error: testAddBook_NewBook_ShouldAddBook() method", e);
        } finally {
            if (conn != null) {
                conn.close();
            }

            if (callableStatement != null) {
                callableStatement.close();
            }
        }

        // then
        assertTrue(id > 0);
    }

    @Test
    public void testFindBookById_ExistingBookForSearch_ShouldReturnBook() throws Exception {
//        given
        Book book = new Book();
        book.setId(5);

//        when
        Connection conn = null;
        CallableStatement callableStatement = null;
        int id = 0;
        try {
            conn = this.connection.getConnection();
            callableStatement = conn.prepareCall("{call find_book_by_id(?)}");
            callableStatement.setInt(1, book.getId());

            ResultSet resultSet = callableStatement.executeQuery();

            if (resultSet.next()) {
                id = resultSet.getInt(1);
            }

        } catch (Exception e) {
            throw new RuntimeException("Error: testFindBookById_ExistingBookForSearch_ShouldReturnBook() method", e);
        } finally {
            if (conn != null) {
                conn.close();
            }

            if (callableStatement != null) {
                callableStatement.close();
            }
        }

//        then
        assertNotEquals(0, id);
        assertEquals(id, book.getId().intValue());
    }

    @Test
    public void testUpdateBook_UpdatedExistingBook_shouldUpdateBook() throws Exception {
//          given
        Book existingBook = new Book();
        existingBook.setId(5);

        Book bookForUpdate = this.getTestBook();
        bookForUpdate.setId(existingBook.getId());

        Book updatedBook = new Book();
//          when
        Connection conn = null;
        CallableStatement callableStatement = null;
        try {
            conn = this.connection.getConnection();
            callableStatement = conn.prepareCall("{call update_book(?, ?, ?, ?, ?, ?, ?, ?)}");
            callableStatement.setInt(1, bookForUpdate.getId());
            callableStatement.setString(2, bookForUpdate.getTitle());
            callableStatement.setInt(3, bookForUpdate.getPublishYear().getValue());
            callableStatement.setInt(4, bookForUpdate.getGenre().ordinal());
            callableStatement.setString(5, bookForUpdate.getDescription());
            callableStatement.setInt(6, bookForUpdate.getTotalAmount());
            callableStatement.setString(7, bookForUpdate.getAuthor().getFirstname());
            callableStatement.setString(8, bookForUpdate.getAuthor().getLastname());

            ResultSet resultSet = callableStatement.executeQuery();

            this.initBookResultSet(updatedBook, resultSet);

        } catch (Exception e) {
            throw new RuntimeException("Error: testUpdateBook_UpdatedExistingBook_shouldUpdateBook() method.", e);
        } finally {
            if (conn != null) {
                conn.close();
            }

            if (callableStatement != null) {
                callableStatement.close();
            }
        }

//          then
        assertNotNull(updatedBook);
        assertEquals(updatedBook.getTitle(), bookForUpdate.getTitle());
        assertEquals(updatedBook.getAuthor().getFirstname(), bookForUpdate.getAuthor().getFirstname());
        assertEquals(updatedBook.getAuthor().getLastname(), bookForUpdate.getAuthor().getLastname());
        assertEquals(updatedBook.getDescription(), bookForUpdate.getDescription());

    }

    @Test
    public void testRemoveBookById_ExistingBookForDelete_ShouldDeleteBook() throws Exception {
//        given
        Book existingBook = new Book();
        existingBook.setId(1);

        int id = 0;
//        when
        Connection conn = null;
        CallableStatement callableStatement = null;
        try {
            conn = this.connection.getConnection();
            callableStatement = conn.prepareCall("{call delete_book_by_id(?)}");
            callableStatement.setInt(1, existingBook.getId());
            callableStatement.execute();

            callableStatement = conn.prepareCall("{call find_book_by_id(?)}");
            callableStatement.setInt(1, existingBook.getId());

            ResultSet resultSet = callableStatement.executeQuery();

            if (resultSet.next()) {
                id = resultSet.getInt(1);
            }

        } catch (Exception e) {
            throw new RuntimeException("Error: testRemoveBookById_ExistingBookForDelete_ShouldDeleteBook() method", e);
        } finally {
            if (conn != null) {
                conn.close();
            }

            if (callableStatement != null) {
                callableStatement.close();
            }
        }

//        then
        assertEquals(0, id);
    }


    private void initBookResultSet(Book book, ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            book.setId(resultSet.getInt(1));
            book.setTitle(resultSet.getString(2));
            Author author = new Author();
            author.setId(resultSet.getInt(3));
            author.setFirstname(resultSet.getString(4));
            author.setLastname(resultSet.getString(5));
            book.setAuthor(author);
            book.setPublishYear(Year.of(resultSet.getInt(6)));
            Genre[] values = Genre.values();
            book.setGenre(values[resultSet.getInt(7)]);
            book.setDescription(resultSet.getString(8));
            book.setTotalAmount(resultSet.getInt(9));
        }
    }
}
