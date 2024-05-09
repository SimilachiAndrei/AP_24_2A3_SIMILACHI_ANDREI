package org.Homework;

import lombok.Getter;

@Getter

public class AuthorRepository extends AbstractRepository<Author> {
    public AuthorRepository() {
        super(Author.class);
    }

}