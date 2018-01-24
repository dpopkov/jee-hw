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
import java.io.InputStream;

@WebServlet(
        name = "cbServlet",
        urlPatterns = {"/cb"}
)
public class CbServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        InputStream xslStream = getServletContext().getResourceAsStream("xml/cb.xsl");
        StreamSource xslSource = new StreamSource(xslStream);

        resp.setContentType("text/html; charset=windows-1251");
        try {
            XSLTransformer transformer = new XSLTransformer(xslSource);
            StreamSource xmlSource = new StreamSource(getServletContext().getResourceAsStream("xml/cb.xml"));
            transformer.transform(xmlSource, resp.getWriter());
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }
}


