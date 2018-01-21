package ru.otus.dpopkov.xmljsonhw.research;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.otus.dpopkov.jdbc.model.Employee;
import ru.otus.dpopkov.jdbc.model.Employees;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class HowToReadJSON {
    public static void main(String[] args) throws IOException {
        String location = "c:/temp/employees.json";

        String jsonString = new String(Files.readAllBytes(Paths.get(location)));
        Gson json = new GsonBuilder().create();
        Employees result = json.fromJson(jsonString, Employees.class);
        for (Employee e : result) {
            System.out.println(e);
        }
    }

    private static Employees readEmployees(String location) throws IOException {
        String jsonString = new String(Files.readAllBytes(Paths.get(location)));
        Gson json = new GsonBuilder().create();
        return json.fromJson(jsonString, Employees.class);
    }

    private static void exampleOfReadingFileToString() throws IOException {
        final String fileName = "manifest.mf";

        // How to read file into String before Java 7
        InputStream is = new FileInputStream(fileName);
        BufferedReader buf = new BufferedReader(new InputStreamReader(is));
        String line = buf.readLine();
        StringBuilder sb = new StringBuilder();
        while (line != null) {
            sb.append(line).append("\n");
            line = buf.readLine();
        }
        String fileAsString = sb.toString();
        System.out.println("Contents (before Java 7) : " + fileAsString);

        // Reading file into String in one line in JDK 7
        String contents = new String(Files.readAllBytes(Paths.get(fileName)));
        System.out.println("Contents (Java 7) : " + contents);

        // Reading file into String using proper character encoding
        String fileString = new String(Files.readAllBytes(Paths.get(fileName)),
                StandardCharsets.UTF_8);
        System.out.println("Contents (Java 7 with character encoding ) : " + fileString);

        // It's even easier in Java 8
        Files.lines(Paths.get(fileName), StandardCharsets.UTF_8).forEach(System.out::println);
    }

}
