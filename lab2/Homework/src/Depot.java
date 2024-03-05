import java.util.Arrays;
import java.util.Objects;

public class Depot {
    private String name;
    private Vehicle[] vehicles;

    public Depot(String name, Vehicle[] vehicles) {
        this.name = name;
        this.vehicles = vehicles;
    }

    public Depot(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Vehicle[] getVehicles() {
        return vehicles;
    }

    public void setVehicles(Vehicle... vehicles) {
        if (this.vehicles == null)
            this.vehicles = new Vehicle[0];
        for (Vehicle vehicle : vehicles) {
            if (addVehicle(vehicle))
                vehicle.setDepot(this);
        }
    }

    public boolean addVehicle(Vehicle vehicle) {
        // Check if depot is already in the array
        for (Vehicle vehicleIterator : vehicles) {
            if (vehicle.equals(vehicleIterator)) {
                System.out.println("The vehicle is already part of the class!");
                return false;
            }
        }
        //set the depot of vehicle to the current class
        vehicle.setDepot(this);
        // Create a new array with increased size
        Vehicle[] newVehicles = new Vehicle[vehicles.length + 1];

        // Copy existing depots to the new array
        for (int i = 0; i < vehicles.length; i++) {
            newVehicles[i] = vehicles[i];
        }

        // Add the new depot to the end of the array
        newVehicles[vehicles.length] = vehicle;

        // Update the reference to the new array
        vehicles = newVehicles;

        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Depot{");
        for (Vehicle vehicle : vehicles) {
            sb.append("{Vehicle : ");
            sb.append(vehicle.getName()).append(", ");
            if(vehicle instanceof Truck)
            {
                sb.append(((Truck) vehicle).getCapacity());
            }
            else if(vehicle instanceof Drone)
            {
                sb.append(((Drone) vehicle).getDuration());
            }
            sb.append("} , ");
        }
        sb.append("}");
        return sb.toString();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Depot depot = (Depot) o;
        return Objects.equals(name, depot.name) && Arrays.equals(vehicles, depot.vehicles);
    }

}
