package com.epam.mentoring.jdbc.intro.task3;

import org.postgresql.Driver;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.Month;

import static java.time.temporal.TemporalAdjusters.lastDayOfMonth;

/**
 * @author Kaikenov Adilhan
 */
public class Task3App {

    private static final String URL = "jdbc:postgresql://localhost:5432/jdbc_intro_task3_social_network";
    private static final String USER = "postgres";
    private static final String PASSWORD = "admin";

    private static final String SCHEMA_SQL_FILE_NAME = "schema.sql";
    private static final String GENERATE_DATA_SQL_FILE_NAME = "generate-data.sql";

    public static void main(String[] args) throws Exception {

        Connection connection = null;
        Statement statement = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            DriverManager.registerDriver(new Driver());
            connection = DriverManager.getConnection(URL, USER, PASSWORD);

            String schemaSql = getSqlScriptFromResource(SCHEMA_SQL_FILE_NAME);
            String createFunctionsSql = getSqlScriptFromResource(GENERATE_DATA_SQL_FILE_NAME);

            statement = connection.createStatement();

//          1. Create simple database with tables:
//          - Users (id, name, surname, birthdate),
//          - Friendships (userid1, userid2, timestamp),
//          - Posts (id, userId, text, timestamp),
//          - Likes (postid, userid, timestamp).
            statement.execute(schemaSql);
//          2. Populate tables with data which are make sense (> 1 000 users, > 70 000 friendships, > 300 000 likes in 2016)*
            statement.execute(createFunctionsSql);

            final int usersAmount = 1000;
            final int friendshipsAmount = 70000;
            final int postsAmount = 500;
            final int likesAmount = 300000;
            generateData(connection, "{call generate_users(?)}", usersAmount);
            generateData(connection, "{call generate_friendships(?)}", friendshipsAmount);
            generateData(connection, "{call generate_posts(?)}", postsAmount);
            generateData(connection, "{call generate_likes(?)}", likesAmount);

//          3. Program should print out all names (only distinct) of users who has more
//          when 100 friends and 100 likes in March 2016.
            final String sql =
                    "SELECT u.id, u.name, u.surname, user_friendships.count AS friendships_count, user_likes.count AS likes_count " +
                    "FROM \"Users\" u " +
                        "INNER JOIN (SELECT userid1 AS user_id, COUNT(*) AS count FROM \"Friendships\" GROUP BY user_id) AS user_friendships ON user_friendships.user_id = u.id " +
                        "INNER JOIN (SELECT userid  AS user_id, COUNT(*) AS count FROM \"Likes\" likes WHERE likes.like_date BETWEEN ? AND ? GROUP BY user_id) AS user_likes ON user_likes.user_id = u.id " +
                    "WHERE user_friendships.count > ? AND user_likes.count > ? " +
                    "ORDER BY u.id;";

            final int year = 2016;
            final int firstDayOfMonth = 1;
            LocalDate initialDate = LocalDate.of(year, Month.MARCH, firstDayOfMonth);
            LocalDate endDate = initialDate.with(lastDayOfMonth());

            Timestamp startTimestamp = Timestamp.valueOf(initialDate.atStartOfDay());
            Timestamp endTimestamp = Timestamp.valueOf(endDate.atStartOfDay());

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDate(1, new Date(startTimestamp.getTime()));
            preparedStatement.setDate(2, new Date(endTimestamp.getTime()));
            preparedStatement.setInt(3, 100);
            preparedStatement.setInt(4, 30);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                System.out.println(MessageFormat.format("{0} - {1} {2} - Friendships = {3}, Likes = {4}",
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getInt(4),
                        resultSet.getInt(5)
                ));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (resultSet != null) {
                resultSet.close();
            }
        }
    }

    private static void generateData(Connection connection, String functionSqlCall, int dataAmount) throws SQLException {
        CallableStatement generateStatement = null;
        try {
            generateStatement = connection.prepareCall(functionSqlCall);
            generateStatement.setInt(1, dataAmount);
            generateStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (generateStatement != null) {
                generateStatement.close();
            }
        }
    }

    private static String getSqlScriptFromResource(String scriptFileName) throws IOException {
        final ClassLoader classLoader = Task3App.class.getClassLoader();
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
