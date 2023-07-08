package studentManagement;

import java.awt.*;
import javax.swing.*;
import java.sql.*;

public class CourseSearch extends JFrame {
    private static final long serialVersionUID = 1L;
    private JLabel nameLabel;
    private JTextField nameField;
    private JButton studentButton;
    private JButton lecturerButton;
    private JLabel resultLabel;
    private JTextArea resultArea;
    private Connection connection;

    public CourseSearch() {
        super("Course Search");
        setLayout(new BorderLayout());

        nameLabel = new JLabel("Name:");
        nameField = new JTextField(20);
        studentButton = new JButton("Search Student Courses");
        lecturerButton = new JButton("Search Lecturer Courses");
        resultLabel = new JLabel("Results:");
        resultArea = new JTextArea(10, 30);

        JPanel inputPanel = new JPanel(new GridLayout(2, 2));
        inputPanel.add(nameLabel);
        inputPanel.add(nameField);
        inputPanel.add(studentButton);
        inputPanel.add(lecturerButton);

        JPanel resultPanel = new JPanel(new BorderLayout());
        resultPanel.add(resultLabel, BorderLayout.NORTH);
        resultPanel.add(new JScrollPane(resultArea), BorderLayout.CENTER);

        add(inputPanel, BorderLayout.NORTH);
        add(resultPanel, BorderLayout.CENTER);

        studentButton.addActionListener(e -> searchCourses("SELECT courses.course_name FROM courses INNER JOIN student_courses ON courses.course_id = student_courses.course_id INNER JOIN students ON student_courses.student_id = students.student_id WHERE students.student_name = ?"));
        lecturerButton.addActionListener(e -> searchCourses("SELECT courses.course_name FROM courses INNER JOIN lecturer_courses ON courses.course_id = lecturer_courses.course_id INNER JOIN lecturers ON lecturer_courses.lecturer_id = lecturers.Lid WHERE lecturers.lecturer_name = ?"));

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);

        try {
            // Connect to the database
            connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\ADMIN\\eclipse-workspace\\studentdb.db");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        JButton cancelButton = new JButton("Cancel");
        cancelButton.setForeground(Color.BLACK);
        cancelButton.addActionListener(e -> {
            Menu menu = new Menu();
            menu.setVisible(true);
            dispose();
        });
        cancelButton.setFont(new Font("Tahoma", Font.BOLD, 14));
        inputPanel.add(cancelButton);
    }

    private void searchCourses(String query) {
        try {
            String name = nameField.getText();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, name);
            ResultSet result = statement.executeQuery();

            resultArea.setText("");
            while (result.next()) {
                String courseName = result.getString("course_name");
                resultArea.append(courseName + "\n");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new CourseSearch();
    }
}