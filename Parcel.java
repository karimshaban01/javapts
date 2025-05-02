import java.sql.*;
//import java.time.*;
class Parcel {
    public int parcel_id;
    public int sender_id;
    public int recipient_id;
    public String dimensions;
    public String pickup_address;
    public String delivery_address;
    public String status;
    public String estimated_arrival_time;
    public String actual_arrival_time;

    Parcel(int parcel_id, int sender_id, int recipient_id, String dimensions, String pickup_address, String delivery_address, String status, String estimated_arrival_time, String actual_arrival_time){
        this.parcel_id = parcel_id;
        this.sender_id = sender_id;
        this.recipient_id =recipient_id;
        this.dimensions = dimensions;
        this.pickup_address = pickup_address;
        this.delivery_address = delivery_address;
        this.status = status;
        this.estimated_arrival_time = estimated_arrival_time;
        this.actual_arrival_time = actual_arrival_time;
    }

    public void save_parcel(){
        String url = "jdbc:mysql://localhost/dsa_ga";
        String username = "karim";
        String pass = "Karim@01";

        try {
            Connection conn = DriverManager.getConnection(url, username, pass);
            System.out.println("Connection established...");

            String sql = "INSERT INTO Parcels(parcel_id, sender_id, recipient_id, dimensions, pickup_address, delivery_address, status, estimated_arrival_time, actual_arrival_time) VALUES(?,?,?,?,?,?,?,?,?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, parcel_id);
            stmt.setInt(2, sender_id);
            stmt.setInt(3, recipient_id);
            stmt.setString(4, dimensions);
            stmt.setString(5, pickup_address);
            stmt.setString(6, delivery_address);
            stmt.setString(7, status);
            stmt.setString(8, estimated_arrival_time);
            stmt.setString(9, actual_arrival_time);
            stmt.executeUpdate();
            }
        catch(SQLException e){
            System.out.println("an error occured");
            e.printStackTrace();
        }
    }
}