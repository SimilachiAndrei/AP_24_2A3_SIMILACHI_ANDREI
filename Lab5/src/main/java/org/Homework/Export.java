package org.Homework;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.io.IOException;

@Getter
@AllArgsConstructor
@Setter

public class Export implements Command {
    private Repository repository;

    @Override
    public void execute() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        try {
            // Convert Repository object to JSON and write to file
            mapper.writeValue(new File("repository.json"), repository);
            System.out.println("Repository exported successfully to repository.json");
        } catch (IOException e) {
            System.err.println("Error exporting repository: " + e.getMessage());
            throw e; // Re-throw the exception to be handled elsewhere if needed
        }
    }
}

