public class Student {
    private int studentID;
    private String name;
    private int age;
    private String courseSection;
    private String comProgGrade;
    private String conTempGrade;
    private String discreteGrade;
    private String hciGrade;
    private String itGrade;
    private String physicsGrade;
    private String GPA; // String type to match database field type

    // Constructor
    public Student(int studentID, String name, int age, String courseSection, String comProgGrade, String conTempGrade, String discreteGrade, String hciGrade, String itGrade, String physicsGrade, String GPA) {
        this.studentID = studentID;
        this.name = name;
        this.age = age;
        this.courseSection = courseSection;
        this.comProgGrade = comProgGrade;
        this.conTempGrade = conTempGrade;
        this.discreteGrade = discreteGrade;
        this.hciGrade = hciGrade;
        this.itGrade = itGrade;
        this.physicsGrade = physicsGrade;
        this.GPA = GPA;
    }

    // Getters and setters
    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCourseSection() {
        return courseSection;
    }

    public void setCourseSection(String courseSection) {
        this.courseSection = courseSection;
    }

    public String getComProgGrade() {
        return comProgGrade;
    }

    public void setComProgGrade(String comProgGrade) {
        this.comProgGrade = comProgGrade;
    }

    public String getConTempGrade() {
        return conTempGrade;
    }

    public void setConTempGrade(String conTempGrade) {
        this.conTempGrade = conTempGrade;
    }

    public String getDiscreteGrade() {
        return discreteGrade;
    }

    public void setDiscreteGrade(String discreteGrade) {
        this.discreteGrade = discreteGrade;
    }

    public String getHCIGrade() {
        return hciGrade;
    }

    public void setHciGrade(String hciGrade) {
        this.hciGrade = hciGrade;
    }

    public String getITGrade() {
        return itGrade;
    }

    public void setItGrade(String itGrade) {
        this.itGrade = itGrade;
    }

    public String getPhysicsGrade() {
        return physicsGrade;
    }

    public void setPhysicsGrade(String physicsGrade) {
        this.physicsGrade = physicsGrade;
    }

    public String getGPA() {
        return GPA;
    }

    public void setGPA(String GPA) {
        this.GPA = GPA;
    }
}
