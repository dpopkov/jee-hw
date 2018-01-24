package ru.otus.dpopkov.web;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class JsoupMain {
    public static void main(String[] args) {
        Document doc;
        String url = "http://localhost:8080/web/";
        try {
            doc = Jsoup.connect(url).get();
            System.out.println(doc.title());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
