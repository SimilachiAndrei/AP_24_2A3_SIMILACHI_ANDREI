package org.example;

import com.github.javafaker.Faker;
import org.jgrapht.Graph;
import org.jgrapht.alg.interfaces.MatchingAlgorithm;
import org.jgrapht.graph.DefaultEdge;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.example.MaxCardinalityMatching.findMaxCardinalityMatching;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //Faker faker = new Faker();

//        List<Destination> destinationList1 = IntStream.rangeClosed(1, 2)
//                .mapToObj(i -> new Destination(faker.address().cityName()))
//                .collect(Collectors.toList());
//        List<Destination> destinationList2 = IntStream.rangeClosed(3, 4)
//                .mapToObj(i -> new Destination(faker.address().cityName()))
//                .collect(Collectors.toList());
//
//        List<Person> people = new ArrayList<>();
//        Random random = new Random();
//        for (int i = 0; i < 5; i++) {
//            if (random.nextBoolean()) {
//                people.add(new Driver(faker.name().name(), (int) (Math.random() * 30),
//                        new Destination(faker.address().cityName(),destinationList1)));
//            } else {
//                people.add(new Passenger(faker.name().name(),
//                        new Destination(destinationList1.get(1).getName())));
//            }
//        }
//        for (int i = 0; i < 5; i++) {
//            if (random.nextBoolean()) {
//                people.add(new Driver(faker.name().name(), (int) (Math.random() * 30),
//                        new Destination(faker.address().cityName(),destinationList2)));
//            } else {
//                people.add(new Passenger(faker.name().name(),
//                        new Destination(destinationList2.get(1).getName())));
//            }
//        }
//        List<Person> drivers = new LinkedList<>();
//        drivers = people.stream()
//                .filter(person -> person instanceof Driver)
//                .collect(Collectors.toList());
//
//        Set<Passenger> passengers = new TreeSet<>(Comparator.comparing(Passenger::getName));
//        passengers = people.stream()
//                .filter(person -> person instanceof Passenger)
//                .map(person -> (Passenger) person)
//                .collect(Collectors.toSet());
//
//        Collections.sort(drivers,(d1,d2) -> {
//            if(((Driver)d1).getAge() > ((Driver)d2).getAge())
//                return 1;
//            else if (((Driver)d1).getAge() == ((Driver)d2).getAge())
//                return 0;
//            return -1;
//        }
//        );

//        System.out.println("Sorted Drivers:");
//        drivers.forEach(driver -> System.out.println(((Driver)driver).getName() + ", Age: " + ((Driver) driver).getAge()));
//
//        System.out.println("\nSorted Passengers:");
//        passengers.forEach(passenger -> System.out.println(((Passenger)passenger).getName()));
        Destination destination = new Destination("Galati");
        List<Person> people = new ArrayList<>();
        for (int i = 0; i < 5000; i++) {
            people.add(new Driver("Driver" + i, i,destination));
        }

        for (int i = 0; i < 5000; i++) {
            people.add(new Passenger("Passenger" + i,destination));
        }
        Problem problem = new Problem(people);
        //System.out.println(problem);
        Graph<Person, DefaultEdge> graph = BipartiteGraphGenerator.generateBipartiteGraph(5000, 5000, 0.1);
        //System.out.println(problem.bipartiteAlgorithm(graph));
        //System.out.println("\nGreedy\n" + problem.greedyAlgorithm());
        Set<Person> maximumCardinalitySet = problem.findMaximumCardinalitySet(graph);

        System.out.println("Maximum Cardinality Set:");
        for (Person person : maximumCardinalitySet) {
            System.out.println(person);
        }
    }
}