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

        // Simulate student login
        try {
            int studentId = Integer.parseInt(request.getParameter("studentId"));
            String name = request.getParameter("studentName");

            // Set in session
            HttpSession session = request.getSession();
            session.setAttribute("studentId", studentId);
            session.setAttribute("studentName", name);

            response.sendRedirect("student"); // Redirect to student dashboard
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Invalid Student ID format");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}