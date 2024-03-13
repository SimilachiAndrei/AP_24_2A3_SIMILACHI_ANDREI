import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;

public class Statue
        extends Attraction
        implements Visitable {
    private Map<LocalDate, TimeInterval> timetable;
    private double ticketPrice;

    Statue(String concertName){
        super(concertName);
    }
    @Override
    public Map<LocalDate,TimeInterval> getTimetable() {
        return timetable;
    }
    public void setTimetable(Map<LocalDate, TimeInterval> timetable) {
        this.timetable = timetable;
    }

    @Override
    public LocalTime getOpeningHour(LocalDate date) {
        return Visitable.super.getOpeningHour(date);
    }

    @Override
    public String toString() {
        return "Statue{" +
                "timetable=" + timetable +
                ", ticketPrice=" + ticketPrice +
                '}';
    }
}