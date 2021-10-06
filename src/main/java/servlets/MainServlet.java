package servlets;

import acccounts.AccountService;
import acccounts.UserProfile;
import acccounts.Verification;
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

@WebServlet(urlPatterns = {"/files"})
public class MainServlet extends HttpServlet {
    private static final String homeDirectory = "C:\\filemanager\\";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sessionId = req.getSession().getId();
        UserProfile userProfile = AccountService.getUserBySessionId(sessionId);
        if (userProfile == null) {
            resp.sendRedirect("/login");
            return;
        }
        String path = req.getParameter("path");
        File dir;
        if (path == null) {
            dir = new File(homeDirectory + userProfile.getLogin());
            Verification.CheckDir(dir);
        }
        else {
            dir = new File(path);
            if (Verification.DirectoryIsInvisible(userProfile.getLogin(),
                    homeDirectory, dir.getAbsolutePath())) {
                req.getRequestDispatcher("warning.jsp").forward(req, resp);
                return;
            }
        }
        File[] arrFiles = dir.listFiles();
        ArrayList<FileDescription> fileDescriptions = FileDescription.getArray(arrFiles,
                req.getRequestURL().toString());
        req.setAttribute("time", (new SimpleDateFormat("dd.MM.yyyy hh:mm:ss").toString()));
        req.setAttribute("path", dir.getAbsolutePath());
        req.setAttribute("parent",
                req.getRequestURL().toString() + "?path=" + dir.getParent());
        req.setAttribute("list", fileDescriptions);
        req.getRequestDispatcher("page.jsp").forward(req, resp);





        /*
        Код для MainServlet в третьем блоке:

        Map<String, String[]> parameterMap = req.getParameterMap();
        String path = parameterMap.get("path")[0];
        File dir = new File(path);
        File[] arrFiles = dir.listFiles();
        ArrayList<FileDescription> models = FileDescription
                .getArray(arrFiles, req.getRequestURL().toString());
        req.setAttribute("time", new SimpleDateFormat("dd.MM.yyyy hh:mm:ss")
                .format(new Date()));
        req.setAttribute("path", path);
        req.setAttribute("parent", req.getRequestURL().toString() + "?path=" + dir.getParent());
        req.setAttribute("list", models);
        req.getRequestDispatcher("page.jsp").forward(req, resp);*/
    }
}
