package ru.otus.dpopkov.xml;

import ru.otus.dpopkov.resource.XmlBean;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;

@WebServlet(
        name = "xmlServlet", urlPatterns = {"/xml"}
        )
public class XmlServlet extends HttpServlet {

    @Resource(name = "bean/XmlBeanFactory")
    private XmlBean xmlBean;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = getParameter(req,"name", "Jack Sparrow");
        int id = getIntParameter(req, "id", 42);
        int age = getIntParameter(req, "age", 33);

        Customer c = new Customer();
        c.setName(name);
        c.setId(id);
        c.setAge(age);
        c.setSalary(new BigDecimal("100000.00"));
        xmlBean.process(c);

        PrintWriter writer = resp.getWriter();
        writer.println("xml path = " + xmlBean.getXmlPath());
    }

    private String getParameter(HttpServletRequest req, String name, String defaultValue) {
        String param = req.getParameter(name);
        if (param == null) {
            param = defaultValue;
        }
        return param;
    }

    private int getIntParameter(HttpServletRequest req, String name, int defaultValue) {
        String strValue = req.getParameter(name);
        if (strValue == null) {
            return defaultValue;
        } else {
            return Integer.parseInt(strValue);
        }
    }
}
