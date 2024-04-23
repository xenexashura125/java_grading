import javax.swing.*;
import java.awt.*;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.util.List;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JComboBox;


public class HomeFrame extends javax.swing.JFrame {
    private String firstName;
    private String lastName;
    private StudentDao student_Dao; // Corrected declaration

    public HomeFrame(StudentDao studentDao, String firstName, String lastName) {
        initComponents();
        this.firstName = firstName;
        this.lastName = lastName;
        this.student_Dao = studentDao; // Assign StudentDao object

        // Set the teacher's name label
        teacher_name.setText(firstName + " " + lastName);

        // Populate student list panel using the provided StudentDao
        populateStudentListPanel(student_Dao);
    }
    
    
    private void populateStudentListPanel(StudentDao studentDao) {
        // Remove any existing components from the panel
        studentListPanel.removeAll();

        // Set layout for the panel
        studentListPanel.setLayout(new GridBagLayout());

        // Fetch student list from the database using StudentDao
        List<Student> studentList = studentDao.getAllStudents();

        // Check if any students were retrieved from the database
        if (studentList.isEmpty()) {
            System.out.println("No students found in the database.");
            return;
        }

        // Output the retrieved students to the console for debugging
        for (Student student : studentList) {
            System.out.println(student);
        }
        
        // Define GridBagConstraints for label positioning
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(5, 5, 5, 5); // Add some padding

        // Add header labels with custom styling
        addHeaderLabel(studentListPanel, gbc, "ID", 0, 0);
        addHeaderLabel(studentListPanel, gbc, "Name", 0, 1);
        addHeaderLabel(studentListPanel, gbc, "Age", 0, 2);
        addHeaderLabel(studentListPanel, gbc, "Course Section", 0, 3);
        addHeaderLabel(studentListPanel, gbc, "IT Era", 0, 4);
        addHeaderLabel(studentListPanel, gbc, "Comprog", 0, 5);
        addHeaderLabel(studentListPanel, gbc, "HCI", 0, 6);
        addHeaderLabel(studentListPanel, gbc, "Contemp", 0, 7);
        addHeaderLabel(studentListPanel, gbc, "Discrete", 0, 8);
        addHeaderLabel(studentListPanel, gbc, "Physics", 0, 9);
        addHeaderLabel(studentListPanel, gbc, "GPA", 0, 10);
        addHeaderLabel(studentListPanel, gbc, "Actions", 0, 11); // New column for buttons

        // Add more header labels for other attributes as needed

        // Add student information to the panel
        int row = 1; // Start from row 1 (row 0 is for header)
        for (Student student : studentList) {
            addLabel(studentListPanel, gbc, String.valueOf(student.getStudentID()), row, 0);
            addLabel(studentListPanel, gbc, student.getName(), row, 1);
            addLabel(studentListPanel, gbc, String.valueOf(student.getAge()), row, 2);
            addLabel(studentListPanel, gbc, student.getCourseSection(), row, 3);
            addLabel(studentListPanel, gbc, student.getITGrade(), row, 4);
            addLabel(studentListPanel, gbc, student.getComProgGrade(), row, 5);
            addLabel(studentListPanel, gbc, student.getHCIGrade(), row, 6);
            addLabel(studentListPanel, gbc, student.getConTempGrade(), row, 7);
            addLabel(studentListPanel, gbc, student.getDiscreteGrade(), row, 8);
            addLabel(studentListPanel, gbc, student.getPhysicsGrade(), row, 9);
            addLabel(studentListPanel, gbc, student.getGPA(), row, 10);

            JButton editButton = new JButton("Modify");
            editButton.addActionListener(e -> {
                // Action to populate the edit fields with student information
                ed_studentID.setText(String.valueOf(student.getStudentID()));
                ed_studentName.setText(student.getName());
                ed_studentAge1.setText(String.valueOf(student.getAge()));
                ed_studentcourse_sec.setSelectedItem(student.getCourseSection()); 
                ed_var_it_grd.setText(student.getITGrade());
                ed_var_comp_grd.setText(student.getComProgGrade());
                ed_var_hci_grd.setText(student.getHCIGrade());
                ed_var_contemp.setText(student.getConTempGrade());
                ed_var_disc_grd.setText(student.getDiscreteGrade());
                ed_var_phy_grd.setText(student.getPhysicsGrade());
                ed_var_gpa.setText(student.getGPA());
            });
            addComponent(studentListPanel, editButton, row, 11); // Adjust column index accordingly

 
            // Add more labels for other attributes as needed
            row++;
        }

        // Update the panel
        studentListPanel.revalidate();
        studentListPanel.repaint();
    }

