<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Student Dashboard</title>
    <style>
        body {
            font-family: 'Segoe UI', sans-serif;
            margin: 30px;
            background-color: #f4f6f8;
            color: #333;
        }

        h1 {
            font-size: 2rem;
            margin-bottom: 10px;
            color: #2c3e50;
        }

        h2 {
            margin-top: 2rem;
            color: #34495e;
        }

        form {
            margin-bottom: 20px;
        }

        label {
            font-weight: bold;
            margin-right: 10px;
        }

        select, button {
            padding: 8px 12px;
            font-size: 14px;
            margin-right: 10px;
        }

        button {
            background-color: #2980b9;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        button:hover {
            background-color: #1c5980;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 10px;
            background-color: white;
            box-shadow: 0 2px 5px rgba(0,0,0,0.1);
        }

        th, td {
            padding: 12px;
            border-bottom: 1px solid #e1e4e8;
            text-align: left;
        }

        th {
            background-color: #f9fafc;
            font-weight: bold;
        }

        .status-pending {
            color: #e67e22;
            font-weight: bold;
        }

        .status-approved {
            color: #27ae60;
            font-weight: bold;
        }

        .status-rejected {
            color: #c0392b;
            font-weight: bold;
        }

        .cancel-button {
            background: none;
            border: none;
            color: #e74c3c;
            font-weight: bold;
            cursor: pointer;
        }

        .cancel-button:hover {
            text-decoration: underline;
        }

    </style>
</head>
<body>
<h1>🎓 Welcome Student</h1>

<h2>Request Mentorship</h2>
<form method="post" action="student">
    <label for="subject">Select Subject:</label>
    <select name="subjectId" id="subject">
        <c:forEach var="s" items="${subjects}">
            <option value="${s.id}">${s.name}</option>
        </c:forEach>
    </select>
    <button type="submit">Request</button>
</form>

<h2>Your Mentorship Requests</h2>
<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Subject</th>
        <th>Status</th>
        <th>Mentor</th>
        <th>Action</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="r" items="${requests}">
        <tr>
            <td>${r.id}</td>
            <td>${r.subjectName}</td>
            <td>
                <span class="status-${r.status.toLowerCase()}">${r.status}</span>
            </td>
            <td>
                <c:choose>
                    <c:when test="${r.mentorName != null}">
                        ${r.mentorName}
                    </c:when>
                    <c:otherwise>Not assigned</c:otherwise>
                </c:choose>
            </td>
            <td>
                <form method="post" action="cancelRequest">
                    <input type="hidden" name="requestId" value="${r.id}" />
                    <button type="submit" class="cancel-button">❌ Cancel</button>
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
