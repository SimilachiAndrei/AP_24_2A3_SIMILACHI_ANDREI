import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class TravelPlan {
    Trip trip;
    int timeToVisitChurch = 30;
    int timeToVisitStatue = 30;
    int timeToTravel = 30;

    TravelPlan(Trip trip) {
        this.trip = trip;
    }

    LocalDate visitAttraction(Attraction attraction) {
        LocalDate visitDate = null;
        List<Attraction> attractionList = trip.getAttractions();
        for (Attraction attractionIt : attractionList) {
            if (attractionIt.equals(attraction) && attractionIt instanceof Visitable) {
                Map<LocalDate, TimeInterval> timetable = ((Visitable) attractionIt).getTimetable();
                visitDate = timetable.keySet().stream().findFirst().orElse(null);
                break;
            }
        }
        return visitDate;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("TravelPlan{ trip=");
        for(Attraction attractionIt : trip.getAttractions())
        {
            stringBuilder.append(visitAttraction(attractionIt));
            stringBuilder.append(",");
        }
        stringBuilder.append("}");
        return stringBuilder.toString();
    }
}