    private void addHeaderLabel(JPanel panel, GridBagConstraints gbc, String text, int row, int column) {
        // Add label with custom styling for header
        JLabel label = new JLabel(text);
        label.setFont(label.getFont().deriveFont(Font.BOLD, 14)); // Set font size and style
        label.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Add border
        label.setBackground(Color.GRAY); // Set background color
        label.setOpaque(true); // Ensure background color is visible
        label.setHorizontalAlignment(SwingConstants.CENTER); // Center align text
        gbc.gridx = column;
        gbc.gridy = row;
        gbc.weightx = 0.2; // Adjust this value to control label width
        gbc.fill = GridBagConstraints.HORIZONTAL; // Ensure label fills horizontal space
        panel.add(label, gbc);
    }

    private void addLabel(JPanel panel, GridBagConstraints gbc, String text, int row, int column) {
        // Add label with consistent width
        JLabel label = new JLabel(text);
        gbc.gridx = column;
        gbc.gridy = row;
        gbc.weightx = 0.2; // Adjust this value to control label width
        panel.add(label, gbc);
    }
    
    // Helper method to add a component to the panel with GridBagConstraints
    private void addComponent(JPanel panel, Component component, int row, int column) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = column;
        gbc.gridy = row;
        gbc.insets = new Insets(5, 5, 5, 5);
        panel.add(component, gbc);
    }

    
    /**
     * Creates new form HomeFrame
     */
    public HomeFrame() {
        initComponents();       
        
        // Populate student list panel
        
        populateStudentListPanel(student_Dao);
        if (!GradingGUI.isLoginSuccessful()) {
            // If login is not successful, display an error message and exit
            JOptionPane.showMessageDialog(this, "Please login first.");
            System.exit(0);
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        textArea1 = new java.awt.TextArea();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        teacher_name = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        var_phy_grd = new javax.swing.JTextField();
        var_disc_grd = new javax.swing.JTextField();
        var_contemp_grd = new javax.swing.JTextField();
        var_hci_grd = new javax.swing.JTextField();
        var_comp_grd = new javax.swing.JTextField();
        var_it_grd = new javax.swing.JTextField();
        totalGPA = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        studentAge = new javax.swing.JTextField();
        studentName = new javax.swing.JTextField();
        studentID = new javax.swing.JTextField();
        studentcourse_sec1 = new javax.swing.JComboBox<>();
        jPanel5 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        var_gpa = new javax.swing.JTextField();
        addStudent = new javax.swing.JButton();
        resultAddStudentGrade = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        ed_studentAge1 = new javax.swing.JTextField();
        ed_studentName = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        ed_studentID = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        ed_var_it_grd = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        ed_var_comp_grd = new javax.swing.JTextField();
        ed_var_hci_grd = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        ed_var_contemp = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        ed_var_disc_grd = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        ed_var_phy_grd = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        confirm_update = new javax.swing.JButton();
        edit_totalGPA = new javax.swing.JButton();
        ed_var_gpa = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        resultTextArea = new javax.swing.JLabel();
        ed_studentcourse_sec = new javax.swing.JComboBox<>();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        studentListPanel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Students Grading ");

        teacher_name.setText("XXXXXXXX");

        jLabel6.setText("Teacher Admin");

        jPanel3.setBackground(new java.awt.Color(116, 182, 245));

        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("IT Era");

        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Comprog");

        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("HCI");

        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Contemp ");

        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Discrete");

        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Physics");

        totalGPA.setText("Total GPA");
        totalGPA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                totalGPAActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 117, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(totalGPA, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(var_phy_grd, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(var_disc_grd, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(var_contemp_grd, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(var_hci_grd, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(var_comp_grd, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(var_it_grd, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(var_comp_grd, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(var_hci_grd, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(var_contemp_grd, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(var_disc_grd, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(var_phy_grd, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(var_it_grd, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(totalGPA)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(116, 182, 245));

        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Course / Section");

        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Student Age");

        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Student Name");

        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Student ID");

        studentName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                studentNameActionPerformed(evt);
            }
        });

        studentID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                studentIDActionPerformed(evt);
            }
        });

        studentcourse_sec1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "BSIT 1-1", "BSIT 1-2", "BSIT 1-3", "BSIT 1-4", "BSIT 1-5", "BSIT 1-6", "BSIT 1-7", "BSIT 1-8" }));
        studentcourse_sec1.setMinimumSize(new java.awt.Dimension(64, 22));
        studentcourse_sec1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                studentcourse_sec1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 62, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(studentAge, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                    .addComponent(studentName, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                    .addComponent(studentID, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                    .addComponent(studentcourse_sec1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(17, 17, 17))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(38, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addComponent(studentName, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(studentAge, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addComponent(studentID, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(127, 127, 127)))
                        .addComponent(studentcourse_sec1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29))))
        );

        jPanel5.setBackground(new java.awt.Color(116, 182, 245));

        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("GPA");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
                .addComponent(var_gpa, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(var_gpa, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        addStudent.setText("Add Student Grade");
        addStudent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addStudentActionPerformed(evt);
            }
        });

        resultAddStudentGrade.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/img2.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(teacher_name, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(resultAddStudentGrade, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(addStudent, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(teacher_name, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(resultAddStudentGrade, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addStudent, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(35, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel19.setText("Student Name");

        ed_studentName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ed_studentNameActionPerformed(evt);
            }
        });

        jLabel7.setText("Course / Section");

        ed_studentID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ed_studentIDActionPerformed(evt);
            }
        });

        jLabel18.setText("Student ID");

        jLabel20.setText("Student Age");

        jLabel21.setText("IT Era");

        ed_var_it_grd.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel22.setText("Comprog");

        ed_var_comp_grd.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        ed_var_hci_grd.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel23.setText("HCI");

        ed_var_contemp.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel24.setText("Contemp");

        ed_var_disc_grd.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel25.setText("Discrete");

        ed_var_phy_grd.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel26.setText("Physics");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setText("Edit Student Item");

        confirm_update.setBackground(new java.awt.Color(116, 182, 245));
        confirm_update.setForeground(new java.awt.Color(255, 255, 255));
        confirm_update.setText("Confirm Modify");
        confirm_update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirm_updateActionPerformed(evt);
            }
        });

        edit_totalGPA.setText("Total GPA");
        edit_totalGPA.setActionCommand("edit Total GPA");
        edit_totalGPA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edit_totalGPAActionPerformed(evt);
            }
        });

        ed_var_gpa.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        ed_var_gpa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ed_var_gpaActionPerformed(evt);
            }
        });

        jLabel27.setText("GPA");

        resultTextArea.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        ed_studentcourse_sec.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "BSIT 1-1", "BSIT 1-2", "BSIT 1-3", "BSIT 1-4", "BSIT 1-5", "BSIT 1-6", "BSIT 1-7", "BSIT 1-8" }));
        ed_studentcourse_sec.setMinimumSize(new java.awt.Dimension(64, 22));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(ed_studentName, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ed_studentAge1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ed_studentID, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(edit_totalGPA, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(confirm_update, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE))
                            .addComponent(ed_var_gpa, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(resultTextArea, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ed_studentcourse_sec, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ed_var_phy_grd, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ed_var_disc_grd, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ed_var_contemp, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ed_var_it_grd, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ed_var_comp_grd, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ed_var_hci_grd, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(12, 12, 12)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(ed_var_it_grd, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ed_var_comp_grd, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ed_var_hci_grd, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(ed_studentID, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(ed_studentName, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(ed_studentAge1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(50, 50, 50))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(24, 24, 24)
                            .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(ed_studentcourse_sec, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ed_var_contemp, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ed_var_disc_grd, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(resultTextArea, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(ed_var_gpa, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(edit_totalGPA, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ed_var_phy_grd, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(confirm_update, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 19, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setMinimumSize(new java.awt.Dimension(1185, 181));

        javax.swing.GroupLayout studentListPanelLayout = new javax.swing.GroupLayout(studentListPanel);
        studentListPanel.setLayout(studentListPanelLayout);
        studentListPanelLayout.setHorizontalGroup(
            studentListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(studentListPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(138, Short.MAX_VALUE))
        );
        studentListPanelLayout.setVerticalGroup(
            studentListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, studentListPanelLayout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(studentListPanel);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1173, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    
    private void studentIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_studentIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_studentIDActionPerformed

    private void studentNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_studentNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_studentNameActionPerformed

    private double calculateGPA(String... grades) {
        double totalScore = 0.0;
        int count = 0;

        for (String grade : grades) {
            try {
                double score = Double.parseDouble(grade);
                totalScore += score;
                count++;
            } catch (NumberFormatException e) {
                // Handle invalid grade format
                // You may want to log an error or display a message
            }
        }

        if (count == 0) {
            return 0.0; // Return 0 if no valid grades were provided to avoid division by zero
        }

        // Calculate average score
        double averageScore = totalScore / count;

        // Convert average score to GPA based on your GPA scale
        double gpa = convertScoreToGPA(averageScore);

        return gpa;
    }
    
    private double convertScoreToGPA(double score) {
        // Convert average score to GPA based on your GPA scale
        // You can define your conversion logic here
        // This is just a placeholder method; replace it with your actual implementation
        return score; // Assuming the score already represents GPA in this example
    }
    
    private void addStudentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addStudentActionPerformed
        List<Student> studentList = student_Dao.getAllStudents();

        int student_id;
        String name = studentName.getText().trim();

        // Check if the name field is empty
        if (name.isEmpty()) {
            // Display error message
            resultAddStudentGrade.setText("Please enter a name.");
            return; // Exit method
        }

        String ageText = studentAge.getText().trim();
        String courseSection = String.valueOf(studentcourse_sec1.getSelectedItem());
        String comProgGrade = var_comp_grd.getText().trim();
        String conTempGrade = var_contemp_grd.getText().trim();
        String discreteGrade = var_disc_grd.getText().trim();
        String hciGrade = var_hci_grd.getText().trim();
        String itGrade = var_it_grd.getText().trim();
        String physicsGrade = var_phy_grd.getText().trim();
        String GPA_grd = var_gpa.getText().trim();

        // Check if any other required field is empty
        if (ageText.isEmpty() || courseSection.isEmpty() ||
                comProgGrade.isEmpty() || conTempGrade.isEmpty() || discreteGrade.isEmpty() ||
                hciGrade.isEmpty() || itGrade.isEmpty() || physicsGrade.isEmpty()) {
            // Display error message
            resultAddStudentGrade.setText("Please fill in all fields.");
            return; // Exit method
        }

        // Validate age input
        int age;
        try {
            age = Integer.parseInt(ageText);
        } catch (NumberFormatException e) {
            // Display error message if age is not a valid integer
            resultAddStudentGrade.setText("Age must be a valid integer.");
            return; // Exit method
        }

        if (!isValidGrade(comProgGrade) || !isValidGrade(conTempGrade) || !isValidGrade(discreteGrade) ||
                !isValidGrade(hciGrade) || !isValidGrade(itGrade) || !isValidGrade(physicsGrade)) {
            // Display error message
            resultAddStudentGrade.setText("Grades must be between 60 and 100.");
            return; // Exit method
        }

        // Check for duplicate student IDs
        try {
            student_id = Integer.parseInt(studentID.getText().trim());
            for (Student existingStudent : studentList) {
                if (existingStudent.getStudentID() == student_id) {
                    // Display error message if student ID already exists
                    resultAddStudentGrade.setText("Student ID already exists.");
                    return; // Exit method
                }
            }
        } catch (NumberFormatException e) {
            // Display error message if student ID is not a valid integer
            resultAddStudentGrade.setText("Student ID must be a valid integer.");
            return; // Exit method
        }

        // Check for duplicate student names
        for (Student existingStudent : studentList) {
            if (existingStudent.getName().equalsIgnoreCase(name)) {
                // Display error message if student name already exists
                resultAddStudentGrade.setText("Student name already exists.");
                return; // Exit method
            }
        }

        // Create a new Student object with the collected information
        Student student = new Student(student_id, name, age, courseSection, comProgGrade,
                conTempGrade, discreteGrade, hciGrade, itGrade, physicsGrade, GPA_grd);

        // Insert the student into the database using StudentDao
        StudentDao studentDao = new StudentDao();
        studentDao.insertStudent(student);

        // Calculate GPA for the current student
        double gpa = calculateGPA(comProgGrade, conTempGrade, discreteGrade, hciGrade, itGrade, physicsGrade);

        // Set the calculated GPA to the GPA field
        var_gpa.setText(String.format("%.2f", gpa)); // Assuming var_gpa is your JTextField for displaying GPA

        // Display a message indicating that the student has been added
        resultAddStudentGrade.setText("Student added successfully!");
        populateStudentListPanel(student_Dao);
    }//GEN-LAST:event_addStudentActionPerformed
    
    private boolean isValidGrade(String grade) {
    try {
        double score = Double.parseDouble(grade);
        return score >= 60 && score <= 100;
    } catch (NumberFormatException e) {
        return false; // Invalid grade format
    }
}
    
    private void totalGPAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_totalGPAActionPerformed
        // Collect grades from the text fields
        String comProgGrade = var_comp_grd.getText().trim();
        String conTempGrade = var_contemp_grd.getText().trim();
        String discreteGrade = var_disc_grd.getText().trim();
        String hciGrade = var_hci_grd.getText().trim();
        String itGrade = var_it_grd.getText().trim();
        String physicsGrade = var_phy_grd.getText().trim();

        // Validate grades input
        if (!isValidGrade(comProgGrade) || !isValidGrade(conTempGrade) || !isValidGrade(discreteGrade) ||
                !isValidGrade(hciGrade) || !isValidGrade(itGrade) || !isValidGrade(physicsGrade)) {
            // Display error message
            resultAddStudentGrade.setText("Grades must be between 60 and 100.");
            return; // Exit method
        }

        
        // Calculate total GPA
        double totalGPA = calculateGPA(comProgGrade, conTempGrade, discreteGrade, hciGrade, itGrade, physicsGrade);

        // Set the calculated total GPA to the GPA field
        var_gpa.setText(String.format("%.2f", totalGPA)); // Assuming var_gpa is your JTextField for displaying GPA
    }//GEN-LAST:event_totalGPAActionPerformed

    
    private void ed_studentIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ed_studentIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ed_studentIDActionPerformed

    private void ed_studentNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ed_studentNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ed_studentNameActionPerformed


    
    private void confirm_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirm_updateActionPerformed
        // Collect updated student information from the text fields
        int studentID = Integer.parseInt(ed_studentID.getText().trim());
        String name = ed_studentName.getText().trim();
        int age = Integer.parseInt(ed_studentAge1.getText().trim());
        String courseSection = String.valueOf(ed_studentcourse_sec.getSelectedItem());
        String comProgGrade = ed_var_comp_grd.getText().trim();
        String conTempGrade = ed_var_contemp.getText().trim();
        String discreteGrade = ed_var_disc_grd.getText().trim();
        String hciGrade = ed_var_hci_grd.getText().trim();
        String itGrade = ed_var_it_grd.getText().trim();
        String physicsGrade = ed_var_phy_grd.getText().trim();
        String GPA = ed_var_gpa.getText().trim();

        // Create a new Student object with the updated information
        Student updatedStudent = new Student(studentID, name, age, courseSection,
                comProgGrade, conTempGrade, discreteGrade, hciGrade, itGrade, physicsGrade, GPA);

        // Update the student information in the database using StudentDao
        student_Dao.updateStudent(updatedStudent);

        // Display a message indicating that the student information has been updated
        JOptionPane.showMessageDialog(this, "Student information updated successfully!");

        // Refresh the student list panel after the update
        populateStudentListPanel(student_Dao);
    }//GEN-LAST:event_confirm_updateActionPerformed

    private void edit_totalGPAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edit_totalGPAActionPerformed
        // TODO add your handling code here:
                // Collect grades from the text fields
        String comProgGrade = ed_var_comp_grd.getText().trim();
        String conTempGrade = ed_var_contemp.getText().trim();
        String discreteGrade = ed_var_disc_grd.getText().trim();
        String hciGrade = ed_var_hci_grd.getText().trim();
        String itGrade = ed_var_it_grd.getText().trim();
        String physicsGrade = ed_var_phy_grd.getText().trim();

        // Validate grades input
        if (!isValidGrade(comProgGrade) || !isValidGrade(conTempGrade) || !isValidGrade(discreteGrade) ||
                !isValidGrade(hciGrade) || !isValidGrade(itGrade) || !isValidGrade(physicsGrade)) {
            // Display error message
            // Assuming you have a JTextArea for displaying messages named resultTextArea
            resultTextArea.setText("Grades must be between 60 and 100.");
            return; // Exit method
        }
        
        // Calculate total GPA
        double totalGPA_ = calculateGPA(comProgGrade, conTempGrade, discreteGrade, hciGrade, itGrade, physicsGrade);

        // Set the calculated total GPA to the GPA field
        ed_var_gpa.setText(String.format("%.2f", totalGPA_)); // Assuming ed_var_gpa is your JTextField for displaying GPA
    }//GEN-LAST:event_edit_totalGPAActionPerformed

    private void ed_var_gpaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ed_var_gpaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ed_var_gpaActionPerformed

    private void studentcourse_sec1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_studentcourse_sec1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_studentcourse_sec1ActionPerformed
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(HomeFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HomeFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HomeFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HomeFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HomeFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addStudent;
    private javax.swing.JButton confirm_update;
    private javax.swing.JTextField ed_studentAge1;
    private javax.swing.JTextField ed_studentID;
    private javax.swing.JTextField ed_studentName;
    private javax.swing.JComboBox<String> ed_studentcourse_sec;
    private javax.swing.JTextField ed_var_comp_grd;
    private javax.swing.JTextField ed_var_contemp;
    private javax.swing.JTextField ed_var_disc_grd;
    private javax.swing.JTextField ed_var_gpa;
    private javax.swing.JTextField ed_var_hci_grd;
    private javax.swing.JTextField ed_var_it_grd;
    private javax.swing.JTextField ed_var_phy_grd;
    private javax.swing.JButton edit_totalGPA;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel resultAddStudentGrade;
    private javax.swing.JLabel resultTextArea;
    private javax.swing.JTextField studentAge;
    private javax.swing.JTextField studentID;
    private javax.swing.JPanel studentListPanel;
    private javax.swing.JTextField studentName;
    private javax.swing.JComboBox<String> studentcourse_sec1;
    private javax.swing.JLabel teacher_name;
    private java.awt.TextArea textArea1;
    private javax.swing.JButton totalGPA;
    private javax.swing.JTextField var_comp_grd;
    private javax.swing.JTextField var_contemp_grd;
    private javax.swing.JTextField var_disc_grd;
    private javax.swing.JTextField var_gpa;
    private javax.swing.JTextField var_hci_grd;
    private javax.swing.JTextField var_it_grd;
    private javax.swing.JTextField var_phy_grd;
    // End of variables declaration//GEN-END:variables
}
