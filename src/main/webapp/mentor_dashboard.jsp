<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
  <title>Mentor Dashboard</title>
  <style>
    body {
      font-family: sans-serif;
      padding: 20px;
      background-color: #f5f7fa;
    }
    h1 {
      color: #2c3e50;
    }
    table {
      width: 100%;
      border-collapse: collapse;
      margin-top: 1rem;
    }
    th, td {
      padding: 12px;
      border: 1px solid #ccc;
      background-color: #fff;
    }
    th {
      background-color: #e9ecef;
      text-align: left;
    }
    .btn {
      padding: 6px 12px;
      border: none;
      border-radius: 4px;
      cursor: pointer;
    }
    .approve {
      background-color: #28a745;
      color: white;
    }
    .reject {
      background-color: #dc3545;
      color: white;
    }
  </style>
</head>
<body>

<h1>🧑‍🏫 Mentor Dashboard</h1>

<c:if test="${empty requests}">
  <p>No mentorship requests assigned to you.</p>
</c:if>

<c:if test="${not empty requests}">
  <table>
    <tr>
      <th>Request ID</th>
      <th>Student</th>
      <th>Subject</th>
      <th>Status</th>
      <th>Action</th>
    </tr>
    <c:forEach var="r" items="${requests}">
      <tr>
        <td>${r.id}</td>
        <td>${r.studentName}</td>
        <td>${r.subjectName}</td>
        <td>${r.status}</td>
        <td>
          <c:if test="${r.status == 'PENDING'}">
            <form method="post" action="mentor" style="display:inline;">
              <input type="hidden" name="requestId" value="${r.id}" />
              <input type="hidden" name="action" value="approve" />
              <button class="btn approve" type="submit">✅ Approve</button>
            </form>
            <form method="post" action="mentor" style="display:inline;">
              <input type="hidden" name="requestId" value="${r.id}" />
              <input type="hidden" name="action" value="reject" />
              <button class="btn reject" type="submit">❌ Reject</button>
            </form>
          </c:if>
          <c:if test="${r.status != 'PENDING'}">
            No actions available
          </c:if>
        </td>
      </tr>
    </c:forEach>
  </table>
</c:if>

</body>
</html>

