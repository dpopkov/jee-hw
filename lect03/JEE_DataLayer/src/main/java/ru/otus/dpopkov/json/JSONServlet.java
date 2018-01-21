package ru.otus.dpopkov.json;

import ru.otus.dpopkov.json.model.User;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(
        name = "JSONServlet",
        urlPatterns = {"/json"}
)
public class JSONServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        User user = new User();
        user.setLogin("test-login");
        user.setPassword(new char[]{'t', 'e', 's', 't'});

        // Create Jsonb and serialize
        Jsonb jsonb = JsonbBuilder.create();
        String result = jsonb.toJson(user);

        PrintWriter writer = resp.getWriter();
        writer.println(result);
//        writer.println(jsonb.toJson(result)); // prints "{\"login\":\"test-login\",\"password\":\"test\"}"
    }
}
