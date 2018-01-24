package ru.otus.dpopkov.study.xslt.servlet;

import ru.otus.dpopkov.study.xslt.XSLTransformer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.TransformerException;
import javax.xml.transform.stream.StreamSource;
import java.io.IOException;

@WebServlet(
        name = "zooServlet",
        urlPatterns = {"/zoo"}
)
public class ZooServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            StreamSource xslSource = new StreamSource(getServletContext().getResourceAsStream("/xml/zoo.xsl"));
            StreamSource xmlSource = new StreamSource(getServletContext().getResourceAsStream("/xml/zoo.xml"));
            XSLTransformer transformer = new XSLTransformer(xslSource);
            transformer.transform(xmlSource, resp.getWriter());
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }
}
