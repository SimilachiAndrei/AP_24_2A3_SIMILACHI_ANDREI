/**
 * A class the extends the vehicle class and represents a drone.
 */
public class Drone extends Vehicle {
    private long duration;

    Drone(String name, long duration) {
        super(name);
        this.duration = duration;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    Drone(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "Drone{" +
                "duration=" + duration +
                ", name='" + name + '\'' +
                ", depot=" + depot +
                '}';
    }
}
