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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Hashtable;
import java.util.List;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static Hashtable<String, String> users = new Hashtable<>();

    static {
        users.put("admin", "admin");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if (req.getParameter("logout") != null) {
            session.invalidate();
            resp.sendRedirect("login");
            return;
        }
        req.setAttribute("loginFailed", false);
        req.getRequestDispatcher("/WEB-INF/jsp/view/login.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        if (loginPasswordCorrect(login, password)) {
            req.getSession().setAttribute("username", login);
            req.changeSessionId();
            resp.sendRedirect("page");
        } else {
            req.setAttribute("loginFailed", true);
            req.getRequestDispatcher("/WEB-INF/jsp/view/login.jsp")
                    .forward(req, resp);
        }
    }

    private boolean loginPasswordCorrect(String login, String password) throws ServletException {
        if (login == null || password == null) {
            return false;
        } else if (users.containsKey(login) && users.get(login).equals(password)) {
            return true;
        } else {
            EntityManager em = JPASessionUtil.getEntityManager();
            try {
                TypedQuery<User> query = em.createQuery("select u from User as u where u.name=:login",
                        User.class);
                query.setParameter("login", login);
                List<User> result = query.getResultList();
                if (result.size() != 1) {
                    return false;
                }
                User user = result.get(0);
                return password.equals(user.getPassword());
            } catch (Exception e) {
                throw new ServletException(e);
            } finally {
                em.close();
            }
        }
    }
}
