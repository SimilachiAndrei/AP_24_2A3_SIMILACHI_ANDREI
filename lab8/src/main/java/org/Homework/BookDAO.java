package org.Homework;

import java.sql.*;

public class BookDAO {
    public void create(String name, String title, String language, int pages, int auth_id, int genre_id) throws SQLException {
        try (Connection con = Database.getConnection();
             PreparedStatement pstmt = con.prepareStatement(
                     "INSERT INTO books (name, title, language, pages, auth_id, genre_id) VALUES (?,?,?,?,?,?)")) {
            pstmt.setString(1, name);
            pstmt.setString(2, title);
            pstmt.setString(3, language);
            pstmt.setInt(4, pages);
            pstmt.setInt(5, auth_id);
            pstmt.setInt(6, genre_id);
            pstmt.executeUpdate();
            con.commit();
        }
    }

    public void createByObject(Book book) throws SQLException {
        try (Connection con = Database.getConnection();
             PreparedStatement pstmt = con.prepareStatement(
                     "INSERT INTO books (name, title, language, pages, auth_id, genre_id) VALUES (?,?,?,?,?,?)")) {
            pstmt.setString(1, book.getName());
            pstmt.setString(2, book.getTitle());
            pstmt.setString(3, book.getLanguage());
            pstmt.setInt(4, book.getPages());

            if (book.getAuthor() != null) {
                pstmt.setInt(5, book.getAuthor().getId());
            } else {
                pstmt.setNull(5, Types.INTEGER);
            }

            if (book.getGenre() != null) {
                pstmt.setInt(6, book.getGenre().getId());
            } else {
                pstmt.setNull(6, Types.INTEGER);
            }

            pstmt.executeUpdate();
            con.commit();
        }
    }

    public Book findByIdAsObject(int id) throws SQLException {
        try (Connection con = Database.getConnection();
             PreparedStatement pstmt = con.prepareStatement(
                     "SELECT * FROM books WHERE id=?")) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Book book = new Book();
                    book.setId(rs.getInt("id"));
                    book.setName(rs.getString("name"));
                    book.setTitle(rs.getString("title"));
                    book.setLanguage(rs.getString("language"));
                    book.setPages(rs.getInt("pages"));

                    int authorId = rs.getInt("auth_id");
                    Author author = new AuthorDAO().findByIdAsObject(authorId);
                    book.setAuthor(author);

                    int genreId = rs.getInt("genre_id");
                    Genre genre = new GenreDAO().findByIdAsObject(genreId);
                    book.setGenre(genre);

                    return book;
                }
                return null;
            }
        }
    }

}
