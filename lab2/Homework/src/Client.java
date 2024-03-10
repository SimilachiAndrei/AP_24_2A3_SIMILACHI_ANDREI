import java.time.LocalTime;
import java.util.Objects;
/**
 * This is the class that represents a client.
 */
public class Client {
    private String name;
    private LocalTime minTime;
    private LocalTime maxTime;

    private ClientType clientType;

    public Client(String name, LocalTime minTime, LocalTime maxTime, ClientType clientType) {
        this.name = name;
        this.minTime = minTime;
        this.maxTime = maxTime;
        this.clientType = clientType;
    }

    public Client(String name, LocalTime minTime, LocalTime maxTime) {
        this.name = name;
        this.minTime = minTime;
        this.maxTime = maxTime;
    }

    public Client(String name, ClientType clientType) {
        this.name = name;
        this.clientType = clientType;
        this.minTime = LocalTime.MIDNIGHT;
        this.maxTime = LocalTime.NOON;
    }

    public Client() {
        this.minTime = LocalTime.MIDNIGHT;
        this.maxTime = LocalTime.NOON;
    }

    public Client(String name) {
        this.name = name;
    }

    public void setClientType(ClientType clientType) {
        this.clientType = clientType;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMinTime(LocalTime minTime) {
        this.minTime = minTime;
    }

    public void setMaxTime(LocalTime maxTime) {
        this.maxTime = maxTime;
    }

    public ClientType getClientType() {
        return clientType;
    }

    public String getName() {
        return name;
    }


    public LocalTime getMinTime() {
        return minTime;
    }

    public LocalTime getMaxTime() {
        return maxTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(name, client.name) && Objects.equals(minTime, client.minTime) && Objects.equals(maxTime, client.maxTime) && clientType == client.clientType;
    }


    @Override
    public String toString() {
        return "Client{" +
                "name='" + name + '\'' +
                ", minTime=" + minTime +
                ", maxTime=" + maxTime +
                ", clientType=" + clientType +
                '}';
    }
}
