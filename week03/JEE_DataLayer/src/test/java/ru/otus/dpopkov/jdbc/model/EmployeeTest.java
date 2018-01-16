package ru.otus.dpopkov.jdbc.model;

import org.junit.Test;
import ru.otus.dpopkov.jdbc.util.JPASessionUtil;

import javax.persistence.EntityManager;

import java.util.Date;

import static org.junit.Assert.*;

public class EmployeeTest {

    @Test
    public void createEmployee() {
        String fullName = "John Doe" + " : " + new Date();
        EntityManager em = null;
        try {
            em = JPASessionUtil.getEntityManager("jpa-test");
            em.getTransaction().begin();

            Employee employee = new Employee();
            employee.setFullName(fullName);
            assertNull(employee.getId());
            em.persist(employee);

            em.getTransaction().commit();

            assertNotNull(employee.getId());
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
}