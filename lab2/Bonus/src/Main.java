import java.time.LocalTime;

public class Main {
    public static void main(String[] args) {
        Problem problem = new Problem();

        Client client1 = new Client();
        client1.setName("Client 1");
        client1.setMinTime(LocalTime.of(8, 0));
        client1.setMaxTime(LocalTime.of(12, 30));
        System.out.println(client1.getName());
        Client client2 = new Client("Client 2");
        System.out.println(client2); //toString is invoked
        Client client3 = new Client("Client 3",
                LocalTime.now(), LocalTime.now().plusMinutes(10));
        Client client4 = new Client("Client 4", LocalTime.now().plusMinutes(10),LocalTime.now().plusMinutes(60), ClientType.PREMIUM);

        Vehicle vehicle = new Truck("Ferrari", 12);
        System.out.println(vehicle);
        Depot depot1 = new Depot("Depot 1");
        depot1.setVehicles(vehicle , new Drone("Lamburger", 11));
        Depot depot2 = new Depot("Depot 2");
        depot2.setVehicles(vehicle);

        problem.addClient(client3);
        problem.addClient(client4);
        problem.addDepot(depot1);
        System.out.println(problem);

//        Tour tour = new Tour();
//        tour.setClients(client3,client4);
//        tour.setVehicle(vehicle);
//        System.out.println(tour);
//        Solution solution = new Solution(problem);

        Problem problemToBeSolved = ProblemGenerator.generateRandomProblem();
        Solution solution =  new Solution(problemToBeSolved);
        System.gc();
        Runtime runtime = Runtime.getRuntime();
        long usedMemoryBefore =
                runtime.totalMemory() - runtime.freeMemory();
        long initialTime = System.currentTimeMillis();
        System.out.println(solution);
        long runningTime = System.currentTimeMillis() - initialTime;
        long usedMemoryAfter =
                runtime.totalMemory() - runtime.freeMemory();
        long memoryIncrease = usedMemoryAfter - usedMemoryBefore;
        System.out.println("Memory used after : " + usedMemoryAfter +'\n'+"Running time : "+runningTime);
    }
}