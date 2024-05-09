package org.Homework;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "houses")
@NamedQueries(
        @NamedQuery(name = "House.findByName",
                query = "select b from House b where b.name like :namePattern")
)
public class House implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "house", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Book> books = new ArrayList<>();

    public House() {
    }

    // Constructors, getters, and setters
    public House(String name) {
        this.name = name;
    }

    public void addBook(Book book) {
        books.add(book);
        book.setHouse(this);
    }

    public void removeBook(Book book) {
        books.remove(book);
        book.setHouse(null);
    }

    @Override
    public String toString() {
        return "House{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}