package org.Homework;

import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

public class DataImporter {
    private static final int maxToImport = 10;

    public static void importData(String filePath) {
        int counter = 0;
        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            String[] line;
            boolean isFirstRow = true; // Flag to skip the first row
            while ((line = reader.readNext()) != null) {
                if (isFirstRow) {
                    isFirstRow = false;
                    continue; // Skip the header row
                }

                if (counter >= maxToImport) {
                    break; // Exit the loop if the counter reaches the maximum limit
                }
                // Process each line
                AuthorDAO authorDAO = new AuthorDAO();
                GenreDAO genreDAO = new GenreDAO();
                BookDAO bookDAO = new BookDAO();
                Book book = new Book();
                book.setLanguage("EN");
                String authorName = null;
                String genreName = "generic";
                for (int i = 0; i < line.length; i++) {
                    String cell = line[i];
                    if (i == 0) {
                        book.setId(Integer.parseInt(cell));
                    } else if (i == 1) {
                        book.setTitle(cell);
                        book.setName(cell);
                    } else if (i == 2) {
                        authorName = cell;
                    } else if (i == 7) {
                        book.setPages(Integer.parseInt(cell));
                    }
                    System.out.print(cell + "\t");
                }
                // Create or retrieve the author
                Author author = authorDAO.findLastByName(authorName);
                if (author == null) {
                    author = new Author();
                    author.setName(authorName);
                    authorDAO.createByObject(author);
                    author = authorDAO.findLastByName(authorName);
                }
                book.setAuthor(author);

                // Create or retrieve the genre
                Genre genre = genreDAO.findLastByName(genreName);
                if (genre == null) {
                    genre = new Genre();
                    genre.setName(genreName);
                    genreDAO.createByObject(genre);
                    genre = genreDAO.findLastByName(genreName);
                }
                book.setGenre(genre);

                bookDAO.createByObject(book);
                System.out.println();
                counter++;
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}