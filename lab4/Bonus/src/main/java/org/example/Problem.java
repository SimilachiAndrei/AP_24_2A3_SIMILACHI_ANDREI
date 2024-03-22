package org.example;

import lombok.*;
import org.jgrapht.Graph;
import org.jgrapht.alg.interfaces.MatchingAlgorithm;
import org.jgrapht.alg.matching.SparseEdmondsMaximumCardinalityMatching;
import org.jgrapht.graph.DefaultEdge;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
    public String bipartiteAlgorithm(Graph<Person, DefaultEdge> graph) {
        MatchingAlgorithm.Matching<Person, DefaultEdge> matching = MaxCardinalityMatching.findMaxCardinalityMatching(graph);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Bipartite Graph Solution:\n");
        for (DefaultEdge edge : matching.getEdges()) {
            Person driver = graph.getEdgeSource(edge);
            Person passenger = graph.getEdgeTarget(edge);
            if (driver.getDestination().equals(passenger.getDestination())||driver.getDestination().getRoad().contains(passenger.getDestination())) {
                stringBuilder.append(((Driver)driver).getName()).append(" - ").append(((Passenger)passenger).getName()).append('\n');
            }
        }
        return stringBuilder.toString();
    }

    public String greedyAlgorithm() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Solution :\n");
        List<Driver> drivers = people.stream()
                .filter(person -> person instanceof Driver)
                .map(person -> (Driver) person)
                .collect(Collectors.toList());

        for (Driver driver : drivers) {
            for (Passenger passenger : people.stream().filter(p -> p instanceof Passenger).map(p -> (Passenger) p).collect(Collectors.toList())) {
                if (!driver.isTaken() && !passenger.isSeated())
                {
                    /*mergea si asa
                     if(driver.getDestination().getName().equals(passenger.getDestination().getName())||driver.getDestination().getRoad().stream()
                            .map(Destination::getName).collect(Collectors.toList()).contains(passenger.getDestination().getName()))
                     */
                    if(driver.getDestination().getName().equals(passenger.getDestination().getName())||driver.getDestination().getRoad().contains(passenger.getDestination()))
                    {
                        driver.setTaken(true);
                        passenger.setSeated(true);
                        stringBuilder.append(driver.getName()).append(" - ").append(passenger.getName()).append('\n');
                    }
                }
            }
        }
        return stringBuilder.toString();
    }
    public static Set<Person> findMaximumCardinalitySet(Graph<Person, DefaultEdge> graph) {
        SparseEdmondsMaximumCardinalityMatching<Person, DefaultEdge> matching = new SparseEdmondsMaximumCardinalityMatching<>(graph);
        Set<DefaultEdge> matchingEdges = matching.getMatching().getEdges();

        Set<Person> maximumCardinalitySet = new HashSet<>();
        for (DefaultEdge edge : matchingEdges) {
            Person driver = graph.getEdgeSource(edge);
            Person passenger = graph.getEdgeTarget(edge);
            if (!driver.getDestination().equals(passenger.getDestination())) {
                maximumCardinalitySet.add(driver);
                maximumCardinalitySet.add(passenger);
            }
        }
        return maximumCardinalitySet;
    }
    @Override
    public String toString() {
        return greedyAlgorithm();
    }
}
