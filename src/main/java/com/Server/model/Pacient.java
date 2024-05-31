package com.Server.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Pacient {
    private int id;
    private String surname;
    private String firstname;

    public Pacient(String surname, String firstname) {
        this.surname = surname;
        this.firstname = firstname;
    }
}
