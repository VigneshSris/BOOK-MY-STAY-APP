import java.util.*;
import java.util.concurrent.*;

// Reservation class
class Reservation {
    private String reservationId;
    private String roomType;

    public Reservation(String reservationId, String roomType) {
        this.reservationId = reservationId;
        this.roomType = roomType;
    }

    public String getReservationId() {
        return reservationId;
    }

    public String getRoomType() {
        return roomType;
    }
}

// Thread-safe inventory
class RoomInventory {
    private Map<String, Integer> inventory = new HashMap<>();

    public RoomInventory() {
        inventory.put("Standard", 5);
        inventory.put("Deluxe", 3);
        inventory.put("Suite", 2);
    }

    // synchronized method to ensure thread-safe booking
    public synchronized boolean bookRoom(String roomType) {
        int available = inventory.getOrDefault(roomType, 0);
        if (available > 0) {
            inventory.put(roomType, available - 1);
            return true;
        } else {
            return false;
        }
    }

    public synchronized void displayInventory() {
        System.out.println("\nCurrent Inventory:");
        for (String type : inventory.keySet()) {
            System.out.println(type + ": " + inventory.get(type));
        }
    }
}

// Booking processor thread
class BookingProcessor implements Runnable {
    private String guestName;
    private String roomType;
    private RoomInventory inventory;
    private List<Reservation> confirmedBookings;

    public BookingProcessor(String guestName, String roomType,
                            RoomInventory inventory, List<Reservation> confirmedBookings) {
        this.guestName = guestName;
        this.roomType = roomType;
        this.inventory = inventory;
        this.confirmedBookings = confirmedBookings;
    }

    @Override
    public void run() {
        boolean success = inventory.bookRoom(roomType);
        if (success) {
            synchronized (confirmedBookings) { // thread-safe addition
                confirmedBookings.add(new Reservation(guestName, roomType));
            }
            System.out.println(guestName + " booked " + roomType + " successfully.");
        } else {
            System.out.println(guestName + " failed to book " + roomType + " (no availability).");
        }
    }
}

// Main class
public class UseCase11ConcurrentBookingSimulation {

    public static void main(String[] args) throws InterruptedException {
        RoomInventory inventory = new RoomInventory();
        List<Reservation> confirmedBookings = Collections.synchronizedList(new ArrayList<>());

        // Simulate multiple guest booking requests
        String[] guests = {"Alice", "Bob", "Charlie", "David", "Eve", "Frank"};
        String[] roomTypes = {"Standard", "Deluxe", "Suite", "Standard", "Deluxe", "Suite"};

        List<Thread> threads = new ArrayList<>();

        // Create and start threads
        for (int i = 0; i < guests.length; i++) {
            Thread t = new Thread(new BookingProcessor(guests[i], roomTypes[i], inventory, confirmedBookings));
            threads.add(t);
            t.start();
        }

        // Wait for all threads to complete
        for (Thread t : threads) {
            t.join();
        }

        // Display final system state
        inventory.displayInventory();

        System.out.println("\nConfirmed Bookings:");
        synchronized (confirmedBookings) {
            for (Reservation r : confirmedBookings) {
                System.out.println(r.getReservationId() + " -> " + r.getRoomType());
            }
        }
    }
}