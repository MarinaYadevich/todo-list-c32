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
 * Servlet implements registration of new users.
 *
 * URL: /registration.
 */
@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        String newUsername = req.getParameter("username");
        String newPassword = req.getParameter("password");
        String confirmPassword = req.getParameter("confirm-password");

        if (newUsername == null || newUsername.trim().isEmpty()
                || newPassword == null || newPassword.trim().isEmpty()
                || confirmPassword == null || confirmPassword.trim().isEmpty() ) {
            req.setAttribute("errorMessage", "Username or password/confirm password cannot be empty");
            req.getRequestDispatcher("/registration.html").forward(req, resp);
            return;
        }

       if (!newPassword.equals(confirmPassword)) {
           req.setAttribute("errorMessage", "New password and confirm password do not match");
           req.getRequestDispatcher("/WEB-INF/page/error.jsp").forward(req, resp);
       }

        boolean isUserAdded = UserRepository.addUser(newUsername, newPassword);

        System.out.println("isUserAdded = " + isUserAdded);

        if (isUserAdded) {
            resp.getWriter().println("Registration successful!");
            HttpSession session = req.getSession();
            session.setAttribute("username", newUsername);
            req.getRequestDispatcher("/WEB-INF/page/todo-list.jsp").forward(req, resp);
        } else {
            req.setAttribute("errorMessage", "User already exists");
            req.getRequestDispatcher("/login.html").forward(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/registration.html").forward(req, resp);
    }
}