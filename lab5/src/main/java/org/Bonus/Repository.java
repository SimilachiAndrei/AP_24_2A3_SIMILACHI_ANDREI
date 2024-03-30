package org.Bonus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

@AllArgsConstructor
@Getter
public class Repository {
    private String directory;
    private List<Person> personList;

    Repository(String directory) {
        this.directory = directory;
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

    public void readExcel(String fileName) {
        try {
            int id = 0;
            FileInputStream file = new FileInputStream(new File(directory + File.separator + fileName));
            Workbook workbook = new XSSFWorkbook(file);

            // Assuming there's only one sheet in the Excel file
            Sheet sheet = workbook.getSheetAt(0);

            // Step 2: Parse Excel data and store persons with their abilities
            Map<String, Person> persons = new HashMap<>();
            for (Row row : sheet) {
                String personName = row.getCell(0).getStringCellValue();
                String[] abilities = row.getCell(1).getStringCellValue().split(",\\s*");

                if (!persons.containsKey(personName)) {
                    Person person = new Person(id++, personName, Arrays.stream(abilities).toList());
                    persons.put(personName, person);
                }

                List<String> personAbilities = new ArrayList<>(Arrays.asList(abilities));
                persons.get(personName).setAbilities(personAbilities);
            }

            // Step 3: Determine maximal groups of people with at least one common ability
            List<Set<Person>> maximalGroups = findMaximalGroups(persons);

            // Step 4: Display the groups
            for (Set<Person> group : maximalGroups) {
                // Check if the group has more than one member or if the single member has common abilities
                if (group.size() > 1 || !group.iterator().next().getAbilities().isEmpty()) {
                    System.out.println("Group:");
                    for (Person person : group) {
                        System.out.println(person.getName());
                    }
                    System.out.println();
                }
            }


            workbook.close();
        } catch (IOException e) {
            System.out.println("Error reading Excel file: " + e.getMessage());
        }
    }

    private List<Set<Person>> findMaximalGroups(Map<String, Person> persons) {
        List<Set<Person>> maximalGroups = new ArrayList<>();
        Set<Person> remainingPersons = new HashSet<>(persons.values());

        while (!remainingPersons.isEmpty()) {
            Person startPerson = remainingPersons.iterator().next();
            Set<Person> group = new HashSet<>();
            Queue<Person> queue = new LinkedList<>();
            queue.add(startPerson);

            while (!queue.isEmpty()) {
                Person currentPerson = queue.poll();
                group.add(currentPerson);
                remainingPersons.remove(currentPerson);

                for (Person person : remainingPersons) {
                    if (haveCommonAbility(currentPerson, person)) {
                        queue.add(person);
                    }
                }
            }
            maximalGroups.add(group);
        }

        return maximalGroups;
    }

    private boolean haveCommonAbility(Person person1, Person person2) {
        for (String ability : person1.getAbilities()) {
            if (person2.getAbilities().contains(ability)) {
                return true;
            }
        }
        return false;
    }
}
