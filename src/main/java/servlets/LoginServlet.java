package servlets;

import acccounts.AccountService;
import acccounts.UserProfile;
import acccounts.Verification;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (Verification.SessionIsExist(req.getSession().getId())) {
            resp.sendRedirect("/files");
            return;
        }

        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sessionId = req.getSession().getId();
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        UserProfile user = AccountService.getUserByLogin(login);
        if (user == null || !password.equals(user.getPassword())) {
            resp.sendRedirect("/login?message=incorrect");
            return;
        }
        AccountService.addSession(sessionId, user);
        resp.sendRedirect("/files");
    }
}
