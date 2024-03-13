import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Concert concert = new Concert("Maraton");
        TimeInterval timeInterval = new TimeInterval(LocalTime.of(9, 20), LocalTime.MAX);
        Map<LocalDate, TimeInterval> timetable = new HashMap<>();
        timetable.put(LocalDate.now(), timeInterval);
        concert.setTimetable(timetable);
        concert.setTicketPrice(20);
        System.out.println(concert);
        Church church = new Church("Biserica Cozmina");
        church.setTimetable(timetable);
        System.out.println(church);
        List<Attraction> lista = new ArrayList<>();
        lista.add(church);
        lista.add(concert);
        Trip trip = new Trip("Galati",LocalDate.of(2022, Month.AUGUST,
                22),LocalDate.now(),lista);
        System.out.println((trip));
    }
}
