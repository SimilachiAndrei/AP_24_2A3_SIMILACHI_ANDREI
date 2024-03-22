package org.example;

import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Passenger implements Person {
    private Destination destination;
    private String name;
    @Getter
    private boolean seated = false;

    public Passenger(String name) {
        this.name = name;
    }
    public Passenger(String name, Destination destination) {
        this.name = name;
        this.destination = destination;
    }

    @Override
    public Destination getDestination() {
        return destination;
    }

    @Override
    public String toString() {
        return "Passenger{" +
                "destination=" + destination +
                ", name='" + name + '\'' +
                ", seated=" + seated +
                '}';
    }
}
