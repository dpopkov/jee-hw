package ru.otus.dpopkov.webstats.servlets;

import ru.otus.dpopkov.webstats.model.User;
import ru.otus.dpopkov.webstats.util.JPASessionUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/dbservlet")
public class DbServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        switch (action) {
            case "init":
                initDb();
                break;
            case "add":
                add(req, resp);
                break;
            case "list":
                listDb(req, resp);
                break;
            default:
                resp.getWriter().println("Unknown action: " + action);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
        String action = req.getParameter("action");
        switch (action) {
            case "add":
                addUser(req, resp);
                break;
        }
    }

    private void addUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        EntityManager em = JPASessionUtil.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();

            User user = new User(login, password);
            em.persist(user);

            transaction.commit();

            resp.sendRedirect("dbservlet?action=list");
        } catch (Exception e) {
            transaction.rollback();
            throw new ServletException(e);
        } finally {
            em.close();
        }
    }

    private void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/jsp/view/add.jsp")
            .forward(req, resp);
    }

    private void listDb(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
        EntityManager em = JPASessionUtil.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();

            TypedQuery<User> query = em.createQuery("select u from User as u", User.class);
            List<User> users = query.getResultList();
            req.setAttribute("users", users);

            transaction.commit();

            req.getRequestDispatcher("/WEB-INF/jsp/view/listUsers.jsp")
                    .forward(req, resp);
        } catch (Exception e) {
            transaction.rollback();
            throw new ServletException(e);
        } finally {
            em.close();
        }
    }

    private void initDb() throws ServletException {
        EntityManager em = JPASessionUtil.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();

            User user;
            user = new User("user", "1234");
            em.persist(user);
            user = new User("admin", "4321");
            em.persist(user);
            user = new User("guest", "guest");
            em.persist(user);

            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw new ServletException(e);
        } finally {
            em.close();
        }
    }
}
