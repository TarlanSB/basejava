package ru.javawebinar.basejava.web;

import ru.javawebinar.basejava.Config;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.storage.Storage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ResumeServlet extends HttpServlet {

    private Storage storage;

    @Override
    public void init() throws ServletException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        storage = Config.get().getStorage();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(createResumeHtmlTable(storage.getAllSorted()));
    }

    String createResumeHtmlTable(List<Resume> resumeList) {
        StringBuilder sb = new StringBuilder();
        sb.append("<html>")
                .append("<body>")
                .append("<h1>Table of resumes</h1>")
                .append("<table border='1'>")
                .append("<tr>")
                .append("<th>UUID</th>")
                .append("<th>FullName</th>")
                .append("</tr>");
        for (Resume r : resumeList) {
            sb.append("<tr>")
                    .append("<td>")
                    .append(r.getUuid())
                    .append("</td>")
                    .append("<td>")
                    .append(r.getFullName())
                    .append("</td>")
                    .append("</tr>");
        }
        sb.append("</table>")
                .append("</body>")
                .append("</html>");
        return sb.toString();
    }
}



