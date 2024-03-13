import java.time.LocalDate;
import java.util.Map;

public class Church
        extends Attraction
        implements Visitable {
    private Map<LocalDate, TimeInterval> timetable;
    private double ticketPrice;

    Church(String churchName){
        super(churchName);
    }
    @Override
    public Map<LocalDate,TimeInterval> getTimetable() {
        return timetable;
    }
    public void setTimetable(Map<LocalDate, TimeInterval> timetable) {
        this.timetable = timetable;
    }

    @Override
    public String toString() {
        return "Church{" +
                "timetable=" + timetable +
                ", ticketPrice=" + ticketPrice +
                '}';
    }
}