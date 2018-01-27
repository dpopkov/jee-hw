package ru.otus.dpopkov.web.research;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

public class GetXmlMain {

    private static final String CURRENCIES_CHARSET = "windows-1251";
    private static final String CURRENCIES_URL = "http://www.cbr.ru/scripts/XML_daily.asp";

    public static void main(String[] args) {
        try {
            String out = new Scanner(new URL(CURRENCIES_URL).openStream(), CURRENCIES_CHARSET).useDelimiter("\\A").next();
            System.out.println(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
