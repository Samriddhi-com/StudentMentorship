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

@WebServlet("/mentor")
public class MentorDashboardServlet extends HttpServlet {

    private MentorshipRequestDAO requestDAO = new MentorshipRequestDAOImpl();
    private SubjectDAO subjectDAO = new SubjectDAOImpl();
    private UserDAO userDAO = new UserDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Integer mentorId = (Integer) session.getAttribute("mentorId");

        if (mentorId == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        List<MentorshipRequest> requests = requestDAO.findByMentorId(mentorId);
        List<User> students = userDAO.findAllByRole("STUDENT");
        List<Subject> subjects = subjectDAO.findAll();

        for (MentorshipRequest r : requests) {
            for (User s : students) {
                if (s.getId() == r.getStudentId()) {
                    r.setStudentName(s.getName());
                    break;
                }
            }
            for (Subject sub : subjects) {
                if (sub.getId() == r.getSubjectId()) {
                    r.setSubjectName(sub.getName());
                    break;
                }
            }
        }

        request.setAttribute("requests", requests);
        request.getRequestDispatcher("mentor_dashboard.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int requestId = Integer.parseInt(request.getParameter("requestId"));
        String action = request.getParameter("action");

        String newStatus = switch (action) {
            case "approve" -> "APPROVED";
            case "reject" -> "REJECTED";
            default -> "PENDING";
        };

        requestDAO.updateStatus(requestId, newStatus);
        response.sendRedirect("mentor");
    }
}
