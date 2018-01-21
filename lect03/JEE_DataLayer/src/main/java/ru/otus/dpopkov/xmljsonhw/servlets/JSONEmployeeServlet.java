package ru.otus.dpopkov.xmljsonhw.servlets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.otus.dpopkov.jdbc.model.Employees;
import ru.otus.dpopkov.resource.XmlBean;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

@WebServlet(
        name = "jsonEmployeeServlet",
        urlPatterns = {"/jsonemployee"}
)
public class JSONEmployeeServlet extends HttpServlet {

    @Resource(name = "bean/XmlBeanFactory")
    private XmlBean xmlBean;

    private String xmlLocation;
    private String jsonLocation;
    private Gson json;

    @Override
    public void init() throws ServletException {
        xmlLocation = xmlBean.getXmlPath().toString();
        jsonLocation = xmlLocation.replace(".xml", ".json");
        json = new GsonBuilder().setPrettyPrinting().create();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "xml2json":
                convertXmlToJson(resp);
                break;
            case "json2model":
                convertJsonToModel(resp);
                break;
            default:
                resp.getWriter().println("Allowed values for action parameter: xml2json, json2model");
                break;
        }
    }

    private void convertXmlToJson(HttpServletResponse resp) throws ServletException {
        try (InputStream stream = new FileInputStream(xmlLocation)) {
            Employees employees = unmarshalXML(stream, Employees.class);
            String jsonString = json.toJson(employees);
            Files.write(Paths.get(jsonLocation), jsonString.getBytes());
            resp.getWriter().println(jsonString);
        } catch (IOException | JAXBException e) {
            throw new ServletException(e);
        }
    }

    private void convertJsonToModel(HttpServletResponse resp) throws ServletException {
        try  {
            String jsonString = new String(Files.readAllBytes(Paths.get(jsonLocation)));
            Employees employees = json.fromJson(jsonString, Employees.class);
            PrintWriter writer = resp.getWriter();
            for (int i = 0; i < employees.size(); i++) {
                if (i % 2 != 0) {
                    writer.println(i + ": " + employees.get(i).toString());
                }
            }
        } catch (IOException e) {
            throw new ServletException(e);
        }
    }

    private static <T> T unmarshalXML(InputStream inputStream, Class<T> clazz) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        Object object = unmarshaller.unmarshal(inputStream);
        return clazz.cast(object);
    }
}
