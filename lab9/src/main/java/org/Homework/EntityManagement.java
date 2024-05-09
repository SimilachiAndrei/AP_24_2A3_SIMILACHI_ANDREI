package org.Homework;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagement {
    private static EntityManagement instance;
    private EntityManagerFactory entityManagerFactory;

    private EntityManagement() {
        entityManagerFactory = Persistence.createEntityManagerFactory("myPersistenceUnit");
    }

    public static synchronized EntityManagement getInstance() {
        if (instance == null) {
            instance = new EntityManagement();
        }
        return instance;
    }

    public EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }

    public void closeEntityManagerFactory() {
        if (entityManagerFactory != null && entityManagerFactory.isOpen()) {
            entityManagerFactory.close();
        }
    }
}
