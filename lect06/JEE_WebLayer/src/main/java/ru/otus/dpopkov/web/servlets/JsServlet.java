package ru.otus.dpopkov.web.servlets;

import jdk.nashorn.api.scripting.NashornScriptEngineFactory;

import javax.script.ScriptEngine;
import javax.script.ScriptException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(
        name = "jsServlet",
        urlPatterns = {"/js"}
)
public class JsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ScriptEngine engine = new NashornScriptEngineFactory().getScriptEngine("-scripting");

        try {
            PrintWriter pw = resp.getWriter();
            String jsCode = req.getParameter("code");
            if (jsCode != null && !jsCode.isEmpty()) {
                pw.println(engine.eval(jsCode));
            }
        } catch(ScriptException e){
            throw new ServletException(e);
        }
    }
}
