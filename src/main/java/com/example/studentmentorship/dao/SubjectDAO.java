package com.example.studentmentorship.dao;

import com.example.studentmentorship.model.Subject;

import java.util.List;

public interface SubjectDAO {
    Subject findById(int id);
    List<Subject> findAll();
    boolean save(Subject subject);
    boolean delete(int id);
}
