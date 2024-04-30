package org.Bonus;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ReadingList {
    private String name;
    List<Book> books = new ArrayList<>();
    Timestamp timestamp;

    ReadingList(String name) {
        this.name = name;
    }
}
