package com.mycompany.login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {

    private static final String URL = "jdbc:sqlite:chatapp.db";

    // Connect to the database
    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    // Create the users table
    public static void createTables() {

        String sql = """
            CREATE TABLE IF NOT EXISTS users (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                username TEXT NOT NULL UNIQUE,
                password TEXT NOT NULL,
                cellphone TEXT NOT NULL
            )
            """;

        try (Connection connection = connect();
             Statement statement = connection.createStatement()) {

            statement.execute(sql);

            System.out.println("Users table ready.");

        } catch (SQLException e) {

            System.out.println("Error creating table: " + e.getMessage());
        }
    }
}