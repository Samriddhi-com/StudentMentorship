package com.example.studentmentorship.dao.impl;

import com.example.studentmentorship.dao.SubjectDAO;
import com.example.studentmentorship.db.DbUtil;
import com.example.studentmentorship.model.Subject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SubjectDAOImpl implements SubjectDAO {

    @Override
    public Subject findById(int id) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM subjects WHERE id = ?");
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Subject(id, rs.getString("name"));
            }
        } catch (SQLException e) {
            System.err.println("Error finding subject: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Subject> findAll() {
        List<Subject> subjects = new ArrayList<>();
        try (Connection conn = DbUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM subjects")) {

            while (rs.next()) {
                subjects.add(new Subject(rs.getInt("id"), rs.getString("name")));
            }
        } catch (SQLException e) {
            System.err.println("Error fetching all subjects: " + e.getMessage());
        }
        return subjects;
    }

    @Override
    public boolean save(Subject subject) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement check = conn.prepareStatement("SELECT COUNT(*) FROM subjects WHERE id = ?");
            check.setInt(1, subject.getId());
            ResultSet rs = check.executeQuery();
            rs.next();
            int count = rs.getInt(1);

            PreparedStatement stmt;
            if (count > 0) {
                stmt = conn.prepareStatement("UPDATE subjects SET name = ? WHERE id = ?");
                stmt.setString(1, subject.getName());
                stmt.setInt(2, subject.getId());
            } else {
                stmt = conn.prepareStatement("INSERT INTO subjects (name) VALUES (?)");
                stmt.setString(1, subject.getName());
            }

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error saving subject: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(int id) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM subjects WHERE id = ?");
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error deleting subject: " + e.getMessage());
            return false;
        }
    }
}
