package org.example;
import lombok.*;

import java.util.List;
import java.util.Objects;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter

public class Destination {
    private String name;
    private List<Destination> road;
    public Destination(String name){this.name = name;}
    public void addToList(Destination destination)
    {
        road.add(destination);
    }
    @Override
    public String toString() {
        return "Destination{" +
                "name='" + name + '\'' +
                ", road=" + road +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Destination that = (Destination) o;
        return Objects.equals(name, that.name) && Objects.equals(road, that.road);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, road);
    }
}
