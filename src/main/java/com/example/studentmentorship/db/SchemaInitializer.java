package com.example.studentmentorship.db;


import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class SchemaInitializer {

    public static void initializeSchema() {
        Connection conn = null;
        try {
            conn = DbUtil.getConnection();
            Statement stmt = conn.createStatement();

            // Users table: shared for students, mentors, admins
            stmt.execute("""
               CREATE TABLE IF NOT EXISTS users (
                     id INT AUTO_INCREMENT PRIMARY KEY,
                     name VARCHAR(100) NOT NULL,
                     email VARCHAR(100) NOT NULL UNIQUE,
                     role VARCHAR(20) NOT NULL
                 );
                 
                 INSERT INTO users (name, email, role) VALUES
                 ('Alice Student', 'alice@example.com', 'STUDENT'),
                 ('Bob Student', 'bob@example.com', 'STUDENT'),
                 ('Dr. John Smith', 'john@mentor.com', 'MENTOR'),
                 ('Prof. Emily Brown', 'emily@mentor.com', 'MENTOR');
                 
            """);
            System.out.println("Users table created.");

            // Subjects table
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS subjects (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    name VARCHAR(100) NOT NULL
                )
                INSERT INTO subjects (name) VALUES
                ('Java'),
                ('Web Development'),
                ('Data Structures');
            """);
            System.out.println("Subjects table created.");

            // Mentorship Requests
            stmt.execute("""
               CREATE TABLE IF NOT EXISTS mentorship_requests (
                     id INT AUTO_INCREMENT PRIMARY KEY,
                     student_id INT,
                     mentor_id INT,
                     subject_id INT,
                     status VARCHAR(20),
                     FOREIGN KEY (student_id) REFERENCES users(id),
                     FOREIGN KEY (mentor_id) REFERENCES users(id),
                     FOREIGN KEY (subject_id) REFERENCES subjects(id)
                 );
                 
                 INSERT INTO mentorship_requests (student_id, mentor_id, subject_id, status) VALUES
                 (1, 2, 1, 'APPROVED'),
                 (2, 3, 2, 'PENDIND'),
                 (1, 4, 3, 'REJECTED');
                
            """);
            System.out.println("Mentorship Requests table created.");

            System.out.println("Schema initialized.");

        } catch (SQLException e) {
            System.err.println("Error setting up schema: " + e.getMessage());
            e.printStackTrace();
        } finally {
            DbUtil.closeQuietly(conn);
        }
    }

    public static void main(String[] args) {
        initializeSchema();  // Manual run for local testing
    }
}