package org.Compulsory;

import lombok.Getter;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Getter

public class AuthorRepository {
    private EntityManagement entityManagement;

    public AuthorRepository() {
        entityManagement = EntityManagement.getInstance();
    }

    public void create(Author author) {
        EntityManager entityManager = entityManagement.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(author);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public Author findById(Integer id) {
        EntityManager entityManager = entityManagement.getEntityManagerFactory().createEntityManager();
        Author author = entityManager.find(Author.class, id);
        entityManager.close();
        return author;
    }

    public List<Author> findByName(String namePattern) {
        EntityManager entityManager = entityManagement.getEntityManagerFactory().createEntityManager();
        TypedQuery<Author> query = entityManager.createNamedQuery("Author.findByName", Author.class);
        query.setParameter("namePattern", "%" + namePattern + "%");
        List<Author> authors = query.getResultList();
        entityManager.close();
        return authors;
    }
}