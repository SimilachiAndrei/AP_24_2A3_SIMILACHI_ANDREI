package org.Homework;

import org.Compulsory.Person;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        List<Person> list = new ArrayList<>();
//        for(int i = 0; i < 10 ; i++)
//        {
//            list.add(new Person(i,"Person"+i));
//        }
//        Repository repo = new Repository("/home/felix/Desktop",list);
        BufferedReader stdin = new BufferedReader(
                new InputStreamReader(System.in));
        try {
            while (true) {
                String line = stdin.readLine();
                Command command;
                if(line.equals("exit") || line.isEmpty())
                {
                    break;
                }
                else if(line.contains("view"))
                {
                    try {
                        String[] parts = line.split(" ");
                        String path = parts[1].trim();
                        command = new View(path);
                        System.out.println(path);
                        command.execute();
                    }
                    catch (IOException | InvalidCommandException | InvalidDataException e)
                    {
                        System.out.println(e.getMessage());
                    }
                }
                else if(line.contains("report"))
                {
                    try {
                        String[] parts = line.split(" ");
                        String path = parts[1].trim();
                        command = new Report(new Repository(path));
                        System.out.println(path);
                        command.execute();
                    }
                    catch (IOException | InvalidCommandException | InvalidDataException e)
                    {
                        System.out.println(e.getMessage());
                    }
                }
                else if(line.contains("export"))
                {
                    try {
                        String[] parts = line.split(" ");
                        String path = parts[1].trim();
                        command = new Export(new Repository(path));
                        System.out.println(path);
                        command.execute();
                    }
                    catch (IOException | InvalidCommandException | InvalidDataException e)
                    {
                        System.out.println(e.getMessage());
                    }
                }
                else {
                    System.out.println("Invalid command");
                }
            }
        } catch (IOException e) {
            System.err.println("Eroare la intrarea standard ! ");
            e.printStackTrace();
        }
    }
}
