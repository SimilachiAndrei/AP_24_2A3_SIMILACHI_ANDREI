import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;

public interface Visitable {
    Map<LocalDate, TimeInterval> getTimetable();
    default LocalTime getOpeningHour(LocalDate date) {
        return LocalTime.of(9, 0); // Opening hour at 9:00 AM
    }

}
