<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Login | Student Mentorship Portal</title>
    <style>
        body {
            font-family: 'Segoe UI', sans-serif;
            background: linear-gradient(135deg, #f8f9fa, #e3f2fd);
            height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
        }

        .login-box {
            background: white;
            padding: 2rem;
            border-radius: 12px;
            box-shadow: 0 8px 16px rgba(0,0,0,0.1);
            width: 100%;
            max-width: 400px;
        }

        h2 {
            text-align: center;
            color: #343a40;
            margin-bottom: 1.5rem;
        }

        label {
            font-weight: 600;
            color: #495057;
        }

        input, select {
            width: 100%;
            padding: 0.6rem;
            margin: 0.4rem 0 1rem;
            border: 1px solid #ced4da;
            border-radius: 6px;
            font-size: 16px;
            background-color: #f1f3f5;
        }

        button {
            width: 100%;
            background: linear-gradient(45deg, #007bff, #3399ff);
            color: white;
            padding: 0.7rem;
            border: none;
            border-radius: 6px;
            font-size: 16px;
            font-weight: bold;
            transition: background 0.3s ease;
            cursor: pointer;
        }

        button:hover {
            background: linear-gradient(45deg, #0056b3, #0069d9);
        }

        .error {
            color: #dc3545;
            background-color: #f8d7da;
            padding: 0.75rem;
            border-radius: 6px;
            text-align: center;
            margin-bottom: 1rem;
        }
    </style>
</head>
<body>
<div class="login-box">
    <h2>🎓 Login to Mentorship Portal</h2>

    <form method="post" action="login">
        <label for="userId">User ID:</label>
        <input type="text" name="userId" id="userId" required />

        <label for="userName">Name:</label>
        <input type="text" name="userName" id="userName" required />

        <label for="role">Role:</label>
        <select name="role" id="role">
            <option value="STUDENT">Student</option>
            <option value="MENTOR">Mentor</option>
        </select>

        <button type="submit">Login</button>
    </form>

    <c:if test="${not empty error}">
        <div class="error">${error}</div>
    </c:if>
</div>
</body>
</html>
