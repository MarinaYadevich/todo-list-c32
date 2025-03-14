<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>To-Do List</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="../css/to-do.css" rel="stylesheet">
</head>

<body id="todo-list-body" class="d-flex align-items-center justify-content-center">

<div id="header-buttons" class="d-flex gap-3">
    <form action="about-me" method="GET">
        <button type="submit" id="button-about-me" class="btn btn-primary">About me</button>
    </form>
    <form action="logout" method="POST">
        <button type="submit" id="button-logout" class="btn btn-danger">Logout</button>
    </form>
</div>

<div id="todo-container" class="container text-center">
    <h1>Hello, <%= request.getSession().getAttribute("username") %> !</h1>

    <form action="todo" method="POST" class="d-flex gap-2 mt-3">
        <input type="text" id="input-task" name="task" class="form-control" placeholder="Enter the task" required>
        <input type="submit" id="task-btn" value="Add task" class="btn btn-success">
    </form>

    <h2 class="mt-4">Task list:</h2>

    <c:if test="${tasks == null}">
        <h4 class="text-muted">There are no active tasks!</h4>
    </c:if>

    <ol class="list-group mt-3">
        <c:forEach var="task" items="${tasks}">
            <li class="list-group-item d-flex justify-content-between align-items-center">
                ${task}
                <button class="delete-btn">✖</button>
            </li>
        </c:forEach>
    </ol>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
