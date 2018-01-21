package ru.otus.dpopkov.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.otus.dpopkov.json.model.Car;
import ru.otus.dpopkov.json.model.Owner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(
        name = "GSONServlet",
        urlPatterns = {"/gson"}
)
public class GSONServlet extends HttpServlet {
//    private static Gson json = new Gson();
    private static Gson json = new GsonBuilder().setPrettyPrinting().create();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");

        Car car = new Car("Lada", "Vesta", 600000.51);
        Owner owner = new Owner("Ivanov Ivan Ivanovich", 30);
        car.setOwner(owner);

        PrintWriter writer = resp.getWriter();
        String jsonString = json.toJson(car);
        writer.println(jsonString);
        System.out.println(jsonString);
    }
}
