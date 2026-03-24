import java.util.*;

class Service {
    private String name;
    private double cost;

    public Service(String name, double cost) {
        this.name = name;
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public double getCost() {
        return cost;
    }
}

class AddOnServiceManager {
    private Map<String, List<Service>> map = new HashMap<>();

    public void addService(String reservationId, Service service) {
        map.computeIfAbsent(reservationId, k -> new ArrayList<>()).add(service);
    }

    public List<Service> getServices(String reservationId) {
        return map.getOrDefault(reservationId, new ArrayList<>());
    }

    public double getTotalCost(String reservationId) {
        double total = 0;
        for (Service s : getServices(reservationId)) {
            total += s.getCost();
        }
        return total;
    }
}

public class UseCase7 {

    public static void main(String[] args) {

        AddOnServiceManager manager = new AddOnServiceManager();
        String reservationId = "R101";

        manager.addService(reservationId, new Service("Breakfast", 300));
        manager.addService(reservationId, new Service("WiFi", 150));

        for (Service s : manager.getServices(reservationId)) {
            System.out.println(s.getName() + " - ₹" + s.getCost());
        }

        System.out.println("Total Cost: ₹" + manager.getTotalCost(reservationId));
    }
}
git push origin feature/UC7
