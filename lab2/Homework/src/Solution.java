import java.util.Arrays;
/**
 * The class that represents the solution
 */
public class Solution {
    private Problem problem;
    private final long depotToClient = (long) (Math.random() * 60);
    private final long clientToClient = (long) (Math.random() * 60);

    public Solution(Problem problem) {
        this.problem = problem;
    }

    public void setProblem(Problem problem) {
        this.problem = problem;
    }

    public Problem getProblem() {
        return problem;
    }
    /**
     *Sorts the array of the clients by the beginning time , preparing it for running the greedy algorithm.
     *
     */
    public void sort() {
        Client[] clients = problem.getClients();
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
     * Computes the solution of the problem and puts it in a string.
     *
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        sort();
        Client[] clients = problem.getClients();
        Vehicle[] vehicles = problem.getVehicles();
        boolean[] available = new boolean[vehicles.length];
        Arrays.fill(available, true);
        Client prevClient = new Client();

        for (Client client : clients) {
            for (int index = 0; index < vehicles.length; index++) {
                if (available[index]) {
                    prevClient = client;
                    stringBuilder.append(vehicles[index]).append(client).append("\n");
                    prevClient.setMinTime(prevClient.getMinTime().plusMinutes(depotToClient));
                    prevClient.setMaxTime(prevClient.getMaxTime().plusMinutes(depotToClient + clientToClient));
                    available[index] = false;
                    break; // Once a vehicle is assigned, move to the next client
                } else if (!available[index] && client.getMinTime().isAfter(prevClient.getMaxTime())) {
                    prevClient = client;
                    stringBuilder.append(vehicles[index]).append(client).append("\n");
                    prevClient.setMinTime(prevClient.getMinTime().plusMinutes(clientToClient));
                    prevClient.setMaxTime(prevClient.getMaxTime().plusMinutes(clientToClient));
                    break; // Move to the next client
                }
            }
        }

        return stringBuilder.toString();
    }

}
