package org.Homework;

import lombok.Getter;

@Getter

public class GenreRepository extends AbstractRepository<Genre>{
    public GenreRepository() {
        super(Genre.class);
    }
}
