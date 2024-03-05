public class Truck extends Vehicle {
    private int capacity;

    Truck(String name) {
        super(name);
    }

    Truck(String name, int capacity) {
        super(name);
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return "Truck{" +
                "capacity=" + capacity +
                ", name='" + name + '\'' +
                ", depot=" + depot +
                '}';
    }
}
