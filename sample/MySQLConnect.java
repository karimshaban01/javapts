import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnect {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/logistics";
        String username = "karim";
        String password = "Karim@01";

        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            System.out.println("✅ Connected to the database!");
            conn.close();
        } catch (SQLException e) {
            System.out.println("❌ Connection failed.");
            e.printStackTrace();
        }
    }
}
