package com.Server.Service;

import com.Server.Model.Author;
import com.Server.Model.Book;
import com.Server.Model.Genre;
import com.Server.Repository.AuthorRepository;
import com.Server.Repository.BookRepository;
import com.Server.Repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Logger;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;
    private static final Logger logger = Logger.getLogger(BookService.class.getName());

    @Autowired
    public BookService(BookRepository bookRepository, AuthorRepository authorRepository, GenreRepository genreRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.genreRepository = genreRepository;
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book insertBook(Book book) {
        try {
            // Ensure authors exist or create new ones
            Set<Author> authors = new HashSet<>();
            for (Author author : book.getAuthors()) {
                if (author.getId() != null) {
                    Optional<Author> existingAuthor = authorRepository.findById(author.getId());
                    if (existingAuthor.isPresent()) {
                        authors.add(existingAuthor.get());
                    } else {
                        logger.warning("Author not found: " + author.getId());
                        authors.add(authorRepository.save(author));
                    }
                } else {
                    authors.add(authorRepository.save(author));
                }
            }

            // Ensure genres exist or create new ones
            Set<Genre> genres = new HashSet<>();
            for (Genre genre : book.getGenres()) {
                if (genre.getId() != null) {
                    Optional<Genre> existingGenre = genreRepository.findById(genre.getId());
                    if (existingGenre.isPresent()) {
                        genres.add(existingGenre.get());
                    } else {
                        logger.warning("Genre not found: " + genre.getId());
                        genres.add(genreRepository.save(genre));
                    }
                } else {
                    genres.add(genreRepository.save(genre));
                }
            }

            // Set the fetched or created authors and genres to the book
            book.setAuthors(authors);
            book.setGenres(genres);

            return bookRepository.save(book);
        } catch (Exception e) {
            logger.severe("Error inserting book: " + e.getMessage());
            throw e;
        }
    }

    public Book modifyName(Integer id ,String name) {
        Optional<Book> book = bookRepository.findById(id);
        if (!book.isPresent()) {
            throw new RuntimeException("Book not found!");
        }
        Book modifiedBook = book.get();

        // Update book details
        modifiedBook.setName(name);
        return  bookRepository.save(modifiedBook);
    }

    public String delete(Integer id) {
        Optional<Book> book = bookRepository.findById(id);
        if (!book.isPresent()) {
            return "Book not found!";
        }
        Book deletedBook = book.get();

        // Update book details
        bookRepository.delete(deletedBook);
        return "Deleted";
    }
}
