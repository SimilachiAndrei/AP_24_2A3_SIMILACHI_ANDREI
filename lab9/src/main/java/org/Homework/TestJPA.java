package org.Homework;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TestJPA {

    public void testJPA() {
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("ExamplePU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Author author = new Author("Mark Twain");
        em.persist(author);
        Author a = (Author)em.createQuery(
                        "select e from Author e where e.name='Mark Twain'")
                .getSingleResult();
        a.setName("Samuel Langhorne Clemens");
        em.getTransaction().commit();
        em.close();
        emf.close();
    }
}
