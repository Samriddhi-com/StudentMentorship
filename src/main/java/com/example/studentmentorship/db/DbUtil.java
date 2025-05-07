package com.example.studentmentorship.db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtil {
    // JDBC URL for embedded H2 (creates/uses file at user home directory)
    private static final String JDBC_URL = "jdbc:h2:~/mentorshipportal;DB_CLOSE_DELAY=-1";
    private static final String USER = "sa";
    private static final String PASSWORD = "";

    // Load H2 JDBC driver
    static {
        try {
            Class.forName("org.h2.Driver");
            System.out.println(" H2 JDBC driver loaded successfully.");
        } catch (ClassNotFoundException e) {
            System.err.println(" H2 JDBC Driver not found!");
            e.printStackTrace();
        }
    }

    /**
     * Get a database connection
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
    }

    /**
     * Quietly close a connection
     */
    public static void closeQuietly(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                System.err.println(" Error closing DB connection: " + e.getMessage());
            }
        }
    }
}