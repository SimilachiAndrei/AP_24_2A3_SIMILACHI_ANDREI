package org.Compulsory;

import lombok.AllArgsConstructor;
import java.io.File;
import java.util.List;

@AllArgsConstructor
public class Repository {
    private String directory;
    private List<Person> personList;
    public void display() {
        try {
            File directoryFile = new File(directory);

            if (!directoryFile.exists() || !directoryFile.isDirectory()) {
                System.out.println("Invalid directory path or directory does not exist.");
                return;
            }

            File[] files = directoryFile.listFiles();

            if (files != null) {
                for (File file : files) {
                    System.out.println(file.getName());
                }
            } else {
                System.out.println("No files found in the directory.");
            }
        } catch (NullPointerException e) {
            System.out.println("Null pointer exception: " + e.getMessage());
        }
    }
}
