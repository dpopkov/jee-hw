package ru.otus.dpopkov.web.jsoupexamples;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class ParseFile {
    public static void main(String[] args) {
        URL url = ParseFile.class.getClassLoader().getResource("page.html");
        File file = new File(url.getFile());
        try {
            Document doc = Jsoup.parse(file, "UTF-8");
            System.out.println("Title: " + doc.title());
            Element topNav = doc.getElementById("top-nav");
            Elements links = topNav.getElementsByTag("a");
            for (Element link : links) {
                String href = link.attr("href");
                String text = link.text();
                System.out.println(href + " -> " + text);
            }

            System.out.println("\nAll elements with 'src' attribute:");
            Elements e = doc.select("[src]");
            for (Element s : e) {
                System.out.printf("%s : %s%n", s.tagName(), s.attr("src"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
