package org.Bonus;

import org.Compulsory.Person;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
      Repository repository = new Repository("/home/felix/Desktop/mdir");
      repository.readExcel("exc.xlsx");
    }
}
