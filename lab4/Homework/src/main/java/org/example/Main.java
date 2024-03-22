package org.example;

import com.github.javafaker.Faker;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Faker faker = new Faker();

        List<Destination> destinationList1 = IntStream.rangeClosed(1, 2)
                .mapToObj(i -> new Destination(faker.address().cityName()))
                .collect(Collectors.toList());
        List<Destination> destinationList2 = IntStream.rangeClosed(3, 4)
                .mapToObj(i -> new Destination(faker.address().cityName()))
                .collect(Collectors.toList());

        List<Person> people = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            if (random.nextBoolean()) {
                people.add(new Driver(faker.name().name(), (int) (Math.random() * 30),
                        new Destination(faker.address().cityName(),destinationList1)));
            } else {
                people.add(new Passanger(faker.name().name(),
                        new Destination(destinationList1.get(1).getName())));
            }
        }
        for (int i = 0; i < 5; i++) {
            if (random.nextBoolean()) {
                people.add(new Driver(faker.name().name(), (int) (Math.random() * 30),
                        new Destination(faker.address().cityName(),destinationList2)));
            } else {
                people.add(new Passanger(faker.name().name(),
                        new Destination(destinationList2.get(1).getName())));
            }
        }
        List<Person> drivers = new LinkedList<>();
        drivers = people.stream()
                .filter(person -> person instanceof Driver)
                .collect(Collectors.toList());

        Set<Passanger> passengers = new TreeSet<>(Comparator.comparing(Passanger::getName));
        passengers = people.stream()
                .filter(person -> person instanceof Passanger)
                .map(person -> (Passanger) person)
                .collect(Collectors.toSet());

        Collections.sort(drivers,(d1,d2) -> {
            if(((Driver)d1).getAge() > ((Driver)d2).getAge())
                return 1;
            else if (((Driver)d1).getAge() == ((Driver)d2).getAge())
                return 0;
            return -1;
        }
        );

//        System.out.println("Sorted Drivers:");
//        drivers.forEach(driver -> System.out.println(((Driver)driver).getName() + ", Age: " + ((Driver) driver).getAge()));
//
//        System.out.println("\nSorted Passengers:");
//        passengers.forEach(passenger -> System.out.println(((Passanger)passenger).getName()));
        Problem problem = new Problem(people);
        System.out.println(problem);

    }
}










