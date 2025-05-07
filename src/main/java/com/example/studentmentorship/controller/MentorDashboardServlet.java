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

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Integer mentorId = (Integer) session.getAttribute("mentorId");

        if (mentorId == null) {
            response.sendRedirect("login.jsp"); // If not logged in
            return;
        }

        List<MentorshipRequest> requests = requestDAO.findByMentorId(mentorId);
        request.setAttribute("requests", requests);

        request.getRequestDispatcher("mentor_dashboard.jsp").forward(request, response);
    }
}
