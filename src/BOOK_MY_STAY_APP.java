import java.util.*;

// Custom Exception
class InvalidBookingException extends Exception {
    public InvalidBookingException(String message) {
        super(message);
    }
}

// Validator class
class BookingValidator {

    private static final List<String> VALID_ROOM_TYPES =
            Arrays.asList("Standard", "Deluxe", "Suite");

    // Validate booking input
    public static void validate(String roomType, int availableRooms)
            throws InvalidBookingException {

        // Validate room type
        if (!VALID_ROOM_TYPES.contains(roomType)) {
            throw new InvalidBookingException("Invalid room type: " + roomType);
        }

        // Validate availability
        if (availableRooms <= 0) {
            throw new InvalidBookingException("No rooms available for type: " + roomType);
        }
    }
}

// Inventory class
class RoomInventory {
    private Map<String, Integer> rooms = new HashMap<>();

    public RoomInventory() {
        rooms.put("Standard", 2);
        rooms.put("Deluxe", 1);
        rooms.put("Suite", 0);
    }

    public void bookRoom(String roomType) throws InvalidBookingException {

        int available = rooms.getOrDefault(roomType, -1);

        // Validate before updating state (Fail-Fast)
        BookingValidator.validate(roomType, available);

        // Update inventory safely
        rooms.put(roomType, available - 1);

        System.out.println("Booking successful for " + roomType);
    }

    public void displayInventory() {
        System.out.println("\nCurrent Inventory:");
        for (String type : rooms.keySet()) {
            System.out.println(type + ": " + rooms.get(type));
        }
    }
}

// Main class
public class UseCase9 ErrorHandlingValidation {

    public static void main(String[] args) {

        RoomInventory inventory = new RoomInventory();

        // Test cases (valid + invalid)
        String[] testBookings = {"Deluxe", "Suite", "Premium"};

        for (String roomType : testBookings) {
            try {
                System.out.println("\nAttempting booking for: " + roomType);
                inventory.bookRoom(roomType);
            } catch (InvalidBookingException e) {
                // Graceful failure handling
                System.out.println("Error: " + e.getMessage());
            }
        }


}