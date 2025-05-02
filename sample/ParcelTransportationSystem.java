import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ParcelTransportationSystem {

    // Database connection setup
    private static final String DB_URL = "jdbc:mysql://localhost:3306/dsa_ga";
    private static final String DB_USER = "karim"; // Use your database username
    private static final String DB_PASSWORD = "Karim@01"; // Use your database password

    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement pstmt = null;

        try {
            // Establish database connection
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			System.out.println("Connection established...");
            // Sample Users
            User sender = new User(1, "John", "Doe", "john.doe@example.com", "Sender");
            User recipient = new User(2, "Jane", "Smith", "jane.smith@example.com", "Recipient");
            User courier = new User(3, "Alex", "Brown", "alex.brown@example.com", "Courier");

            // Sample Parcel
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date estimatedArrival = sdf.parse("2025-04-22 10:00:00");

            // Insert sender and recipient to Users table
            insertUser(connection, sender);
            insertUser(connection, recipient);
            insertUser(connection, courier);

            // Insert parcel into Parcels table
            Parcel parcel = new Parcel(1, sender.getUserId(), recipient.getUserId(), "30x40x50, 5kg",
                                       "Sender Address", "Recipient Address", "Registered", estimatedArrival);
            insertParcel(connection, parcel);

            // Send Notification
            Notification notification = new Notification(1, sender.getUserId(), parcel.getParcelId(),
                                                        "Email", "Your parcel has been registered!", new Date(), "Sent");
            insertNotification(connection, notification);

            // Update Parcel Status
            parcel.updateStatus("In Transit");
            updateParcelStatus(connection, parcel);

            // Insert Tracking Information
            ParcelTracking tracking = new ParcelTracking(1, parcel.getParcelId(), "Warehouse A", new Date(), "At Warehouse");
            insertParcelTracking(connection, tracking);

            // Update parcel to Delivered and set actual arrival time
            parcel.updateStatus("Delivered");
            parcel.setActualArrivalTime(sdf.parse("2025-04-22 15:00:00"));
            updateParcelStatus(connection, parcel);

            // Display Parcel Details
            parcel.displayParcelDetails();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Method to insert user into the Users table
    public static void insertUser(Connection connection, User user) throws SQLException {
        String query = "INSERT INTO Users (user_id, first_name, last_name, email, role) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, user.getUserId());
            pstmt.setString(2, user.getFirstName());
            pstmt.setString(3, user.getLastName());
            pstmt.setString(4, user.getEmail());
            pstmt.setString(5, user.getRole());
            pstmt.executeUpdate();
        }
    }

    // Method to insert parcel into the Parcels table
    public static void insertParcel(Connection connection, Parcel parcel) throws SQLException {
        String query = "INSERT INTO Parcels (parcel_id, sender_id, recipient_id, dimensions, pickup_address, " +
                       "delivery_address, status, estimated_arrival_time) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, parcel.getParcelId());
            pstmt.setInt(2, parcel.getSenderId());
            pstmt.setInt(3, parcel.getRecipientId());
            pstmt.setString(4, parcel.getDimensions());
            pstmt.setString(5, parcel.getPickupAddress());
            pstmt.setString(6, parcel.getDeliveryAddress());
            pstmt.setString(7, parcel.getStatus());
            pstmt.setTimestamp(8, new Timestamp(parcel.getEstimatedArrivalTime().getTime()));
            pstmt.executeUpdate();
        }
    }

    // Method to update parcel status in the Parcels table
    public static void updateParcelStatus(Connection connection, Parcel parcel) throws SQLException {
        String query = "UPDATE Parcels SET status = ?, actual_arrival_time = ? WHERE parcel_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, parcel.getStatus());
            pstmt.setTimestamp(2, parcel.getActualArrivalTime() != null ? 
                               new Timestamp(parcel.getActualArrivalTime().getTime()) : null);
            pstmt.setInt(3, parcel.getParcelId());
            pstmt.executeUpdate();
        }
    }

    // Method to insert notification into the Notifications table
    public static void insertNotification(Connection connection, Notification notification) throws SQLException {
        String query = "INSERT INTO Notifications (user_id, parcel_id, notification_type, message, sent_time, status) " +
                       "VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, notification.getUserId());
            pstmt.setInt(2, notification.getParcelId());
            pstmt.setString(3, notification.getNotificationType());
            pstmt.setString(4, notification.getMessage());
            pstmt.setTimestamp(5, new Timestamp(notification.getSentTime().getTime()));
            pstmt.setString(6, notification.getStatus());
            pstmt.executeUpdate();
        }
    }

    // Method to insert parcel tracking information
    public static void insertParcelTracking(Connection connection, ParcelTracking tracking) throws SQLException {
        String query = "INSERT INTO Parcel_Tracking (parcel_id, checkpoint_location, status_update_time, status) " +
                       "VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, tracking.getParcelId());
            pstmt.setString(2, tracking.getCheckpointLocation());
            pstmt.setTimestamp(3, new Timestamp(tracking.getStatusUpdateTime().getTime()));
            pstmt.setString(4, tracking.getStatus());
            pstmt.executeUpdate();
        }
    }
}
