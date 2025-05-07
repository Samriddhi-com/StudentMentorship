<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<html>
<head>
    <title>Login | Student Mentorship Portal</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            max-width: 400px;
            margin: 100px auto;
            padding: 2rem;
            border: 1px solid #ccc;
            border-radius: 8px;
            background-color: #f9f9f9;
        }
        input[type=text], input[type=email] {
            width: 100%;
            padding: 0.5rem;
            margin: 0.5rem 0;
        }
        button {
            padding: 0.5rem 1rem;
            background-color: #007bff;
            border: none;
            color: white;
            cursor: pointer;
        }
        button:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
<h2>🎓 Login to Student Mentorship Portal</h2>

<form method="post" action="login">
    <label for="studentId">Student ID:</label>
    <input type="text" name="studentId" id="studentId" required />

    <label for="studentName">Name:</label>
    <input type="text" name="studentName" id="studentName" required />

    <button type="submit">Login</button>
</form>
</body>
</html>
