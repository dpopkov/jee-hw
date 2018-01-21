package ru.otus.dpopkov.xmljsonhw;

import ru.otus.dpopkov.jdbc.model.Employee;
import ru.otus.dpopkov.jdbc.model.Employees;
import ru.otus.dpopkov.jdbc.model.Position;
import ru.otus.dpopkov.jdbc.util.JPASessionUtil;
import ru.otus.dpopkov.resource.XmlBean;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@WebServlet(
        name = "marshalXmlServlet",
        urlPatterns = {"/marshalxml"}
)
public class MarshalXMLServlet extends HttpServlet {
    @Resource(name = "bean/XmlBeanFactory")
    private XmlBean xmlBean;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        List<Employee> list = prepareEmployees();
        List<Employee> list = getEmployees();
        Employees employees = new Employees(list);
        xmlBean.process(employees);
//        process(employees, MarshalXMLServlet.class.)

        PrintWriter writer = resp.getWriter();
        writer.println("xml path = " + xmlBean.getXmlPath());
    }

    /* This method used for testing */
    private List<Employee> prepareEmployees() {
        List<Employee> list = new ArrayList<>();
        Employee employee;
        Position hr = new Position(10L, "HR");
        employee = new Employee(1L, "Petrov Petr", BigDecimal.valueOf(100000L));
        employee.setPosition(hr);
        list.add(employee);
        employee = new Employee(2L, "Nititin Nikita", BigDecimal.valueOf(200000L));
        employee.setPosition(hr);
        list.add(employee);
        return list;
    }


    private void process(Object persistObject, OutputStream outputStream) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(persistObject.getClass());
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(persistObject, outputStream);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    private List<Employee> getEmployees() throws ServletException {
        List<Employee> employees;
        EntityManager em = JPASessionUtil.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            TypedQuery<Employee> query = em.createQuery("select e from Employee as e order by e.id desc",
                    Employee.class);
            employees = query.getResultList();
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw new ServletException(e);
        } finally {
            em.close();
        }
        return employees;
    }
}
