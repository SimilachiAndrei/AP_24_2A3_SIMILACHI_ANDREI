package org.Homework;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "authors")
@NamedQueries({
        @NamedQuery(name = "Author.findAll",
                query = "select e from Author e order by e.name"),
        @NamedQuery(name = "Author.findByName",
                query = "SELECT a FROM Author a WHERE a.name LIKE :namePattern")
})
public class Author implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "id")
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "authors", fetch = FetchType.EAGER)
    private Set<Book> books = new HashSet<>();

    public Author(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Author{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}