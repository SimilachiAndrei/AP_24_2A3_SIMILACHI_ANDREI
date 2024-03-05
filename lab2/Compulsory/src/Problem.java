import java.util.Arrays;

public class Problem {
    private Vehicle vehicle;
    private Client[] clients;

    public Problem(Vehicle vehicle, Client[] clients) {
        this.vehicle = vehicle;
        this.clients = clients;
    }
    public Problem(){}

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Client[] getClients() {
        return clients;
    }

    public void setClients(Client[] clients) {
        this.clients = clients;
    }

    @Override
    public String toString() {
        return "Problem{" +
                "vehicle=" + vehicle +
                ", clients=" + Arrays.toString(clients) +
                '}';
    }
}
