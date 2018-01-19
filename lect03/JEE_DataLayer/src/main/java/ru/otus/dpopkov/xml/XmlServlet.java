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
        PrintWriter writer = resp.getWriter();
        Customer c = new Customer();
        c.setName("Jack Sparrow");
        c.setId(42);
        c.setAge(33);
        c.setSalary(new BigDecimal("100000.00"));
        xmlBean.process(c);
        writer.println("xml path = " + xmlBean.getXmlPath());
    }
}
