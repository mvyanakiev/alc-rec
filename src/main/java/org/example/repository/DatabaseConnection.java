package org.example.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {


    // TODO Hide it!

    private static final String URL = "jdbc:postgresql://localhost:5432/alcrecdb";
    private static final String USER = "postgres";
    private static final String PASSWORD = "1";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to the database", e);
        }
    }
}
