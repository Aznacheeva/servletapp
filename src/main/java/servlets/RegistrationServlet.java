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

@WebServlet(urlPatterns = {"/registration"})
public class RegistrationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        if (Verification.SessionIsExist(req.getSession().getId())) {
            resp.sendRedirect("/files");
            return;
        }
        req.getRequestDispatcher("registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String sessionId = req.getSession().toString();
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        UserProfile userProfile = new UserProfile(login, password, email);
        AccountService.addNewUser(userProfile);
        AccountService.addSession(sessionId.split("[:@]")[1], userProfile);
        resp.sendRedirect("/files");
    }
}
