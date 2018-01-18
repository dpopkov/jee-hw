package ru.otus.dpopkov.jdbc.servlets;

import ru.otus.dpopkov.jdbc.model.Employee;
import ru.otus.dpopkov.jdbc.model.Position;
import ru.otus.dpopkov.jdbc.util.JPASessionUtil;

import javax.persistence.*;
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

    private static final String STORED_PROCEDURE_EMPLOYEE_NAME_MAX_SALARY = "employee_name_max_salary";

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
            case "maxSalary":
                showEmployeeWithMaxSalary(resp);
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
            case "remove":
                removeEmployee(req, resp);
                break;
            default:
                resp.sendRedirect("employees");
                break;
        }
    }

    private void showEmployeeWithMaxSalary(HttpServletResponse resp) throws ServletException {
        EntityManager em = JPASessionUtil.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();

            createStoredProcedure();

            StoredProcedureQuery query = em.createStoredProcedureQuery(STORED_PROCEDURE_EMPLOYEE_NAME_MAX_SALARY);
            String name = (String)query.getSingleResult();  // todo: try to replace method to getResultList()
            PrintWriter writer = resp.getWriter();
            writer.println("Employee with maximum salary: " + name);

            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw new ServletException(e);
        } finally {
            em.close();
        }
    }

    private void createStoredProcedure() throws ServletException {
        EntityManager em = JPASessionUtil.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();

            // todo: fix the warning below
            // Warning: No data sources are configured to run this SQL and provide advanced code assistance.
            String sqlCreateFunction = "create or replace function employee_name_max_salary()\n" +
                    "returns character varying(255) as\n" +
                    "$$ select e.fullname from employee as e where e.salary in (select max(salary) from employee) $$\n" +
                    "language 'sql' volatile;";
            em.createNativeQuery(sqlCreateFunction).executeUpdate();

            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw new ServletException(e);
        } finally {
            em.close();
        }
    }

    private void removeEmployee(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String employeeIdParam = req.getParameter("employeeId");

        EntityManager em = JPASessionUtil.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            Long employeeId = Long.parseLong(employeeIdParam);
            Query query = em.createQuery("delete from Employee as e where e.id=:employeeId");
            query.setParameter("employeeId", employeeId);
            query.executeUpdate();

            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw new ServletException(e);
        } finally {
            em.close();
        }

        resp.sendRedirect("employees");
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

            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw new ServletException(e);
        } finally {
            em.close();
        }

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
            TypedQuery<Employee> query = em.createQuery("select e from Employee as e order by e.id desc",
                    Employee.class);
            List<Employee> employees = query.getResultList();
            PrintWriter writer = writeHtmlHeader(resp);

            writer.append("<table>\r\n");
            writeEmployeesHeader(writer);
            for (Employee emp : employees) {
                writeEmployee(writer, emp);
            }
            writer.append("</table>\r\n");

            writeHtmlFooter(writer);

            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw new ServletException(e);
        } finally {
            em.close();
        }
    }

    private void writeEmployeesHeader(PrintWriter writer) {
        writer.append("<tr><th>Id</th><th>Full name</th><th>Login</th><th>Position</th><th>Salary</th></tr>\r\n");
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
        writer.append("</td><td>\r\n");
        writer.append(emp.getSalary().toString());
        writer.append("</td><td>\r\n");
        writer.append(buildRemoveEmployeeForm(emp.getId()));
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

    private String buildRemoveEmployeeForm(Long id) {
        return "<form action=\"employees\" method=\"POST\">\n" +
                "        <input type=\"hidden\" name=\"action\" value=\"remove\">\n" +
                "        <input type=\"hidden\" name=\"employeeId\" value=\"" + id + "\">\n" +
                "        <input type=\"submit\" value=\"Remove\">\n" +
                "    </form>";
    }
}
