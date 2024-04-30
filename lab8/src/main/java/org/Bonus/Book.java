package org.Bonus;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Book {
    private int id;
    private String name;
    private String title;
    private String language;
    private int pages;
    private Author author;
    private Genre genre;

}