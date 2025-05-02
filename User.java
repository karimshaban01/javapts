import java.sql.*;
class User {
    public int id;
    public String first_name;
    public String last_name;
    public String email;
    public String password;
    public String role;
    public String phone_number;
    public String address;

    User(int id, String first_name, String last_name, String email, String password, String role, String phone_number, String address){
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.phone_number = phone_number;
        this.address = address;
    }

    public void save(){
        String url = "jdbc:mysql://localhost/dsa_ga";
        String username = "karim";
        String pass = "Karim@01";

        try {
            Connection conn = DriverManager.getConnection(url, username, pass);
            String sql = "INSERT INTO Users(user_id, first_name, last_name, email, password, role, phone_number, address) VALUES(?,?,?,?,?,?,?,?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, this.id);
            stmt.setString(2, this.first_name);
            stmt.setString(3, this.last_name);
            stmt.setString(4, this.email);
            stmt.setString(5, this.password);
            stmt.setString(6, this.role);
            stmt.setString(7, this.phone_number);
            stmt.setString(8, this.address);
            stmt.executeUpdate();
            }
        catch(SQLException e){
            System.out.println("an error occured");
            e.printStackTrace();
        }
    }
 
    
}