package org.example;

import lombok.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class Problem {
    private List<Person> people;

    public Map<Destination, Person> getMap() {
        return people.stream()
                .collect(Collectors.toMap(
                        Person::getDestination, // Key mapper
                        person -> person,       // Value mapper
                        (existing, replacement) -> existing)); // Merge function (if duplicate keys)
    }

    public List<Destination> getDestinations() {
        return people.stream()
                .map(Person::getDestination)
                .distinct()
                .collect(Collectors.toList());
    }

    public String greedyAlgorithm() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Solution :\n");
        List<Driver> drivers = people.stream()
                .filter(person -> person instanceof Driver)
                .map(person -> (Driver) person)
                .collect(Collectors.toList());

        for (Driver driver : drivers) {
            for (Passanger passanger : people.stream().filter(p -> p instanceof Passanger).map(p -> (Passanger) p).collect(Collectors.toList())) {
                if (!driver.isTaken() && !passanger.isSeated())
                {
                    /*mergea si asa
                     if(driver.getDestination().getName().equals(passanger.getDestination().getName())||driver.getDestination().getRoad().stream()
                            .map(Destination::getName).collect(Collectors.toList()).contains(passanger.getDestination().getName()))
                     */
                    if(driver.getDestination().getName().equals(passanger.getDestination().getName())||driver.getDestination().getRoad().contains(passanger.getDestination()))
                    {
                        driver.setTaken(true);
                        passanger.setSeated(true);
                        stringBuilder.append(driver.getName()).append(" - ").append(passanger.getName()).append('\n');
                    }
                }
            }
        }
        return stringBuilder.toString();
    }

    @Override
    public String toString() {
        return greedyAlgorithm();
    }
}
