package ru.otus.dpopkov.jdbc.servlets;

import au.com.bytecode.opencsv.CSVReader;
import ru.otus.dpopkov.jdbc.model.Employee;
import ru.otus.dpopkov.jdbc.model.Position;
import ru.otus.dpopkov.jdbc.model.Role;
import ru.otus.dpopkov.jdbc.util.JPASessionUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;

@WebServlet(
        name = "initDataServlet",
        urlPatterns = {"/init"}
)
public class InitDataServlet extends HttpServlet {

    public static final String POSITIONS_CSV_LOCATION = "/positions.csv";
    public static final String EMPLOYEES_CSV_LOCATION = "/employees.csv";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EntityManager em = JPASessionUtil.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            addDataToDb(em);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw new ServletException(e);
        } finally {
            em.close();
        }
        resp.sendRedirect("index.html");
    }

    private void addDataToDb(EntityManager em) throws IOException {
        /*Role viewPersonal = new Role();
        viewPersonal.setName("ViewPersonalInfo");

        Position regular = new Position();
        regular.setName("RegularEmployee");
        regular.getRoles().add(viewPersonal);

        Employee employee = new Employee();
        employee.setFullName("Jack Sparrow");
        employee.setPosition(regular);

        em.persist(employee);*/

        Map<String, Position> positionMap = readPositions();
        List<Employee> employeeList = readEmployees(positionMap);
        for (Employee employee : employeeList) {
            em.persist(employee);
        }
    }

    private Map<String, Position> readPositions() throws IOException {
        Map<String, Position> positionMap = new HashMap<>();
        Map<String, Role> roleMap = new HashMap<>();

        List<String[]> list = csvToList(POSITIONS_CSV_LOCATION);
        for (String[] line : list) {
            Position position = new Position();
            String name = line[0];
            position.setName(name);
            for (int i = 1; i < line.length; i++) {
                String roleName = line[i];
                Role role = new Role();
                role.setName(roleName);
                roleMap.putIfAbsent(roleName, role);
                position.getRoles().add(roleMap.get(roleName));
            }
            positionMap.put(name, position);
        }
        return positionMap;
    }

    private List<Employee> readEmployees(Map<String, Position> positionMap) throws IOException {
        List<Employee> employeeList = new ArrayList<>();
        List<String[]> list = csvToList(EMPLOYEES_CSV_LOCATION);
        for(String[] line : list) {
            // todo: create every employee
            String fullName = line[0];
            Employee employee = new Employee();
            employee.setFullName(fullName);
            employee.setLogin(line[1]);
            Position position = positionMap.get(line[2]);
            employee.setPosition(position);
            employeeList.add(employee);
        }
        return employeeList;
    }

    private List<String[]> csvToList(String csvResourceLocation) throws IOException {
        List<String[]> lines;
        try (InputStream inputStream = Thread.currentThread().getContextClassLoader()
                .getResourceAsStream(csvResourceLocation)) {
            CSVReader reader = new CSVReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            lines = reader.readAll();
        }
        return lines;
    }
}
