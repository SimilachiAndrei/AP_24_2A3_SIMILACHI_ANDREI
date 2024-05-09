package org.Homework;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Create repositories
//        AuthorRepository authorRepository = new AuthorRepository();
//        GenreRepository genreRepository = new GenreRepository();
//        HouseRepository publishingHouseRepository = new HouseRepository();
        BookRepository bookRepository = new BookRepository();
//
//        // Create authors
//        Author author1 = new Author("John Doe");
//        Author author2 = new Author("Jane Smith");
//        authorRepository.create(author1);
//        authorRepository.create(author2);
//
//        // Create genres
//        Genre genre1 = new Genre("Fiction");
//        Genre genre2 = new Genre("Mystery");
//        genreRepository.create(genre1);
//        genreRepository.create(genre2);
//
//        // Create publishing house
//        House publishingHouse = new House("Acme Publishing");
//        publishingHouseRepository.create(publishingHouse);
//
//        // Create books
//        Book book1 = new Book("Book 1", "Book 1 Title", "English", 300, publishingHouse);
//        Book book2 = new Book("Book 2", "Book 2 Title", "English", 400,  publishingHouse);
//        book2.addAuthor(author2);
//        book2.addGenre(genre2);
//        book1.addAuthor(author1);
//        book1.addGenre(genre1);
//        bookRepository.create(book1);
//        bookRepository.create(book2);


        // Find books by name
        String namePattern = "Book 1";
        System.out.println("Books with name containing '" + namePattern + "':");
        List<Book> books = bookRepository.findByName(namePattern);
        for (Book book : books) {
            System.out.println(book);
            System.out.println();
        }

    }
}