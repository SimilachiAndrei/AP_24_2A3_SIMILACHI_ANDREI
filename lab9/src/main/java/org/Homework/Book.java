package org.Homework;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "books")
@NamedQueries(
        @NamedQuery(name = "Book.findByName",
                query = "select b from Book b where b.name like :namePattern")
)
public class Book implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "id")
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    private String title;
    private String language;
    private int pages;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "book_author",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private Set<Author> authors = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "book_genre",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private Set<Genre> genres = new HashSet<>();    @ManyToOne

    @JoinColumn(name = "house_id")
    private House house;

    // Constructors, getters, and setters
    public Book(String name, String title, String language, int pages, House house) {
        this.name = name;
        this.title = title;
        this.language = language;
        this.pages = pages;
        this.house = house;
    }

    public Book() {
    }

    // Methods for managing authors and genres
    public void addAuthor(Author author) {
        authors.add(author);
    }

    public void removeAuthor(Author author) {
        authors.remove(author);
    }

    public void addGenre(Genre genre) {
        genres.add(genre);
    }

    public void removeGenre(Genre genre) {
        genres.remove(genre);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", title='" + title + '\'' +
                ", language='" + language + '\'' +
                ", pages=" + pages +
                ", authors=" + authors +
                ", genres=" + genres +
                ", house=" + house +
                '}';
    }
}