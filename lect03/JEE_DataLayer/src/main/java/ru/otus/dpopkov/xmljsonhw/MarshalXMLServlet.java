package ru.otus.dpopkov.xmljsonhw;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
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
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@WebServlet(
        name = "marshalXmlServlet",
        urlPatterns = {"/marshalxml"}
)
public class MarshalXMLServlet extends HttpServlet {
    @Resource(name = "bean/XmlBeanFactory")
    private XmlBean xmlBean;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "write":
                writeXml(resp);
                break;
            case "find":
                findEmployees(resp);
                break;
            default:
                resp.getWriter().println("Allowed values for action parameter: write, find");
                break;
        }
    }

    private void findEmployees(HttpServletResponse resp) throws ServletException {
        String location = xmlBean.getXmlPath().toString();

        try {
            Document document = getDocument(location);

            Element root = document.getDocumentElement();
            System.out.println(root.getNodeName() + ":");

            XPathFactory xPathFactory = XPathFactory.newInstance();
            XPath xPath = xPathFactory.newXPath();
            XPathExpression xPathExpression;
            Object result;

            xPathExpression = xPath.compile("sum(//employee/salary)");
            result = xPathExpression.evaluate(document, XPathConstants.NUMBER);
            double sum = (double) result;

            xPathExpression = xPath.compile("count(//employee/salary)");
            result = xPathExpression.evaluate(document, XPathConstants.NUMBER);
            double count = (double) result;

            double averageSalary = sum / count;
            String averageSalaryStr = String.format(Locale.ENGLISH, "%.2f", averageSalary);

            String expression = "//employee[salary>" + averageSalaryStr + "]";
            xPathExpression = xPath.compile(expression);
            result = xPathExpression.evaluate(document, XPathConstants.NODESET);
            NodeList nodeList = (NodeList) result;

            displayEmployees(nodeList, resp);
        } catch (ParserConfigurationException | IOException | SAXException | XPathExpressionException e) {
            throw new ServletException(e);
        }
    }

    private void displayEmployees(NodeList nodeList, HttpServletResponse resp) throws IOException {
        PrintWriter writer = resp.getWriter();
        writer.append("<html><body><table>\r\n");
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                writer.append("<tr>");
                displayElement(writer, element, "id");
                displayElement(writer, element, "fullName");
                displayElement(writer, element, "login");
                displayElement(writer, element, "salary");
                writer.append("</tr>\r\n");
            }
        }
        writer.append("</table></body></html>\r\n");
    }

    private static void displayElement(PrintWriter writer, Element element, String tagName) {
        writer.println("<td>" + element.getElementsByTagName(tagName).item(0).getTextContent() + "</td>");
    }

    private Document getDocument(String location) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        Document document = builder.parse(new File(location));
        document.getDocumentElement().normalize();
        return document;
    }

    private void writeXml(HttpServletResponse resp) throws ServletException, IOException {
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
