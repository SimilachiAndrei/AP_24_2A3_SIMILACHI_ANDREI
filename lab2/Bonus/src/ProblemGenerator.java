import java.util.Random;

public class ProblemGenerator {
    private static final int MAX_DEPOTS = 5; // Maximum number of depots
    private static final int MAX_CLIENTS = 20; // Maximum number of clients
    private static final int MAX_VEHICLES_PER_DEPOT = 3; // Maximum number of vehicles per depot

    public static Problem generateRandomProblem() {
        Random random = new Random();

        // Generate random number of depots and clients
        int numDepots = random.nextInt(MAX_DEPOTS) + 1; // Ensure at least one depot
        int numClients = random.nextInt(MAX_CLIENTS) + 1; // Ensure at least one client

        Depot[] depots = new Depot[numDepots];
        Client[] clients = new Client[numClients];

        // Generate depots
        for (int i = 0; i < numDepots; i++) {
            depots[i] = generateRandomDepot(random);
        }

        // Generate clients
        for (int i = 0; i < numClients; i++) {
            clients[i] = generateRandomClient(random);
        }

        return new Problem(depots, clients);
    }

    private static Depot generateRandomDepot(Random random) {
        int numVehicles = random.nextInt(MAX_VEHICLES_PER_DEPOT) + 1; // Ensure at least one vehicle

        Vehicle[] vehicles = new Vehicle[numVehicles];
        for (int i = 0; i < numVehicles; i++) {
            // Generate either a Truck or a Drone with random properties
            if (random.nextBoolean()) {
                vehicles[i] = new Truck("Truck " + (i + 1), random.nextInt(100) + 1); // Random capacity between 1 and 100
            } else {
                vehicles[i] = new Drone("Drone " + (i + 1), random.nextInt(100) + 1); // Random duration between 1 and 100
            }
        }

        return new Depot("Depot " + (random.nextInt(100) + 1), vehicles); // Random depot name
    }

    private static Client generateRandomClient(Random random) {
        // Generate a random client with a random demand
        return new Client("Client " + (random.nextInt(100) + 1)); // Random demand between 1 and 10
    }
}

