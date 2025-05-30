<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

    <title>To-Do List</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="../../css/to-do.css" rel="stylesheet">
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

    <c:if test="${tasks == null || tasks.isEmpty()}">
        <h4 class="text-muted">There are no active tasks!</h4>
    </c:if>

    <ul class="list-group mt-3">
        <c:forEach var="task" items="${tasks}">
            <li class="list-group-item d-flex justify-content-between align-items-center">
                    ${task}
                        <button type="button" class="btn btn-primary" data-bs-toggle="modal"
                                data-bs-target="#editTaskModal"
                                data-task="${task}" onclick="openEditModal(this)">
                            Редактировать
                        </button>

                <form action="todo" method="POST">
                    <input type="hidden" name="deletedTask" class="delete-btn" value="${task}">
                    <input type="submit" value="✖" id="delete-btn">
                </form>
            </li>
        </c:forEach>
    </ul>

    <div class="modal fade" id="editTaskModal" tabindex="-1" aria-labelledby="editTaskModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="editTaskModalLabel">Редактировать задачу</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Закрыть"></button>
                </div>
                <div class="modal-body">
                    <form id="editTaskForm" action="edit-task" method="post">
                        <input type="hidden" name="taskId" id="taskId">
                        <div class="mb-3">
                            <label for="taskName" class="form-label">Название задачи</label>
                            <input type="text" class="form-control" name="taskName" id="taskName">
                        </div>
                        <button type="submit" class="btn btn-success">Сохранить</button>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <script>
        function openEditModal(button) {
            var taskName = button.getAttribute("data-task");
            console.log("Редактируемая задача:", taskName);
            document.getElementById("taskName").value = taskName;
        }
    </script>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<script>
    function openEditModal(taskId, taskName) {
        document.getElementById("taskId").value = taskId;
        document.getElementById("taskName").value = taskName;
    }
</script>
</body>
</html>
