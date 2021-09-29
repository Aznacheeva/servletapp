package servlets;

import mypackage.FileDescription;

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
import java.util.Map;

@WebServlet(urlPatterns = {"/files"})
public class MainServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String[]> parameterMap = req.getParameterMap();
        String path = parameterMap.get("path")[0];
        File dir = new File(path);
        File[] arrFiles = dir.listFiles();
        ArrayList<FileDescription> models = FileDescription.getArray(arrFiles != null ? arrFiles : new File[0], req.getRequestURL().toString());
        req.setAttribute("parent", req.getRequestURL().toString() + "?path=" + dir.getParent());
        req.setAttribute("time", new SimpleDateFormat("dd.MM.yyyy hh:mm:ss")
                .format(new Date()));
        req.setAttribute("path", path);
        req.setAttribute("list", models);
        req.getRequestDispatcher("page.jsp").forward(req, resp);
    }
}
