package ru.otus.dpopkov.webstats.servlets;

import ru.otus.dpopkov.webstats.model.StatMarker;
import ru.otus.dpopkov.webstats.util.JPASessionUtil;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/show-stats")
public class StatsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<StatMarker> statMarkers = getStatMarkers();
        req.setAttribute("statMarkers", statMarkers);
        req.getRequestDispatcher("/WEB-INF/jsp/view/showStats.jsp")
                .forward(req, resp);
    }

    private List<StatMarker> getStatMarkers() throws ServletException {
        EntityManager em = JPASessionUtil.getEntityManager();
        try {
            TypedQuery<StatMarker> query = em.createQuery("select s from StatMarker as s",
                    StatMarker.class);
            return query.getResultList();
        } catch (Exception e) {
            throw new ServletException(e);
        } finally {
            em.close();
        }
    }
}
