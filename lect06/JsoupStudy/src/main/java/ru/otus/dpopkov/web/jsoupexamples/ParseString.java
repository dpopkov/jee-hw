package ru.otus.dpopkov.web.jsoupexamples;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class ParseString {
    public static void main(String[] args) {
        String html = "<html><head><title>First parse</title></head>"
                + "<body><p>Parsed HTML into a doc.</p></body></html>";
        Document doc = Jsoup.parse(html);
        System.out.println(doc.title());
        System.out.println(doc.body().getElementsByTag("p").first().text());
    }
}
