package org.example;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class Driver implements Person {
    private Destination destination;
    private boolean taken = false;
    private String name;
    private int age;
    private Passenger passenger;

    public Driver(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Driver(String name, int age, Destination destination) {
        this.name = name;
        this.age = age;
        this.destination = destination;
    }

    @Override
    public Destination getDestination() {
        return destination;
    }

    @Override
    public String toString() {
        return "Driver{" +
                "destination=" + destination +
                ", seat=" + taken +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
