package studentManagement;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class LecturerReport extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;
    private final JTextArea reportTextArea;
    private final JButton generateButton;

    public LecturerReport() {
        super("Lecturer Report");
        setLayout(new BorderLayout());

        // Create GUI components
        reportTextArea = new JTextArea();
        reportTextArea.setEditable(false);
        JScrollPane reportScrollPane = new JScrollPane(reportTextArea);

        generateButton = new JButton("Generate Report");
        generateButton.addActionListener(this);

        cancelButton = new JButton("Back");
        cancelButton.setForeground(Color.BLACK);
        cancelButton.addActionListener(e -> {
            Menu menu = new Menu();
            menu.setVisible(true);
            dispose();
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(generateButton);
        buttonPanel.add(cancelButton);
        
        // Add components to frame
        add(reportScrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new LecturerReport();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            // Connect to the database
            Connection connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\ADMIN\\eclipse-workspace\\studentdb.db");
            Statement statement = connection.createStatement();

            // Retrieve data
            String query = "SELECT name, unitname FROM lecturer";
            ResultSet resultSet = statement.executeQuery(query);

            // Format the data
            Map<String, List<String>> lecturerUnitsMap = new HashMap<>();
            while (resultSet.next()) {
                String lecturerName = resultSet.getString("name");
                String unitName = resultSet.getString("unitname");
                lecturerUnitsMap.computeIfAbsent(lecturerName, k -> new ArrayList<>()).add(unitName);
            }

            // Generate the report
            StringBuilder report = new StringBuilder("Lecturer Report\n\n");
            report.append(String.format("%-20s %-20s\n", " name", "Units Allocated"));
            lecturerUnitsMap.forEach((name, units) -> report.append(String.format("%-20s %-20s\n", name, String.join(", ", units))));

            // Display the report
            reportTextArea.setText(report.toString());

            // Close resources
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private JButton cancelButton = new JButton("Cancel");
    private final JPanel inputPanel = new JPanel();

    {
        cancelButton.setForeground(Color.BLACK);
        cancelButton.addActionListener(e -> {
            Menu menu = new Menu();
            menu.setVisible(true);
            dispose();
        });
        cancelButton.setFont(new Font("Tahoma", Font.BOLD, 14));
        inputPanel.add(cancelButton);
    }
}