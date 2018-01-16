package ru.otus.dpopkov.jdbc.servlets;

import ru.otus.dpopkov.jdbc.model.Employee;
import ru.otus.dpopkov.jdbc.util.JPASessionUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(
        name = "employeeServlet",
        urlPatterns = {"/employees"}
)
public class EmployeeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EntityManager em = JPASessionUtil.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            Query query = em.createQuery("from Employee");
            List<Employee> employees = query.getResultList();
            PrintWriter writer = writeHtmlHeader(resp);
            writer.append("<table>\r\n");
            for (Employee emp : employees) {
                writer.append("<tr><td>");
                writer.append(emp.getFullName());
                writer.append("</td><td>\r\n");
                writer.append(emp.getLogin());
                writer.append("</td><td>\r\n");
                writer.append(emp.getPosition().getName());
                writer.append("</td></tr>\r\n");
            }
            writer.append("</table></body></html>");
        } catch (Exception e) {
            transaction.rollback();
            throw new ServletException(e);
        } finally {
            em.close();
        }

        transaction.commit();
    }

    private PrintWriter writeHtmlHeader(HttpServletResponse resp) throws IOException {
        PrintWriter writer = resp.getWriter();
        writer.append("<html><head><title>Employee Servlet</title></head><body>\r\n");
        return writer;
    }
}
