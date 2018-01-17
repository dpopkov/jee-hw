package ru.otus.dpopkov.jdbc.servlets;

import ru.otus.dpopkov.jdbc.model.Employee;
import ru.otus.dpopkov.jdbc.model.Position;
import ru.otus.dpopkov.jdbc.util.JPASessionUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
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
        String action = req.getParameter("action");
        if (action == null) {
            action = "list";
        }
        switch(action) {
            case "edit":
                showEditEmployeeForm(resp);
                break;
            case "list":
            default:
                listEmployees(resp);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        switch (action) {
            case "edit":
                editEmployee(req, resp);
                break;
            default:
                resp.sendRedirect("employees");
                break;
        }
    }

    private void editEmployee(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String employeeIdParam = req.getParameter("employeeId");
        String employeeFullName = req.getParameter("employeeFullName");
        String positionParam = req.getParameter("employeePosition");

        EntityManager em = JPASessionUtil.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();

            Long employeeId = Long.parseLong(employeeIdParam);
            TypedQuery<Employee> employeeQuery = em.createQuery("select e from Employee as e where e.id=:employeeId",
                    Employee.class);
            employeeQuery.setParameter("employeeId", employeeId);
            Employee employee = employeeQuery.getSingleResult();

            TypedQuery<Position> positionQuery = em.createQuery("select p from Position as p where p.name=:positionName",
                    Position.class);
            positionQuery.setParameter("positionName", positionParam);
            Position position = positionQuery.getSingleResult();

            employee.setFullName(employeeFullName);
            employee.setPosition(position);
        } catch (Exception e) {
            transaction.rollback();
            throw new ServletException(e);
        } finally {
            em.close();
        }

        transaction.commit();
        resp.sendRedirect("employees");
    }

    private void showEditEmployeeForm(HttpServletResponse resp) throws IOException {
        PrintWriter writer = writeHtmlHeader(resp);

        writer.append("<h2>Edit Employee</h2>\r\n");
        writer.append("<form method=\"POST\" action=\"employees\">\r\n");
        writer.append("    <input type=\"hidden\" name=\"action\" value=\"edit\"/>\r\n");
        writer.append("    Employee ID<br/>\r\n");
        writer.append("    <input type=\"text\" name=\"employeeId\"/><br/><br/>\r\n");
        writer.append("    Full name<br/>\r\n");
        writer.append("    <input type=\"text\" name=\"employeeFullName\"/><br/><br/>\r\n");
        writer.append("    Position<br/>\r\n");
        writer.append("    <input type=\"text\" name=\"employeePosition\"/><br/><br/>\r\n");
        writer.append("    <input type=\"submit\" value=\"Submit\"/>\r\n");
        writer.append("</form>\r\n");

        writeHtmlFooter(writer);
    }

    private void listEmployees(HttpServletResponse resp) throws ServletException {
        EntityManager em = JPASessionUtil.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            Query query = em.createQuery("select e from Employee as e order by e.id desc");
            List<Employee> employees = query.getResultList();
            PrintWriter writer = writeHtmlHeader(resp);

            writer.append("<table>\r\n");
            for (Employee emp : employees) {
                writeEmployee(writer, emp);
            }
            writer.append("</table>\r\n");

            writeHtmlFooter(writer);
        } catch (Exception e) {
            transaction.rollback();
            throw new ServletException(e);
        } finally {
            em.close();
        }

        transaction.commit();
    }

    private void writeEmployee(PrintWriter writer, Employee emp) {
        writer.append("<tr><td>");
        writer.append(emp.getId().toString());
        writer.append("</td><td>\r\n");
        writer.append(emp.getFullName());
        writer.append("</td><td>\r\n");
        writer.append(emp.getLogin());
        writer.append("</td><td>\r\n");
        writer.append(emp.getPosition().getName());
        writer.append("</td></tr>\r\n");
    }

    private PrintWriter writeHtmlHeader(HttpServletResponse resp) throws IOException {
        PrintWriter writer = resp.getWriter();
        writer.append("<html><head><title>Employee Servlet</title></head><body>\r\n");
        return writer;
    }

    private void writeHtmlFooter(PrintWriter writer) {
        writer.append("</body></html>");
    }
}
