/**
 * The class that represents a tour of a vehicle.
 */
public class Tour {
    private Vehicle vehicle;
    private Client[] clients;
    private final long depotToClient = (long) (Math.random() * 60);
    private final long clientToClient = (long) (Math.random() * 60);
    public Tour(Vehicle vehicle, Client[] clients) {
        this.vehicle = vehicle;
        this.clients = clients;
    }
    Tour(){}

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Client[] getClients() {
        return clients;
    }

    public void setClients(Client ... clients) {
        this.clients = clients;
    }
    public void sort()
    {
        for (int i = 0; i < clients.length - 1; i++) {
            for (int j = i + 1; j < clients.length; j++) {
                if (clients[i].getMinTime().isAfter(clients[j].getMinTime())) {
                    Client temp = clients[i];
                    clients[i] = clients[j];
                    clients[j] = temp;
                }
            }
        }
    }
    /**
     * Computes the tour and puts it in a string.
     *
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        sort();
        Client prevClient = null;
        for (Client client : clients) {
           if(prevClient == null)
           {
               prevClient = client;
               stringBuilder.append(vehicle).append(client);
               prevClient.getMinTime().plusMinutes(depotToClient );
               prevClient.getMaxTime().plusMinutes(depotToClient + clientToClient);
           }
           else if (client.getMinTime().isAfter(prevClient.getMaxTime()))
           {
               stringBuilder.append("\n");
               prevClient = client;
               prevClient.getMaxTime().plusMinutes(clientToClient);
               stringBuilder.append(vehicle).append(client);
           }
        }
        return stringBuilder.toString();
    }

}
