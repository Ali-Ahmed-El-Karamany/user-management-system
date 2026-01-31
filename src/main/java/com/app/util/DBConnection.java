package com.app.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL =
            "jdbc:sqlserver://localhost:1600;database=user_management_db;encrypt=true;trustServerCertificate=true;";
    private static final String USER = "Aly";
    private static final String PASSWORD = "2304";


    private static Connection connection;
    private DBConnection(){
        // Prevent object instantiation
    }

    public static Connection getConnection() throws SQLException {

        try {
            if(connection == null || connection.isClosed()) {
                // Load and register the database driver
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

                // Establish the connection with the database
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            }
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver not found", e);
        }
        return connection;
    }
}
