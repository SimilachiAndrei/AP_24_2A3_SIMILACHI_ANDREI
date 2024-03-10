import java.util.Arrays;

/**
 * The class that represents the solution
 */
public class Solution {
    private Problem problem;
    private int[][] matrix;
    private int rows;
    private int columns;

    public Solution(Problem problem) {
        setProblem(problem);
    }

    public Problem getProblem() {
        return problem;
    }

    public void setProblem(Problem problem) {
        this.problem = problem;
        this.rows = Math.max(problem.getVehicles().length,problem.getClients().length + problem.getDepots().length);
        this.columns = Math.max(problem.getVehicles().length,problem.getClients().length + problem.getDepots().length);
        matrix = new int[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (i > 0 && i < rows - 1 && j > 0 && j < columns - 1) {
                    // Connect to the top, bottom, left, and right nodes
                    matrix[i - 1][j] = matrix[i + 1][j] = matrix[i][j + 1] = matrix[i][j - 1] = 1 + (int) (Math.random() * 10);
                } else if (i == 0 && j > 0 && j < columns - 1) {
                    // Connect to the bottom, left, and right nodes
                    matrix[i + 1][j] = matrix[i][j + 1] = matrix[i][j - 1] = 1 + (int) (Math.random() * 10);
                } else if (i == rows - 1 && j > 0 && j < columns - 1) {
                    // Connect to the top, left, and right nodes
                    matrix[i - 1][j] = matrix[i][j + 1] = matrix[i][j - 1] = 1 + (int) (Math.random() * 10);
                } else if (j == 0 && i > 0 && i < rows - 1) {
                    // Connect to the top, bottom, and right nodes
                    matrix[i - 1][j] = matrix[i + 1][j] = matrix[i][j + 1] = 1 + (int) (Math.random() * 10);
                } else if (j == columns - 1 && i > 0 && i < rows - 1) {
                    // Connect to the top, bottom, and left nodes
                    matrix[i - 1][j] = matrix[i + 1][j] = matrix[i][j - 1] = 1 + (int) (Math.random() * 10);
                } else matrix[i][j] = Integer.MAX_VALUE;
            }
        }


    }

    public int[][] getMatrix() {
        return matrix;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        Client[] clients = problem.getClients();
        Vehicle[] vehicles = problem.getVehicles();
        int[][] distances = calculateShortestPaths();

        // Keep track of vehicle availability
        boolean[] vehicleAvailable = new boolean[vehicles.length];
        Arrays.fill(vehicleAvailable, true);

        // Allocate clients to vehicles based on shortest paths and availability
        for (int i = 0; i < clients.length; i++) {
            Client client = clients[i];

            int minDistance = Integer.MAX_VALUE;
            int assignedVehicleIndex = -1;
            for (int j = 0; j < vehicles.length; j++) {
                if (vehicleAvailable[j]) {
                    if (distances[0][i] + distances[i][0] < minDistance) {
                        minDistance = distances[0][i] + distances[i][0];
                        assignedVehicleIndex = j;
                    }
                }
            }
            if (assignedVehicleIndex != -1) {
                // Assign the vehicle to the client
                stringBuilder.append("Client ").append(i).append(" assigned to Vehicle ").append(vehicles[assignedVehicleIndex].getName()).append("\n");
                // Update vehicle availability and client-vehicle association
                vehicleAvailable[assignedVehicleIndex] = false;
            } else {
                // No available vehicle found within the client's time interval
                stringBuilder.append("Client ").append(i).append(" cannot be assigned a vehicle within the time interval ").append(client.getMinTime()).append(" - ").append(client.getMaxTime()).append("\n");
            }
        }
        return stringBuilder.toString();
    }


    private int[][] calculateShortestPaths() {
        int[][] distances = new int[rows][columns];
        // Initialize distances with matrix
        for (int i = 0; i < rows; i++) {
            System.arraycopy(matrix[i], 0, distances[i], 0, columns);
        }
        // Floyd-Warshall algorithm
        int maxNodes = Math.min(rows, columns);
        for (int k = 0; k < maxNodes; k++) {
            for (int i = 0; i < maxNodes; i++) {
                for (int j = 0; j < maxNodes; j++) {
                    if (distances[i][k] != Integer.MAX_VALUE && distances[k][j] != Integer.MAX_VALUE &&
                            distances[i][k] + distances[k][j] < distances[i][j]) {
                        distances[i][j] = distances[i][k] + distances[k][j];
                    }
                }
            }
        }
        return distances;
    }
}
