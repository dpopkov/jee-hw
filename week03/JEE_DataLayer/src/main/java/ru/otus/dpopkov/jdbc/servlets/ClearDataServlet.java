package ru.otus.dpopkov.jdbc.servlets;

import ru.otus.dpopkov.jdbc.util.JPASessionUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(
        name = "clearDataServlet",
        urlPatterns = {"/clear"}
)
public class ClearDataServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EntityManager em = JPASessionUtil.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();

            em.createQuery("delete from Role").executeUpdate();
            em.createQuery("delete from Employee ").executeUpdate();
            em.createQuery("delete from Position ").executeUpdate();

            transaction.commit();
        }  catch (Exception e) {
            transaction.rollback();
            throw new ServletException(e);
        } finally {
            em.close();
        }

        resp.sendRedirect("employees?action=list");
    }
}
