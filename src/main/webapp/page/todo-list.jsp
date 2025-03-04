<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
<link href="../css/to-do.css" rel="stylesheet">

    <style>
        <%@include file="/css/to-do.css"%>
    </style>
</head>

<body>

<h1> Hello, <%=request.getSession().getAttribute("username")%> !</h1>

<div class="header-buttons">
    <form action="about-me" method="GET">
        <button type="submit" id="button-about-me">About me page</button>
    </form>
    <form action="logout" method="POST">
        <button type="submit" id="button-logout">Logout</button>
    </form>
</div>

<form action="todo" method="POST">
    <input type="text" id="input-task" name="task" placeholder="Enter the task" required>
    <input type="submit" id="task-btn" value="Add task">
</form>

<h2>Task list:</h2>

<c:if test="${tasks == null}">
    <h4>There are no active tasks!</h4>
</c:if>

<ol>
    <c:forEach var="task" items="${tasks}">
        <li>${task}</li>
    </c:forEach>
</ol>
</body>
</html>
