import java.time.LocalTime;

public class Main {
    public static void main(String[] args) {
        Problem pb = new Problem();

        Client client1 = new Client();
        client1.setName("Client 1");
        client1.setMinTime(LocalTime.of(8, 0));
        client1.setMaxTime(LocalTime.of(12, 30));
        System.out.println(client1.getName());
        Client client2 = new Client("Client 2");
        System.out.println(client2); //toString is invoked
        Client client3 = new Client("Client 3",
                LocalTime.NOON, LocalTime.MIDNIGHT);
        Client client4 = new Client("Client 4", ClientType.PREMIUM);

        Vehicle vehicle = new Vehicle("Ferrari");
        System.out.println(vehicle);
        Depot depot1 = new Depot("Depot 1");
        depot1.setVehicles(vehicle , vehicle);
        Depot depot2 = new Depot("Depot 2");
        depot2.setVehicles(vehicle);

        System.out.println(pb);
    }
}