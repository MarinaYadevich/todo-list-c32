package com.appToDoList.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import com.appToDoList.repository.UserRepository;

import java.io.IOException;

/**
 * A servlet that handles user login.
 *
 * URL: /login
 *
 * GET-request: Displays the login page if the user is not logged in.
 * POST-request: checks login/password, saves the user to the session and redirects to /todo.
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    UserRepository userRepository;

    public LoginServlet() {
        this.userRepository = new UserRepository();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        boolean isValidUser = userRepository.isValid(username, password);

        if (isValidUser) {
            HttpSession session = req.getSession();
            session.setAttribute("username", username);
            resp.sendRedirect("/todo");
        } else {
            req.setAttribute("errorMessage", "Invalid username or password");
            req.getRequestDispatcher("/WEB-INF/page/error.jsp").forward(req, resp);
            }
        }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = (String) req.getSession().getAttribute("username");
        if (username == null) {
            req.getRequestDispatcher("/login.html").forward(req, resp);
        } else {
            req.getRequestDispatcher("/todo").forward(req, resp);
        }
    }
}