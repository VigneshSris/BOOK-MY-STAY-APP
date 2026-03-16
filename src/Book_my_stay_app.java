<<<<<<< HEAD
public class Book_my_stay_app {
=======
import java.util.HashMap;
import java.util.Map;

/**
 * UseCase3InventorySetup
 *
 * Demonstrates centralized room inventory management
 * using a HashMap as a single source of truth.
 *
 * @author Vigneshsirs
 * @version 3.1
 */

class RoomInventory {

    private HashMap<String, Integer> inventory;

    // Constructor initializes room availability
    public RoomInventory() {
        inventory = new HashMap<>();

        inventory.put("Single Room", 5);
        inventory.put("Double Room", 3);
        inventory.put("Suite Room", 2);
    }

    // Retrieve availability
    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    // Update availability
    public void updateAvailability(String roomType, int newCount) {
        inventory.put(roomType, newCount);
    }

    // Display inventory
    public void displayInventory() {
        System.out.println("Current Room Inventory:");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + " -> Available: " + entry.getValue());
        }
    }
}

public class UseCase3InventorySetup {
>>>>>>> 62dd821 (UC3: Centralized Room Inventory Management using HashMap)

    public static void main(String[] args) {

        System.out.println("=====================================");
        System.out.println("        BOOK MY STAY APP");
<<<<<<< HEAD
        System.out.println("      Hotel Booking System v1.0");
        System.out.println("=====================================");
        System.out.println("Welcome to the Hotel Booking Application!");
        System.out.println("Application started successfully.");

=======
        System.out.println("      Hotel Booking System v3.1");
        System.out.println("=====================================\n");

        RoomInventory inventory = new RoomInventory();

        // Display initial inventory
        inventory.displayInventory();

        // Example update
        System.out.println("\nUpdating Single Room availability...\n");
        inventory.updateAvailability("Single Room", 4);

        // Display updated inventory
        inventory.displayInventory();
>>>>>>> 62dd821 (UC3: Centralized Room Inventory Management using HashMap)
    }

}