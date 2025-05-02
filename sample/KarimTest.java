import java.sql.*;

class KarimTest {
    public static void main(String[] args){
        String url = "jdbc:mysql://localhost:3306/logistics";
        String username = "karim";
        String password = "Karim@01";
        
        try {
        Connection conn = DriverManager.getConnection(url, username, password);
        System.out.println("Connected successfully...");
        
        //SELECTION
        String selectQuery = "SELECT * FROM percels";
        Statement selectStmt = conn.createStatement();
        ResultSet rs = selectStmt.executeQuery(selectQuery);
        
        while(rs.next()){
            String id = rs.getString("track_id");
            String origin = rs.getString("pickup_address");
            
            System.out.println("ID : "+id+"  FROM : "+origin);
        }
        }
        catch (SQLException e){
            System.out.println("Error connecting to database");
            e.printStackTrace();
        }
        
    }    
}
