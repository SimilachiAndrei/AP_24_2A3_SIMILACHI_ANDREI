import java.time.LocalDate;
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
//...
}