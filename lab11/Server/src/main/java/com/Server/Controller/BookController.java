package com.Server.Controller;

import com.Server.Model.Book;
import com.Server.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> getBooks() {
        return bookService.getAllBooks();
    }

    @PostMapping
    public Book postBook(@RequestBody Book book) {
        return bookService.insertBook(book);
    }

    @PutMapping("/{id}")
    public Book putBook(@PathVariable Integer id, @RequestBody String name) {
        return bookService.modifyName(id, name);
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable Integer id) {
        return bookService.delete(id);
    }
}
