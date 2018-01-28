package ru.otus.dpopkov.web.servlets;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(
        name = "newsCrawlerServlet",
        urlPatterns = {"/rbc"},
        initParams = {
                @WebInitParam(name = "numberOfNews", value="4")
        }
)
public class NewsCrawlerServlet extends HttpServlet {
    private static final String NEWS_URL = "https://www.rbc.ru";

    private int numberOfNews;

    @Override
    public void init() throws ServletException {
        String numString = getServletConfig().getInitParameter("numberOfNews");
        numberOfNews = Integer.parseInt(numString);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Document doc  = Jsoup.connect(NEWS_URL).get();
        Element divCentralWrapper = doc.getElementsByAttributeValue("data-vr-zone", "Центральная колонка").first();
        Element divItemWrapper = divCentralWrapper.getElementsByClass("item__wrapper").first();
        Elements elements = divItemWrapper.children();
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter writer = resp.getWriter();
        writer.append("<ul>");
        int newsCount = 0;
        for(Element div : elements) {
            if (newsCount == numberOfNews) {
                break;
            }
            Element anchor = div.getElementsByTag("a").first();
            if (anchor == null) {
                continue;
            }
            String href = anchor.attr("href");
            String title = anchor.getElementsByTag("span").text().trim();
            writer.append("<li><a href=\"");
            writer.append(href);
            writer.append("\">");
            writer.append(title);
            writer.append("</a></li>\r\n");
            newsCount++;
        }
        writer.append("</ul");
    }
}
