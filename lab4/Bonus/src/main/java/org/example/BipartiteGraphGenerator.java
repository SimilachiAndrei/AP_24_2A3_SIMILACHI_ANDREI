package org.example;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import java.util.Random;

public class BipartiteGraphGenerator {
    public static Graph<Person, DefaultEdge> generateBipartiteGraph(int numDrivers, int numPassengers, double edgeProbability) {
        Graph<Person, DefaultEdge> graph = new SimpleGraph<>(DefaultEdge.class);
        Random random = new Random();
        Destination destination = new Destination("Galati");
        // Add driver vertices
        for (int i = 0; i < numDrivers; i++) {
            graph.addVertex(new Driver("Driver" + i, i,destination));
        }

        // Add passenger vertices
        for (int i = 0; i < numPassengers; i++) {
            graph.addVertex(new Passenger("Passenger" + i,destination));
        }

        // Add edges with probability
        for (Person driver : graph.vertexSet()) {
            for (Person passenger : graph.vertexSet()) {
                if (driver instanceof Driver && passenger instanceof Passenger && random.nextDouble() < edgeProbability) {
                    graph.addEdge(driver, passenger);
                }
            }
        }

        return graph;
    }
}
