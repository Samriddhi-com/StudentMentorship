package com.example.studentmentorship.dao;

import com.example.studentmentorship.model.MentorshipRequest;

import java.util.List;

public interface MentorshipRequestDAO {

    MentorshipRequest findById(int id);
    List<MentorshipRequest> findAll();
    List<MentorshipRequest> findByStudentId(int studentId);
    List<MentorshipRequest> findByMentorId(int mentorId);
    boolean save(MentorshipRequest request);
    boolean delete(int id);

    void updateStatus(int requestId, String newStatus);
}