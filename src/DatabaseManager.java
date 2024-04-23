import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseManager {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3307/java_grading";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    // Method to establish a connection to the database
// In DatabaseManager
public static Connection getConnection() throws SQLException {
    return DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
}

public static boolean verifyLogin(String email, String password) {
    try (Connection connection = getConnection()) {
        String sql = "SELECT * FROM users WHERE email = ? AND password = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, email);
            statement.setString(2, password);

            try (java.sql.ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next(); // If there is a matching record, login is successful
            }
        }
    } catch (SQLException ex) {
        System.err.println("Error verifying login: " + ex.getMessage());
        return false; // Return false in case of an exception
    }
}


public static void insertUser(String firstname, String lastname, String email, String password) {
    try (Connection connection = getConnection()) {
        String sql = "INSERT INTO users (firstname, lastname, email, password) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, firstname);
            statement.setString(2, lastname);
            statement.setString(3, email);
            statement.setString(4, password);

            int rowsInserted = statement.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("User inserted successfully!");
            }
        }
    } catch (SQLException ex) {
        System.err.println("Error inserting user: " + ex.getMessage());
    }
}
}
