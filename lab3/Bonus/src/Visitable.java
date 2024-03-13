import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;

public interface Visitable {
    Map<LocalDate, TimeInterval> getTimetable();

    default LocalTime getOpeningHour(LocalDate date) {
        Map<LocalDate, TimeInterval> map = getTimetable();
        // Check if the map contains the given date
        if (map.containsKey(date)) {
            // Retrieve the TimeInterval for the given date
            TimeInterval timeInterval = map.get(date);
            // Return the start time of the TimeInterval
            return timeInterval.getFirst();
        }
        return null;
    }
}
