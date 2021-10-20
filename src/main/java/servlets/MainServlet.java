package servlets;

import acccounts.AccountService;
import acccounts.Verification;
import acccounts.UsersDataSet;
import files.FileDescription;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@WebServlet(urlPatterns = {"/files"})
public class MainServlet extends HttpServlet {
    private static final String homeDirectory = "C:\\filemanager\\";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sessionId = req.getSession().getId();
        UsersDataSet user = AccountService.getUserBySessionId(sessionId);
        if (user == null) {
            resp.sendRedirect("/login");
            return;
        }
        String path = req.getParameter("path");
        File dir;
        if (path == null) {
            dir = new File(homeDirectory + user.getLogin());
            Verification.CheckDir(dir);
        }
        else {
            dir = new File(path);
            if (Verification.DirectoryIsInvisible(user.getLogin(),
                    homeDirectory, dir.getAbsolutePath())) {
                req.getRequestDispatcher("warning.jsp").forward(req, resp);
                return;
            }
        }
        File[] arrFiles = dir.listFiles();
        ArrayList<FileDescription> fileDescriptions = FileDescription.getArray(arrFiles,
                req.getRequestURL().toString());
        req.setAttribute("time", (new SimpleDateFormat("dd.MM.yyyy hh:mm:ss")).format(new Date()));
        req.setAttribute("path", dir.getAbsolutePath());
        req.setAttribute("parent",
                req.getRequestURL().toString() + "?path=" + dir.getParent());
        req.setAttribute("list", fileDescriptions);
        req.getRequestDispatcher("page.jsp").forward(req, resp);
    }
}
