package com.example.studentmentorship.dao.impl;

import com.example.studentmentorship.dao.MentorshipRequestDAO;
import com.example.studentmentorship.db.DbUtil;
import com.example.studentmentorship.model.MentorshipRequest;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MentorshipRequestDAOImpl implements MentorshipRequestDAO {

    @Override
    public MentorshipRequest findById(int id) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM mentorship_requests WHERE id = ?");
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return extractRequest(rs);
            }
        } catch (SQLException e) {
            System.err.println("Error finding mentorship request: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<MentorshipRequest> findAll() {
        List<MentorshipRequest> list = new ArrayList<>();
        try (Connection conn = DbUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM mentorship_requests")) {

            while (rs.next()) {
                list.add(extractRequest(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error fetching mentorship requests: " + e.getMessage());
        }
        return list;
    }

    @Override
    public List<MentorshipRequest> findByStudentId(int studentId) {
        List<MentorshipRequest> list = new ArrayList<>();
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(
                    "SELECT * FROM mentorship_requests WHERE student_id = ?");
            stmt.setInt(1, studentId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(extractRequest(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error fetching requests by student ID: " + e.getMessage());
        }
        return list;
    }

    @Override
    public List<MentorshipRequest> findByMentorId(int mentorId) {
        List<MentorshipRequest> list = new ArrayList<>();
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(
                    "SELECT * FROM mentorship_requests WHERE mentor_id = ?");
            stmt.setInt(1, mentorId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(extractRequest(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error fetching requests by mentor ID: " + e.getMessage());
        }
        return list;
    }

    @Override
    public boolean save(MentorshipRequest request) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement check = conn.prepareStatement("SELECT COUNT(*) FROM mentorship_requests WHERE id = ?");
            check.setInt(1, request.getId());
            ResultSet rs = check.executeQuery();
            rs.next();
            boolean exists = rs.getInt(1) > 0;

            PreparedStatement stmt;
            if (exists) {
                stmt = conn.prepareStatement(
                        "UPDATE mentorship_requests SET student_id=?, mentor_id=?, subject_id=?, status=? WHERE id=?");
                stmt.setInt(1, request.getStudentId());
                stmt.setObject(2, request.getMentorId());
                stmt.setInt(3, request.getSubjectId());
                stmt.setString(4, request.getStatus());
                stmt.setInt(5, request.getId());
            } else {
                stmt = conn.prepareStatement(
                        "INSERT INTO mentorship_requests (student_id, mentor_id, subject_id, status) VALUES (?, ?, ?, ?)");
                stmt.setInt(1, request.getStudentId());
                stmt.setObject(2, request.getMentorId());
                stmt.setInt(3, request.getSubjectId());
                stmt.setString(4, request.getStatus());
            }

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error saving mentorship request: " + e.getMessage());
            return false;
        }
    }
    @Override
    public boolean delete(int requestId) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM mentorship_requests WHERE id = ?");
            stmt.setInt(1, requestId);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Helper method
    private MentorshipRequest extractRequest(ResultSet rs) throws SQLException {
        return new MentorshipRequest(
                rs.getInt("id"),
                rs.getInt("student_id"),
                rs.getObject("mentor_id") != null ? rs.getInt("mentor_id") : null,
                rs.getInt("subject_id"),
                rs.getString("status")
        );
    }
}