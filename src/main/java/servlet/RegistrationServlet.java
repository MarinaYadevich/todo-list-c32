package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

// This class implements registration of new users.
@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        String newUsername = req.getParameter("username");
        String newPassword = req.getParameter("password");

        if (newUsername == null || newUsername.trim().isEmpty() || newPassword == null || newPassword.trim().isEmpty()) {
            req.setAttribute("error", "Username and password cannot be empty");
            req.getRequestDispatcher("/registration.html").forward(req, resp);
            return;
        }

        boolean isUserAdded = UserRepository.addUser(newUsername, newPassword);

        if (isUserAdded) {
            resp.getWriter().println("Registration successful!");
//            HttpSession session = req.getSession();
//            session.setAttribute("username", newUsername);
//            req.getRequestDispatcher("/page/todo-list.jsp").forward(req, resp);
        } else {
            req.setAttribute("error", "User already exists");
            req.getRequestDispatcher("/registration.html").forward(req, resp);
        }
    }
}
