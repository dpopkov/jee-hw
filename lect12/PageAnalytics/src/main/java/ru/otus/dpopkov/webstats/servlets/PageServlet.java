package ru.otus.dpopkov.webstats.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.InetAddress;

@WebServlet("/page")
public class PageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("username") == null) {
            resp.sendRedirect("login");
            return;
        }

        String pageNumber = req.getParameter("number");
        if (pageNumber != null) {
            String view = "page" + pageNumber;
            req.getRequestDispatcher("/WEB-INF/jsp/view/" + view + ".jsp")
                    .forward(req, resp);
        } else {
            resp.getWriter().println("Hello page without number");
        }
    }
}
