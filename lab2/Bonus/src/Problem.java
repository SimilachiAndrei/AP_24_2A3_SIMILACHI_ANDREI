import java.util.Arrays;
import java.util.ArrayList;
/**
 * The class the class the represents an instance of the problem.
 */
public class Problem {
    private Depot[] depots;
    private Client[] clients;

    public Problem(Depot[] depots, Client[] clients) {
        this.depots = depots;
        this.clients = clients;
    }

    public Problem() {
    }

    public Depot[] getDepots() {
        return depots;
    }

    public void setDepots(Depot[] depots) {

        if (this.depots == null)
            this.depots = new Depot[0];
        for (Depot depot : depots) {
            addDepot(depot);
        }
    }

    public Client[] getClients() {
        return clients;
    }

    public void setClients(Client[] clients) {
        if (this.clients == null)
            this.clients = new Client[0];
        for (Client client : clients) {
            addClient(client);
        }
    }
    /**
     * Finds out all the vehicles that are found in the depots.
     *
     * @return all the vehicles found in the depots.
     */
    public Vehicle[] getVehicles() {
        ArrayList<Vehicle> allVehicles = new ArrayList<>();
        for (Depot depot : depots) {
            Vehicle[] depotVehicles = depot.getVehicles();
            for (Vehicle vehicle : depotVehicles) {
                if (vehicle instanceof Truck) {
                    Truck truck = new Truck(vehicle.getName(), ((Truck) vehicle).getCapacity());
                    allVehicles.add(truck);

                } else if (vehicle instanceof Drone) {
                    Drone drone = new Drone(vehicle.getName(), ((Drone) vehicle).getDuration());
                    allVehicles.add(drone);
                }
            }
        }
        return allVehicles.toArray(new Vehicle[0]);
    }
    /**
     * Adds a depot to the depots array if it is not already part of it.
     *
     * @param depot the depot to be added.
     */
    public void addDepot(Depot depot) {
        if (depots == null)
            depots = new Depot[0];
        // Check if depot is already in the array
        for (Depot depotIterator : depots) {
            if (depot.equals(depotIterator)) {
                System.out.println("The depot is already part of the class!");
                return;
            }
        }

        // Create a new array with increased size
        Depot[] newDepots = new Depot[depots.length + 1];

        // Copy existing depots to the new array
        for (int i = 0; i < depots.length; i++) {
            newDepots[i] = depots[i];
        }

        // Add the new depot to the end of the array
        newDepots[depots.length] = depot;

        // Update the reference to the new array
        depots = newDepots;

    }
    /**
     * Adds a client to the clients array if it is not already part of it.
     *
     * @param client the client to be added.
     */
    public void addClient(Client client) {
        if (clients == null)
            this.clients = new Client[0];
        // Check if depot is already in the array
        for (Client clientIterator : clients) {
            if (client.equals(clientIterator)) {
                System.out.println("The client is already part of the class!");
                return;
            }
        }

        // Create a new array with increased size
        Client[] newClients = new Client[clients.length + 1];

        // Copy existing depots to the new array
        for (int i = 0; i < clients.length; i++) {
            newClients[i] = clients[i];
        }

        // Add the new depot to the end of the array
        newClients[clients.length] = client;

        // Update the reference to the new array
        this.clients = newClients;

    }

    @Override
    public String toString() {
        return "Problem{" +
                "depots=" + Arrays.toString(depots) +
                ", clients=" + Arrays.toString(clients) +
                '}';
    }
}
