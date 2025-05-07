package com.example.studentmentorship.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            int userId = Integer.parseInt(request.getParameter("userId"));
            String name = request.getParameter("userName");
            String role = request.getParameter("role");

            HttpSession session = request.getSession();
            session.setAttribute("userId", userId);
            session.setAttribute("userName", name);
            session.setAttribute("role", role);

            if ("STUDENT".equalsIgnoreCase(role)) {
                session.setAttribute("studentId", userId);
                response.sendRedirect("student");
            } else if ("MENTOR".equalsIgnoreCase(role)) {
                session.setAttribute("mentorId", userId);
                response.sendRedirect("mentor");
            } else {
                throw new IllegalArgumentException("Invalid role");
            }

        } catch (NumberFormatException e) {
            request.setAttribute("error", "Invalid ID format");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}
