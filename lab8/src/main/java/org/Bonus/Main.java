package org.Bonus;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            BookDAO bookDAO = new BookDAO();
            BookGraph bookGraph = new BookGraph();
            bookGraph.addAllBooks(bookDAO);
            bookGraph.addEdgesBetweenRelatedBooks();
            List<ReadingList> readingLists = bookGraph.getReadingListsFromGreedyColoring();

            // Print the reading lists
            for (ReadingList readingList : readingLists) {
                System.out.println("Reading List: " + readingList.getName());
                for (Book book : readingList.getBooks()) {
                    System.out.println("- " + book.getName());
                }
                System.out.println();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            Database.closeConnection();
        }
    }
}