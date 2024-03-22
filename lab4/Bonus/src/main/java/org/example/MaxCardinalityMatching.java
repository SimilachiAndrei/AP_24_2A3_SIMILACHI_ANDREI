package org.example;

import org.jgrapht.Graph;
import org.jgrapht.alg.interfaces.MatchingAlgorithm;
import org.jgrapht.alg.matching.SparseEdmondsMaximumCardinalityMatching;
import org.jgrapht.graph.DefaultEdge;

public class MaxCardinalityMatching {
    public static MatchingAlgorithm.Matching<Person, DefaultEdge> findMaxCardinalityMatching(Graph<Person, DefaultEdge> graph) {
        // Use the Edmonds-Karp algorithm to find the maximum cardinality matching
        SparseEdmondsMaximumCardinalityMatching<Person, DefaultEdge> matcher =
                new SparseEdmondsMaximumCardinalityMatching<>(graph);
        return matcher.getMatching();
    }
}
