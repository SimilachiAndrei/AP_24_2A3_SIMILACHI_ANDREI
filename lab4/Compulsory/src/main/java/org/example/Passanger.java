package org.example;

import lombok.*;

import java.util.Objects;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class Passanger implements Person {
    private Destination destination;
    private String name;
    private boolean isSeated;

    Passanger(String name) {
        this.name = name;
    }

    @Override
    public Destination getDestination() {
        return destination;
    }

    Passanger passanger;
}
