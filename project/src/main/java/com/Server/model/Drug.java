package com.Server.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Drug {
    private int id;
    private String name;
    private int quantity;
    public Drug(String name , int quantity)
    {
        this.name=name;
        this.quantity=quantity;
    }
}
