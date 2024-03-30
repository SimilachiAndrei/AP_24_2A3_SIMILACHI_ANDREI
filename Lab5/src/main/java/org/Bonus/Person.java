package org.Bonus;

import java.util.Collection;
import java.util.List;

public record Person (int id, String name, Collection<String>  abilities) {
    public Collection<String> getAbilities() {
        return  abilities;
    }

    public String getName() {
        return name;
    }

    public Person setAbilities(List<String> personAbilities) {
        return new Person(id, name, personAbilities);
    }

}
