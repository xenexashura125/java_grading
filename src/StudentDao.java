import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDao {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3307/java_grading";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    // Method to establish a connection to the database
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
    }

    // Method to insert a new student record into the database
    public void insertStudent(Student student) {
        String sql = "INSERT INTO students (StudentID,Name, Age, Course_Section, ComProgGrade, ConTempGrade, DiscreteGrade, HCIGrade, ITGrade, PhysicsGrade, GPA) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, student.getStudentID());
            statement.setString(2, student.getName());
            statement.setInt(3, student.getAge());
            statement.setString(4, student.getCourseSection());
            statement.setString(5, student.getComProgGrade());
            statement.setString(6, student.getConTempGrade());
            statement.setString(7, student.getDiscreteGrade());
            statement.setString(8, student.getHCIGrade());
            statement.setString(9, student.getITGrade());
            statement.setString(10, student.getPhysicsGrade());
            statement.setString(11, student.getGPA()); // Adding GPA field

            statement.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Error inserting student: " + ex.getMessage());
        }
    }

    // Method to retrieve all students from the database
    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM students";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int studentID = resultSet.getInt("StudentID");
                String name = resultSet.getString("Name");
                int age = resultSet.getInt("Age");
                String courseSection = resultSet.getString("Course_Section");
                String comProgGrade = resultSet.getString("ComProgGrade");
                String conTempGrade = resultSet.getString("ConTempGrade");
                String discreteGrade = resultSet.getString("DiscreteGrade");
                String hciGrade = resultSet.getString("HCIGrade");
                String itGrade = resultSet.getString("ITGrade");
                String physicsGrade = resultSet.getString("PhysicsGrade");
                String GPA = resultSet.getString("GPA"); // Retrieving GPA field

                students.add(new Student(studentID, name, age, courseSection, comProgGrade, conTempGrade, discreteGrade, hciGrade, itGrade, physicsGrade, GPA));
            }
        } catch (SQLException ex) {
            System.err.println("Error retrieving students: " + ex.getMessage());
        }

        return students;
    }

    // Method to update a student record in the database
    public void updateStudent(Student student) {
        String sql = "UPDATE students SET Name = ?, Age = ?, Course_Section = ?, ComProgGrade = ?, ConTempGrade = ?, DiscreteGrade = ?, HCIGrade = ?, ITGrade = ?, PhysicsGrade = ?, GPA = ? WHERE StudentID = ?";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, student.getName());
            statement.setInt(2, student.getAge());
            statement.setString(3, student.getCourseSection());
            statement.setString(4, student.getComProgGrade());
            statement.setString(5, student.getConTempGrade());
            statement.setString(6, student.getDiscreteGrade());
            statement.setString(7, student.getHCIGrade());
            statement.setString(8, student.getITGrade());
            statement.setString(9, student.getPhysicsGrade());
            statement.setString(10, student.getGPA()); // Adding GPA field
            statement.setInt(11, student.getStudentID());

            statement.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Error updating student: " + ex.getMessage());
        }
    }

    // Method to delete a student record from the database
    public void deleteStudent(int studentID) {
        String sql = "DELETE FROM students WHERE StudentID = ?";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, studentID);

            statement.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Error deleting student: " + ex.getMessage());
        }
    }
}
