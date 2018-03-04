package ru.otus.dpopkov.webstats.util;

import org.hibernate.Session;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.HashMap;
import java.util.Map;

public class JPASessionUtil {
    public static final String PERSISTENCE_UNIT_NAME = "jpa";

    private static Map<String, EntityManagerFactory> persistenceUnits = new HashMap<>();

    public static synchronized EntityManager getEntityManager() {
        return getEntityManager(PERSISTENCE_UNIT_NAME);
    }

    public static synchronized EntityManager getEntityManager(String persistenceUnitName) {
        persistenceUnits.putIfAbsent(persistenceUnitName, Persistence.createEntityManagerFactory(persistenceUnitName));
        return persistenceUnits.get(persistenceUnitName).createEntityManager();
    }

    public static Session getSession(String persistenceUnitName) {
        return getEntityManager(persistenceUnitName).unwrap(Session.class);
    }
}
