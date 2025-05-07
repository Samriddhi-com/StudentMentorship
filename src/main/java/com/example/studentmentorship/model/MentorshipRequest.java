package com.example.studentmentorship.model;

public class MentorshipRequest {
    private int id;
    private int studentId;
    private Integer mentorId; // Nullable
    private int subjectId;
    private String status; // PENDING, ACCEPTED, REJECTED, COMPLETED

    // For display
    private String subjectName;
    private String mentorName;

    public MentorshipRequest() {}

    public MentorshipRequest(int id, int studentId, Integer mentorId, int subjectId, String status) {
        this.id = id;
        this.studentId = studentId;
        this.mentorId = mentorId;
        this.subjectId = subjectId;
        this.status = status;
    }

    // Getters and setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getStudentId() {
        return studentId;
    }
    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public Integer getMentorId() {
        return mentorId;
    }
    public void setMentorId(Integer mentorId) {
        this.mentorId = mentorId;
    }

    public int getSubjectId() {
        return subjectId;
    }
    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public String getSubjectName() {
        return subjectName;
    }
    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getMentorName() {
        return mentorName;
    }
    public void setMentorName(String mentorName) {
        this.mentorName = mentorName;
    }

    public void setStudentName(String name) {
    }
}
