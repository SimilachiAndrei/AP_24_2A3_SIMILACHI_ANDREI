package org.Compulsory;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Person> list = new ArrayList<>();
        for(int i = 0; i < 10 ; i++)
        {
            list.add(new Person(i,"Person"+i));
        }
        Repository repo = new Repository("/home/felix/Desktop",list);
        repo.display();
    }
}
