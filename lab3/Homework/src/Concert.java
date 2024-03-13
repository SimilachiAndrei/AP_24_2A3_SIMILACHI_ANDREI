import java.time.LocalDate;
import java.util.Map;

public class Concert
        extends Attraction
        implements Visitable, Payable {
    private Map<LocalDate, TimeInterval> timetable;
    private double ticketPrice;

    Concert(String concertName){
        super(concertName);
    }
    @Override
    public Map<LocalDate,TimeInterval> getTimetable() {
        return timetable;
    }
    public void setTimetable(Map<LocalDate, TimeInterval> timetable) {
        this.timetable = timetable;
    }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    @Override
    public double getTicketPrice() {
        return ticketPrice;
    }

    @Override
    public String toString() {
        return "Concert{" +
                "timetable=" + timetable +
                ", ticketPrice=" + ticketPrice +
                '}';
    }
    //...
}