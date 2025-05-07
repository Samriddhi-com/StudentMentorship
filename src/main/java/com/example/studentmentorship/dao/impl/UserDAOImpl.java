package com.example.studentmentorship.dao.impl;

import com.example.studentmentorship.dao.UserDAO;
import com.example.studentmentorship.db.DbUtil;
import com.example.studentmentorship.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {

    @Override
    public User findById(int id) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users WHERE id = ?");
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new User(id, rs.getString("name"), rs.getString("email"), rs.getString("role"));
            }
        } catch (SQLException e) {
            System.err.println("Error finding user: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        try (Connection conn = DbUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM users")) {

            while (rs.next()) {
                users.add(new User(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("role")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error finding all users: " + e.getMessage());
        }
        return users;
    }

    @Override
    public List<User> findByRole(String role) {
        List<User> users = new ArrayList<>();
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users WHERE role = ?");
            stmt.setString(1, role);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                users.add(new User(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        role
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error finding users by role: " + e.getMessage());
        }
        return users;
    }

    @Override
    public boolean save(User user) {
        try (Connection conn = DbUtil.getConnection()) {
            // Check if user exists
            PreparedStatement check = conn.prepareStatement("SELECT COUNT(*) FROM users WHERE id = ?");
            check.setInt(1, user.getId());
            ResultSet rs = check.executeQuery();
            rs.next();
            int count = rs.getInt(1);

            PreparedStatement stmt;
            if (count > 0) {
                stmt = conn.prepareStatement("UPDATE users SET name = ?, email = ?, role = ? WHERE id = ?");
                stmt.setString(1, user.getName());
                stmt.setString(2, user.getEmail());
                stmt.setString(3, user.getRole());
                stmt.setInt(4, user.getId());
            } else {
                stmt = conn.prepareStatement("INSERT INTO users (name, email, role) VALUES (?, ?, ?)");
                stmt.setString(1, user.getName());
                stmt.setString(2, user.getEmail());
                stmt.setString(3, user.getRole());
            }

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error saving user: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(int id) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM users WHERE id = ?");
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error deleting user: " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<User> findAllMentors() {
        List<User> mentors = new ArrayList<>();
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users WHERE role = 'MENTOR'");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                mentors.add(new User(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("role")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mentors;
    }
    @Override
    public List<User> findAllByRole(String role) {
        List<User> users = new ArrayList<>();
        try (Connection conn = DbUtil.getConnection()) {
            String sql = "SELECT * FROM users WHERE role = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, role);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                User user = new User(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("role")
                );
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
}