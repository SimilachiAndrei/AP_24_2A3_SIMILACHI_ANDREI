package org.Homework;

//import javax.xml.crypto.Data;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try {
            var book = new BookDAO();
//            DataImporter.importData("/home/felix/Desktop/books.csv");
            Book dummy = book.findByIdAsObject(5);
            System.out.println(dummy);
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        finally {
            Database.closeConnection();
        }
    }
}