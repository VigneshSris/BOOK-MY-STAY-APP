import java.util.*;

// Reservation class
class Reservation {
    private String reservationId;
    private String roomType;
    private String roomId;
    private boolean isActive;

    public Reservation(String reservationId, String roomType, String roomId) {
        this.reservationId = reservationId;
        this.roomType = roomType;
        this.roomId = roomId;
        this.isActive = true;
    }

    public String getReservationId() { return reservationId; }
    public String getRoomType() { return roomType; }
    public String getRoomId() { return roomId; }
    public boolean isActive() { return isActive; }
    public void cancel() { isActive = false; }
}

// Inventory class
class RoomInventory {
    private Map<String, Integer> inventory = new HashMap<>();

    public RoomInventory() {
        inventory.put("Standard", 2);
        inventory.put("Deluxe", 1);
    }

    public void increment(String roomType) {
        inventory.put(roomType, inventory.getOrDefault(roomType, 0) + 1);
    }

    public void displayInventory() {
        System.out.println("\nCurrent Inventory:");
        for (String type : inventory.keySet()) {
            System.out.println(type + ": " + inventory.get(type));
        }
    }
}

// Cancellation Service
class CancellationService {
    private Map<String, Reservation> reservations;
    private Stack<String> rollbackStack;
    private RoomInventory inventory;

    public CancellationService(Map<String, Reservation> reservations, RoomInventory inventory) {
        this.reservations = reservations;
        this.inventory = inventory;
        this.rollbackStack = new Stack<>();
    }

    public void cancelBooking(String reservationId) {
        if (!reservations.containsKey(reservationId)) {
            System.out.println("Error: Reservation not found.");
            return;
        }

        Reservation res = reservations.get(reservationId);

        if (!res.isActive()) {
            System.out.println("Error: Reservation already cancelled.");
            return;
        }

        rollbackStack.push(res.getRoomId());
        inventory.increment(res.getRoomType());
        res.cancel();

        System.out.println("Cancellation successful for Reservation ID: " + reservationId);
    }

    public void displayRollbackStack() {
        System.out.println("\nRollback Stack (Recent Releases): " + rollbackStack);
    }
}

// Main class — must match the file name
public class UseCase10 Booking git checkout  {

    public static void main(String[] args) {
        Map<String, Reservation> reservations = new HashMap<>();
        reservations.put("R101", new Reservation("R101", "Standard", "S1"));
        reservations.put("R102", new Reservation("R102", "Deluxe", "D1"));

        RoomInventory inventory = new RoomInventory();
        CancellationService service = new CancellationService(reservations, inventory);

        // Test cases
        service.cancelBooking("R101");   // valid
        service.cancelBooking("R101");   // already cancelled
        service.cancelBooking("R999");   // invalid ID

        // Display system state
        inventory.displayInventory();
        service.displayRollbackStack();
    }
}