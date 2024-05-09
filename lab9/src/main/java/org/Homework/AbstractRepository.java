package org.Homework;

import lombok.Getter;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Getter


public abstract class AbstractRepository<T> {
    private EntityManagement entityManagement;
    private Class<T> entityClass;

    public AbstractRepository(Class<T> entityClass) {
        entityManagement = EntityManagement.getInstance();
        this.entityClass = entityClass;
    }

    public void create(T object) {
        EntityManager entityManager = entityManagement.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(object);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public T findById(Integer id) {
        EntityManager entityManager = entityManagement.getEntityManagerFactory().createEntityManager();
        T object = entityManager.find(entityClass, id);
        entityManager.close();
        return object;
    }

    public List<T> findByName(String namePattern) {
        EntityManager entityManager = entityManagement.getEntityManagerFactory().createEntityManager();
        String queryName = entityClass.getSimpleName() + ".findByName";
        TypedQuery<T> query = entityManager.createNamedQuery(queryName, entityClass);
        query.setParameter("namePattern", "%" + namePattern + "%");
        List<T> objects = query.getResultList();
        entityManager.close();
        return objects;
    }
}
