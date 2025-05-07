package com.example.studentmentorship.controller;

import com.example.studentmentorship.dao.MentorshipRequestDAO;
import com.example.studentmentorship.dao.impl.MentorshipRequestDAOImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/cancelRequest")
public class CancelRequestServlet extends HttpServlet {

    private MentorshipRequestDAO requestDAO = new MentorshipRequestDAOImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            int requestId = Integer.parseInt(request.getParameter("requestId"));
            requestDAO.delete(requestId);
            response.sendRedirect("student"); // redirect back to dashboard
        } catch (NumberFormatException e) {
            response.sendRedirect("student?error=Invalid+Request+ID");
        }
    }
}
