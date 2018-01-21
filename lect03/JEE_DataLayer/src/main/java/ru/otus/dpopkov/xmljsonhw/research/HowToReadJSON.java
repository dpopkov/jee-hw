package ru.otus.dpopkov.xmljsonhw.research;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.otus.dpopkov.jdbc.model.Employee;
import ru.otus.dpopkov.jdbc.model.Employees;

import java.io.*;
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
}
