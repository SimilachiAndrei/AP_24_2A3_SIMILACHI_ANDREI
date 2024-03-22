package org.example;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class Driver implements Person {
    private Destination destination;
    private boolean seat;
    private String name;
    private int age;
    Driver(String name , int age)
    {
        this.name=name;
        this.age=age;
    }

    @Override
    public Destination getDestination() {
        return destination;
    }
}
