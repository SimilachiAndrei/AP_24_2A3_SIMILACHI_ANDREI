package org.Homework;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
public class Repository {
    private String directory;
    private List<Person> personList;
    Repository(String directory)
    {
        this.directory=directory;
    }

    public List<String> getFileNames() {
        List<String> fileNames = new ArrayList<>();
        try {
            File directoryFile = new File(directory);

            if (!directoryFile.exists() || !directoryFile.isDirectory()) {
                System.out.println("Invalid directory path or directory does not exist.");
                return fileNames;
            }

            File[] files = directoryFile.listFiles();

            if (files != null) {
                for (File file : files) {
                    fileNames.add(file.getName());
                }
            } else {
                System.out.println("No files found in the directory.");
            }
        } catch (NullPointerException e) {
            System.out.println("Null pointer exception: " + e.getMessage());
        }
        return fileNames;
    }
}
