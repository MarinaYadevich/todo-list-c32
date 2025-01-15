package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

// This class implements the login to the application
@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false); // Get an existing session without creating a new one

        String username = null;
        if (session != null) {
            username = (String) session.getAttribute("username");
        }

        if (username == null) {
            req.getRequestDispatcher("/login.html").forward(req, resp);
        } else {
            req.getRequestDispatcher("/page/todo-list.html").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Setting the content type
        resp.setContentType("text/html");

        // We get from the request
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        // User authentication
        boolean isValidUser = UserRepository.isValid(username, password);

        // Preparing an answer
        if (isValidUser) {
            HttpSession session = req.getSession();
            session.setAttribute("username", username);
            req.getRequestDispatcher("/page/todo-list.html").forward(req, resp);
        } else {
            req.setAttribute("error", "Invalid username or password");
            try (PrintWriter out = resp.getWriter()) {
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Settings</title>");
                out.println("<style>");
                out.println("body { font-family: Arial, sans-serif; background-color: #f0f0f0; padding: 20px; text-align: center; }");
                out.println("h1 { color: #e74c3c; }"); // Header color
                out.println("h2 { color: #e74c3c; font-size: 20px; margin-top: 10px; }"); // Subtitle color and size
                out.println(".error-message { background-color: #f8d7da; border: 1px solid #f5c6cb; padding: 20px; border-radius: 8px; color: #721c24; font-size: 18px; max-width: 400px; margin: 0 auto; margin-top: 20px; }");
                out.println("</style>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>ERROR</h1>");
                out.println("<div class='error-message'>");
                out.println("<h2>Invalid username or password</h2>");
                out.println("</div>");
                out.println("</body>");
                out.println("</html>");

               // req.getRequestDispatcher("/login.html").forward(req, resp);
            }
        }
    }
}
