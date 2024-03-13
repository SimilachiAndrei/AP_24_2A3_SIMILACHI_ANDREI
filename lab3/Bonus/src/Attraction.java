import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public abstract class Attraction
        implements Comparable<Attraction> {
    private String name;

    protected Attraction(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return "Attraction{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Attraction that = (Attraction) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public int compareTo(Attraction other) {
        if (this instanceof Visitable && other instanceof Visitable) {
            Visitable thisVisitable = (Visitable) this;
            Visitable otherVisitable = (Visitable) other;
            Map<LocalDate, TimeInterval> map = new HashMap<>();
            map= thisVisitable.getTimetable();
            LocalDate thisOpeningDate = getFirstKey(map);
            LocalDate otherOpeningDate = getFirstKey(otherVisitable.getTimetable());
            return thisVisitable.getOpeningHour(thisOpeningDate).compareTo(otherVisitable.getOpeningHour(otherOpeningDate));
        }
        return 0; // Default comparison
    }
    private LocalDate getFirstKey(Map<LocalDate, ?> map) {
        for (Map.Entry<LocalDate, ?> entry : map.entrySet()) {
            return entry.getKey(); // Return the key of the first entry encountered
        }
        return null; // Return null if the map is empty
    }

}
