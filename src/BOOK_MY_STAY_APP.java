import java.util.*;

// Reservation class
class Reservation {
    private String reservationId;
    private String guestName;
    private String roomType;

    public Reservation(String reservationId, String guestName, String roomType) {
        this.reservationId = reservationId;
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getReservationId() {
        return reservationId;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }

    @Override
    public String toString() {
        return "Reservation ID: " + reservationId +
                ", Guest: " + guestName +
                ", Room Type: " + roomType;
    }
}

// Booking History class (stores confirmed bookings)
class BookingHistory {
    private List<Reservation> history = new ArrayList<>();

    // Add confirmed reservation
    public void addReservation(Reservation reservation) {
        history.add(reservation);
    }

    // Retrieve all reservations
    public List<Reservation> getAllReservations() {
        return history;
    }
}

// Reporting Service
class BookingReportService {

    // Display all bookings
    public void displayAllBookings(List<Reservation> reservations) {
        System.out.println("---- Booking History ----");
        for (Reservation r : reservations) {
            System.out.println(r);
        }
    }

    // Generate summary report
    public void generateSummary(List<Reservation> reservations) {
        System.out.println("\n---- Booking Summary ----");
        System.out.println("Total Bookings: " + reservations.size());

        // Count room types
        Map<String, Integer> roomCount = new HashMap<>();
        for (Reservation r : reservations) {
            roomCount.put(
                    r.getRoomType(),
                    roomCount.getOrDefault(r.getRoomType(), 0) + 1
            );
        }

        System.out.println("Room Type Distribution:");
        for (String type : roomCount.keySet()) {
            System.out.println(type + ": " + roomCount.get(type));
        }
    }
}

// Main class
public class UseCase8BookingHistoryReport {

    public static void main(String[] args) {

        BookingHistory history = new BookingHistory();
        BookingReportService reportService = new BookingReportService();

        // Simulating confirmed bookings
        history.addReservation(new Reservation("R101", "Vignesh", "Deluxe"));
        history.addReservation(new Reservation("R102", "Arun", "Standard"));
        history.addReservation(new Reservation("R103", "Priya", "Deluxe"));

        // Admin views booking history
        List<Reservation> reservations = history.getAllReservations();

        reportService.displayAllBookings(reservations);
        reportService.generateSummary(reservations);

        // Ensure no modification
        System.out.println("\nBooking history remains unchanged.");
    }
