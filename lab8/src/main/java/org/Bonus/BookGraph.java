package org.Bonus;

import org.jgrapht.Graph;
import org.jgrapht.alg.color.GreedyColoring;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BookGraph {
    private Graph<Book, DefaultEdge> graph = new SimpleGraph<>(DefaultEdge.class);

    public void addAllBooks(BookDAO bookDAO) throws SQLException {
        List<Book> books = bookDAO.findAll();
        for (Book book : books) {
            graph.addVertex(book);
        }
    }

    public void addEdgesBetweenRelatedBooks() {
        for (Book book1 : graph.vertexSet()) {
            for (Book book2 : graph.vertexSet()) {
                if (book1 != book2 && !areRelated(book1, book2)) {
                    graph.addEdge(book1, book2);
                }
            }
        }
    }

    private boolean areRelated(Book book1, Book book2) {
        // Check if the books are related based on genre
        return book1.getGenre().equals(book2.getGenre());
    }

    public List<ReadingList> getReadingListsFromGreedyColoring() {
        GreedyColoring<Book, DefaultEdge> coloring = new GreedyColoring<>(graph);
        Map<Book, Integer> colorMap = coloring.getColoring().getColors();

        List<ReadingList> readingLists = new ArrayList<>();

        // Create a reading list for each color
        int maxColor = colorMap.values().stream().mapToInt(Integer::intValue).max().orElse(0);
        for (int color = 0; color <= maxColor; color++) {
            ReadingList readingList = new ReadingList("Reading List " + (color + 1));
            readingLists.add(readingList);
        }

        // Add books to their respective reading lists based on color
        for (Map.Entry<Book, Integer> entry : colorMap.entrySet()) {
            Book book = entry.getKey();
            int color = entry.getValue();
            readingLists.get(color).getBooks().add(book);
        }

        return readingLists;
    }
}