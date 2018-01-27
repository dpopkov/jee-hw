package ru.otus.dpopkov.web.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.net.URL;

@WebServlet(
        name = "currenciesServlet",
        urlPatterns = {"/currencies"},
        loadOnStartup = 1
)
public class CurrenciesServlet extends HttpServlet {
    private static final String CURRENCIES_URL = "http://www.cbr.ru/scripts/XML_daily.asp";
    private static final String CURRENCIES_CHARSET = "windows-1251";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        InputStream xslStream = getServletContext().getResourceAsStream("/WEB-INF/xsl/currency.xsl");
        StreamSource xslSource = new StreamSource(xslStream);

        InputStreamReader xmlReader = new InputStreamReader(new URL(CURRENCIES_URL).openStream(), CURRENCIES_CHARSET);
        StreamSource xmlSource = new StreamSource(xmlReader);

        try {
            Transformer transformer = TransformerFactory.newInstance().newTransformer(xslSource);
            Writer stringWriter = new StringWriter();
            transformer.transform(xmlSource, new StreamResult(stringWriter));
            resp.getWriter().println(stringWriter.toString());
        } catch (javax.xml.transform.TransformerException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
