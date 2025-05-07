package com.example.studentmentorship.dao;

import com.example.studentmentorship.model.User;

import java.util.List;

public interface UserDAO {
    User findById(int id);
    List<User> findAll();
    List<User> findByRole(String role); // e.g., STUDENT, MENTOR
    boolean save(User user);
    boolean delete(int id);

    List<User> findAllMentors();
    List<User> findAllByRole(String role);
}