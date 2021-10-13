package servlets;

import acccounts.AccountService;
import acccounts.UserProfile;
import acccounts.Verification;
import dbService.DBService;

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
        String sessionId = req.getSession().getId();
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        if (Verification.loginOrPassIsEmpty(login, password, resp))
            return;
        /*if (login.equals("") || password.equals("")) {
            resp.sendRedirect("/login");
            return;
        }*/
        UserProfile userProfile = new UserProfile(login, password, email);
        DBService.addUser(userProfile);
        AccountService.addSession(sessionId, userProfile);
        resp.sendRedirect("/files");
    }
}
