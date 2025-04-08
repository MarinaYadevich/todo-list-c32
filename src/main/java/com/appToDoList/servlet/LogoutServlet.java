package com.appToDoList.servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * Servlet that handles user logout.
 *
 * URL: /logout
 *
 * When called, invalidates the current session and redirects to the login page.
 */

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String username = (String) req.getSession().getAttribute("username");
        req.getSession().invalidate();
        System.out.println("User logged out: " + username);
        resp.sendRedirect(req.getContextPath() + "/login");
    }
}
