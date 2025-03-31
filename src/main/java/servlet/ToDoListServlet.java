package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import repository.TaskRepository;

import java.io.IOException;
import java.util.Set;

@WebServlet("/todo")
public class ToDoListServlet extends HttpServlet {
    private static final TaskRepository taskRepository = TaskRepository.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = (String) req.getSession().getAttribute("username");

        if (username == null) {
            resp.sendRedirect("login.jsp"); // Если нет пользователя, отправляем на логин
            return;
        }

        Set<String> tasks = taskRepository.getTasksByUsername(username);
        req.setAttribute("tasks", tasks); // Передаём список в JSP

        System.out.println("Загружаемые задачи для " + username + ": " + tasks);
        req.getRequestDispatcher("/page/todo-list.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String username = (String) session.getAttribute("username");

        if (username == null) {
            resp.sendRedirect("login.jsp");
            return;
        }

        String task = req.getParameter("task");
        String deletedTask = req.getParameter("deletedTask");

        if (task != null && !task.trim().isEmpty()) {
            taskRepository.addTask(username, task);
        }

        if (deletedTask != null) {
            taskRepository.removeTask(username, deletedTask);
        }

        Set<String> tasks = taskRepository.getTasksByUsername(username);
        req.setAttribute("tasks", tasks);

        req.getRequestDispatcher("/page/todo-list.jsp").forward(req, resp);
    }
}