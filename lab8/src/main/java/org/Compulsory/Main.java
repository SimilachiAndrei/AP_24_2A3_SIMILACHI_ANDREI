package org.Compulsory;


import java.sql.SQLException;

public class Main {
    public static void main(String args[]) {
        try {
            var authors = new AuthorDAO();
            System.out.println(authors.findById(4));
            Database.closeConnection();
        } catch (SQLException e) {
            System.err.println(e);
        }
    }
}