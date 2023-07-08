package studentManagement;

import java.awt.EventQueue;
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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JDesktopPane;
import java.awt.Color;

public class ViewStudent extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private final JTextArea reportTextArea;
	private final JButton generateButton;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				ViewStudent frame = new ViewStudent();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public ViewStudent() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 582, 611);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBackground(Color.GRAY);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup().addComponent(desktopPane,
						GroupLayout.PREFERRED_SIZE, 753, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addComponent(desktopPane, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(423, Short.MAX_VALUE)));

		// Create GUI components
		reportTextArea = new JTextArea();
		reportTextArea.setEditable(false);
		JScrollPane reportScrollPane = new JScrollPane(reportTextArea);

		generateButton = new JButton("Generate Report");
		generateButton.addActionListener(this);

		JLabel lblNewLabel = new JLabel("Student Details");
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 28));
		lblNewLabel.setBounds(255, 27, 225, 52);
		desktopPane.add(lblNewLabel);

		JButton btnNewButton = new JButton("Go Back");
		btnNewButton.setForeground(Color.BLACK);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Menu menu = new Menu();
				menu.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.setBounds(10, 96, 113, 32);
		desktopPane.add(btnNewButton);

		// Add components to the content pane
		GroupLayout gl_desktopPane = new GroupLayout(desktopPane);
		gl_desktopPane.setHorizontalGroup(gl_desktopPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_desktopPane.createSequentialGroup().addContainerGap()
						.addGroup(gl_desktopPane.createParallelGroup(Alignment.LEADING)
								.addComponent(reportScrollPane, GroupLayout.DEFAULT_SIZE, 733, Short.MAX_VALUE)
								.addComponent(generateButton)).addContainerGap()));
		gl_desktopPane.setVerticalGroup(gl_desktopPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_desktopPane.createSequentialGroup().addContainerGap()
						.addComponent(generateButton).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addComponent(reportScrollPane, GroupLayout.DEFAULT_SIZE, 423, Short.MAX_VALUE)
						.addContainerGap()));
		desktopPane.setLayout(gl_desktopPane);

		contentPane.setLayout(gl_contentPane);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			// Connect to the database
			Connection connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\ADMIN\\eclipse-workspace\\studentdb.db");
			Statement statement = connection.createStatement();

			// Retrieve data
			String query = "SELECT name, Studentid FROM student";
			ResultSet resultSet = statement.executeQuery(query);

			// Format the data
			Map<String, List<String>> lecturerUnitsMap = new HashMap<>();
			while (resultSet.next()) {
				String StudentName = resultSet.getString("name");
				String StudentId = resultSet.getString("StudentId");
				lecturerUnitsMap.computeIfAbsent(StudentName, k -> new ArrayList<>()).add(StudentId);
			}

			// Generate the report
			StringBuilder report = new StringBuilder("Student Report\n\n");
			report.append(String.format("%-20s %-20s\n", " name", "Student Id"));
			lecturerUnitsMap.forEach((name, StudentId) -> report.append(String.format("%-20s %-20s\n", name, String.join(", ", StudentId))));

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
}