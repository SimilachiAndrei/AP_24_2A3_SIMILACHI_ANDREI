package org.example;

import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Passanger implements Person {
    private Destination destination;
    private String name;
    @Getter
    private boolean seated = false;

    public Passanger(String name) {
        this.name = name;
    }
    public Passanger(String name, Destination destination) {
        this.name = name;
        this.destination = destination;
    }

    @Override
    public Destination getDestination() {
        return destination;
    }

    @Override
    public String toString() {
        return "Passanger{" +
                "destination=" + destination +
                ", name='" + name + '\'' +
                ", seated=" + seated +
                '}';
    }
}
