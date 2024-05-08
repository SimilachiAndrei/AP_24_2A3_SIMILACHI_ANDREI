package org.Compulsory;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Setter
@Getter
@ToString
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

    Author(String name) {
        this.name = name;
    }
}
