package com.example.studentmentorship.controller;

import com.example.studentmentorship.dao.MentorshipRequestDAO;
import com.example.studentmentorship.dao.SubjectDAO;
import com.example.studentmentorship.dao.UserDAO;
import com.example.studentmentorship.dao.impl.MentorshipRequestDAOImpl;
import com.example.studentmentorship.dao.impl.SubjectDAOImpl;


import com.example.studentmentorship.dao.impl.UserDAOImpl;
import com.example.studentmentorship.model.MentorshipRequest;
import com.example.studentmentorship.model.Subject;
import com.example.studentmentorship.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;
@WebServlet("/student")
public class StudentDashboardServlet extends HttpServlet {

    private MentorshipRequestDAO requestDAO = new MentorshipRequestDAOImpl();
    private SubjectDAO subjectDAO = new SubjectDAOImpl();
    private UserDAO userDAO = new UserDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Integer studentId = (Integer) session.getAttribute("studentId");

        if (studentId == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        // Fetch all necessary data
        List<MentorshipRequest> requests = requestDAO.findByStudentId(studentId);
        List<Subject> subjects = subjectDAO.findAll();
        List<User> mentors = userDAO.findAllByRole("MENTOR");

        // Attach subject & mentor names to each request
        for (MentorshipRequest r : requests) {
            for (Subject s : subjects) {
                if (s.getId() == r.getSubjectId()) {
                    r.setSubjectName(s.getName());
                    break;
                }
            }
            for (User m : mentors) {
                if (m.getId() == r.getMentorId()) {
                    r.setMentorName(m.getName());
                    break;
                }
            }
        }

        // Set attributes for JSP
        request.setAttribute("requests", requests);
        request.setAttribute("subjects", subjects);

        // Forward to student dashboard
        request.getRequestDispatcher("student_dashboard.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Integer studentId = (Integer) session.getAttribute("studentId");

        if (studentId == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        try {
            int subjectId = Integer.parseInt(request.getParameter("subjectId"));
            MentorshipRequest newRequest = new MentorshipRequest(0, studentId, null, subjectId, "PENDING");
            requestDAO.save(newRequest);
            session.setAttribute("message", "Mentorship request submitted!");
        } catch (NumberFormatException e) {
            session.setAttribute("error", "Invalid subject ID.");
        }

        response.sendRedirect(request.getContextPath() + "/student");
    }
}