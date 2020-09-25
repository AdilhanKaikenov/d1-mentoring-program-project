package com.epam.mentoring.jdbc.intro.task2;

import com.epam.mentoring.jdbc.intro.task2.dao.AuthorDao;
import com.epam.mentoring.jdbc.intro.task2.dao.BookDao;
import com.epam.mentoring.jdbc.intro.task2.dao.jdbc.JdbcBookDao;
import com.epam.mentoring.jdbc.intro.task2.dao.jdbc.JdbcDaoFactory;
import com.epam.mentoring.jdbc.intro.task2.model.Author;
import com.epam.mentoring.jdbc.intro.task2.model.Book;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.database.IDatabaseConnection;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.sql.Connection;
import java.sql.Statement;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class BookSqlStatementTest extends SqlTest {

    private IDatabaseConnection connection = null;
    private JdbcDatabaseTester jdbcDatabaseTester = null;

    private static String schemaSql = null;
    private static String dataSql = null;

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

        statement.close();
    }

    @Test
    public void testAddBook_NewBook_ShouldAddBook() throws Exception {
        // given
        final Book testBook = this.getTestBook();

        Book addedBook = null;
        // when
        JdbcDaoFactory jdbcDaoFactory = null;
        try {
            jdbcDaoFactory = new JdbcDaoFactory(this.connection.getConnection());
            BookDao bookDao = jdbcDaoFactory.getBookDao();
            AuthorDao authorDao = jdbcDaoFactory.getAuthorDao();

            jdbcDaoFactory.beginTransaction(); //transaction block start

            Author author = authorDao.read(testBook.getAuthor());
            if (author == null) {
                author = authorDao.create(testBook.getAuthor());
            }

            testBook.setAuthor(author);

            addedBook = bookDao.create(testBook);

            jdbcDaoFactory.endTransaction(); //transaction block end
        } catch (Exception e) {
            this.rollbackTransaction(jdbcDaoFactory);
            throw new RuntimeException("Error: testAddBook_NewBook_ShouldAddBook() method", e);
        } finally {
            this.closeJdbcDaoFactory(jdbcDaoFactory); // close connection
        }

        // then
        assertNotNull(addedBook);
        assertNotNull(addedBook.getId());
        assertEquals(addedBook.getTitle(), testBook.getTitle());
        assertEquals(addedBook.getAuthor(), testBook.getAuthor());
    }

    @Test
    public void testFindBookById_ExistingBookForSearch_ShouldReturnBook() {
//        given
        Book book = new Book();
        book.setId(5);

        Book result = null;
//        when
        try (JdbcDaoFactory jdbcDaoFactory = new JdbcDaoFactory(this.connection.getConnection())) {

            BookDao bookDao = jdbcDaoFactory.getBookDao();
            result = bookDao.read(book.getId());

        } catch (Exception e) {
            throw new RuntimeException("Error: testFindBookById_ExistingBookForSearch_ShouldReturnBook() method", e);
        }

//        then
        assertNotNull(result);
        assertNotNull(result.getId());
        assertEquals(result.getId(), book.getId());
    }

    @Test
    public void testUpdateBook_UpdatedExistingBook_shouldUpdateBook() throws Exception {
//          given
        Book existingBook = new Book();
        existingBook.setId(5);

        Book bookForUpdate = this.getTestBook();
        bookForUpdate.setId(existingBook.getId());

//          when
        JdbcDaoFactory jdbcDaoFactory = null;
        try {
            jdbcDaoFactory = new JdbcDaoFactory(this.connection.getConnection());
            BookDao bookDao = jdbcDaoFactory.getBookDao();
            AuthorDao authorDao = jdbcDaoFactory.getAuthorDao();

            existingBook = bookDao.read(existingBook.getId());

            jdbcDaoFactory.beginTransaction(); //transaction block start

            Author author = authorDao.read(bookForUpdate.getAuthor());
            if (author == null) {
                author = authorDao.create(bookForUpdate.getAuthor());
            }

            bookForUpdate.setAuthor(author);

            bookDao.update(bookForUpdate);

            jdbcDaoFactory.endTransaction(); //transaction block end

        } catch (Exception e) {
            this.rollbackTransaction(jdbcDaoFactory);
            throw new RuntimeException("Error: testUpdateBook_UpdatedExistingBook_shouldUpdateBook() method.", e);
        } finally {
            this.closeJdbcDaoFactory(jdbcDaoFactory); // close connection
        }

//          then
        Connection connection = this.jdbcDatabaseTester.getConnection().getConnection();
        BookDao bookDao = new JdbcBookDao(connection);

        Book updatedBook = bookDao.read(existingBook.getId());

        connection.close(); // close connection

        assertNotNull(updatedBook);
        assertNotEquals(updatedBook.getTitle(), existingBook.getTitle());
        assertNotEquals(updatedBook.getDescription(), existingBook.getDescription());
        assertEquals(updatedBook.getTitle(), bookForUpdate.getTitle());
        assertEquals(updatedBook.getAuthor().getFirstname(), bookForUpdate.getAuthor().getFirstname());
        assertEquals(updatedBook.getAuthor().getLastname(), bookForUpdate.getAuthor().getLastname());
        assertEquals(updatedBook.getDescription(), bookForUpdate.getDescription());
    }

    @Test
    public void testRemoveBookById_ExistingBookForDelete_ShouldDeleteBook() throws Exception {
//          given
        Book existingBook = new Book();
        existingBook.setId(6);

//          when
        JdbcDaoFactory jdbcDaoFactory = null;
        try {
            jdbcDaoFactory = new JdbcDaoFactory(this.connection.getConnection());
            BookDao bookDao = jdbcDaoFactory.getBookDao();
            AuthorDao authorDao = jdbcDaoFactory.getAuthorDao();

            jdbcDaoFactory.beginTransaction(); //transaction block start

            existingBook = bookDao.read(existingBook.getId());
            bookDao.delete(existingBook);

            Author author = existingBook.getAuthor();
            int booksNumByAuthor = bookDao.countBookByAuthor(author);

            if (booksNumByAuthor == 0) {
                authorDao.delete(author);
            }

            jdbcDaoFactory.endTransaction(); //transaction block end

        } catch (Exception e) {
            this.rollbackTransaction(jdbcDaoFactory);
            throw new RuntimeException("Error: testRemoveBookById_ExistingBookForDelete_ShouldDeleteBook() method.", e);
        } finally {
            this.closeJdbcDaoFactory(jdbcDaoFactory); // close connection
        }

//        then
        Connection connection = this.jdbcDatabaseTester.getConnection().getConnection();
        BookDao bookDao = new JdbcBookDao(connection);

        Book removedBook = bookDao.read(existingBook.getId());

        connection.close(); // close connection

        assertNull(removedBook);
    }

    private void rollbackTransaction(JdbcDaoFactory jdbcDaoFactory) throws Exception {
        if (jdbcDaoFactory != null) {
            try {
                jdbcDaoFactory.rollbackTransaction(); //Error, rollback
            } catch (Exception ex) {
                throw new RuntimeException("Failed to rollback transaction", ex);
            }
        }
    }

    private void closeJdbcDaoFactory(JdbcDaoFactory jdbcDaoFactory) throws Exception {
        if (jdbcDaoFactory != null) {
            try {
                jdbcDaoFactory.close();
            } catch (Exception e) {
                throw new RuntimeException("Failed to close jdbcDaoFactory", e);
            }
        }
    }
}
