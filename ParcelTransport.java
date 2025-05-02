import java.util.Scanner;
import java.sql.*;

public class ParcelTransport {
    public static String role;

    public static void login(){
        Scanner input = new Scanner(System.in);
        System.out.println("----- You are sign in parcel transport system -----");
        System.out.print("Email # ");
        String user_email = input.nextLine();
        
        System.out.print("Password # ");
        String passwd = input.nextLine();

        System.out.println("========= AUTHENTICATING =========");
        
        
        String url = "jdbc:mysql://localhost/dsa_ga?useSSL=false&allowPublicKeyRetrieval=true";
        String username = "karim";
        String pass = "Karim@01";

        try {
            Connection conn = DriverManager.getConnection(url, username, pass);
            String authentication_sql = "SELECT * FROM Users WHERE email = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(authentication_sql);
            preparedStatement.setString(1, user_email); // Safely set the email parameter
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {   
                String first_name = rs.getString("first_name");
                String role = rs.getString("role");
                String passd = rs.getString("password");

                if(passwd.equals(passd)){
                    System.out.println("User authenticated");
                } else {
                    System.out.println("Invalid password");
                    login();
                }
                // Retrieve other user details
                System.out.println("Role: "+role);
                System.out.println("Hi "+first_name);
            }
        } catch (SQLException e) {
            System.out.println("An error occurred");
            e.printStackTrace();
        }
    

        System.out.println(".... Redirecting to Menu .... ");
        menu();
    }

    public static void register_parcel(){
        Scanner input = new Scanner(System.in);
        System.out.println("----- You are registering new parcel -----");
        System.out.print("PARCEL ID # ");
        int id = input.nextInt();
        
        System.out.print("SENDER # ");
        int sender = input.nextInt();

        System.out.print("RECIPIENT # ");
        int recipient = input.nextInt();

        System.out.print("DIMENSIONS # ");
        String dimensions = input.nextLine();

        System.out.print("PICK UP ADDRESS # ");
        String pickup_address = input.nextLine();

        System.out.print("DELIVERY ADDRESS # ");
        String delivery_address = input.nextLine();

        System.out.print("STATUS # ");
        String status = input.nextLine();

        System.out.print("ESTIMATED ARRIVAL TIME # ");
        String estimated_arrival_time = input.nextLine();

        System.out.print("ACTUAL ARRIVAL TIME # ");
        String actual_arrival_time = input.nextLine();

        System.out.println("========= SAVING PARCEL =========");
        Parcel p = new Parcel(id, sender, recipient, dimensions, pickup_address, delivery_address, status, estimated_arrival_time, actual_arrival_time);
        p.save_parcel();
        

        System.out.println("New parcel added");
        menu();
    }

    public static void track_parcel(){
        System.out.println("------Tracking a parcel------");
        Scanner input = new Scanner(System.in);

        System.out.print("ENTER PARCEL TRACK ID # ");
        int track_id = input.nextInt();

        String url = "jdbc:mysql://localhost/dsa_ga";
        String username = "karim";
        String pass = "Karim@01";

        try {
            Connection conn = DriverManager.getConnection(url, username, pass);

            String query = "SELECT * FROM Parcels WHERE parcel_id="+track_id;
            //PreparedStatement stmt = conn.prepareStatement(query);
            //stmt.setInt(1, track_id);
            Statement selectStmt = conn.createStatement();
            ResultSet rs = selectStmt.executeQuery(query);

            while(rs.next()){
                String pickup_address = rs.getString("pickup_address");
                System.out.println(pickup_address);
            }
            
            System.out.println("it works");
        }
        catch(SQLException e){
            e.printStackTrace();
        }

        menu();
    }


    public static void register(){
        Scanner input = new Scanner(System.in);
        System.out.println("----- You are registering new user -----");
        System.out.print("ID # ");
        int id = input.nextInt();
        
        System.out.print("FIRST NAME # ");
        String first_name = input.nextLine();

        System.out.print("LAST NAME # ");
        String last_name = input.nextLine();

        System.out.print("EMAIL # ");
        String email = input.nextLine();

        System.out.print("PASSWORD # ");
        String password = input.nextLine();

        System.out.print("ROLE # ");
        role = input.nextLine();

        System.out.print("PHONE NUMBER # ");
        String phone_number = input.nextLine();

        System.out.print("ADDRESS # ");
        String address = input.nextLine();

        System.out.print("========= SAVING USER =========");
        User user = new User(id, first_name, last_name, email, password, role, phone_number, address);
        user.save();
        System.out.println("New User added...");
        menu();
    }

    
    public static void menu(){
        System.out.println(role);
        Scanner input = new Scanner(System.in);
        System.out.print("Enter option # ");
        int opt = input.nextInt();
        switch(opt){
            case 1:
                register_parcel();
                break;
            
            case 2:
                track_parcel();
                break;

            case 3:
                System.out.println("rou selected 3");
                break;

            case 4:
                register();
                break;
        }
    }

    public static void main(String[] args){
        login();
    }
}