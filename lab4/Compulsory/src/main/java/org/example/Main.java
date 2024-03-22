package org.example;

import java.util.*;
import java.util.stream.Collectors;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        // Step 4: Create a random group of persons
        List<Person> people = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            if (random.nextBoolean()) {
                people.add(new Driver("Driver" + i, (int) (Math.random() * 30)));
            } else {
                people.add(new Passanger("Passenger" + i));
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

        System.out.println("Sorted Drivers:");
        drivers.forEach(driver -> System.out.println(((Driver)driver).getName() + ", Age: " + ((Driver) driver).getAge()));

        System.out.println("\nSorted Passengers:");
        passengers.forEach(passenger -> System.out.println(((Passanger)passenger).getName()));
    }
}










