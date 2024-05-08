package org.Compulsory;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Create a new author
        Author author1 = new Author("John Doe");

        // Create an instance of AuthorRepository
        AuthorRepository authorRepository = new AuthorRepository();

        // Save the author to the database

        // Find author by ID
        Author foundAuthor = authorRepository.findById(1);
        System.out.println("Found Author: " + foundAuthor);

        // Close the entity manager factory when done
        authorRepository.getEntityManagement().closeEntityManagerFactory();
    }
}
