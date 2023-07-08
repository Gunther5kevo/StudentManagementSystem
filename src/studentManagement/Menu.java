
/*Group Members 
Joseph Okolla eb1/46515/20
Musila Gloria eb1/56113/21
Jeff Mwenda eb1/56388/21
Malcom Pinotte eb1/56125/21
Roy Nyarega eb1/52858/21
Glen Ijatia eb1/56069/21
Ian Lenox eb1/52915/21
Chrsiphyn Oino eb1/56071/21
Brian Mungai eb1/56045/21
Leah Wanjiru  eb1/56422/21
Kevin Kipyego eb1/56110/21
Albert Murimi eb1/46507/20
Meshack Cheruyiot Eb1/56081/21
James Mwangi Mwaniki eb1/56141/21
*/


package studentManagement;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JDesktopPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class Menu extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3461950381615900626L;
	private JPanel contentPane;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu frame = new Menu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public Menu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 0, 780, 890);
		contentPane = new JPanel();
		contentPane.setBackground(Color.GRAY);
		contentPane.setBorder(new EmptyBorder(5, 3, 5, 5));
		setContentPane(contentPane);
		
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBackground(Color.LIGHT_GRAY);
		
		JDesktopPane desktopPane_1 = new JDesktopPane();
		desktopPane_1.setBackground(Color.LIGHT_GRAY);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			    gl_contentPane.createParallelGroup(Alignment.LEADING)
			        .addGroup(gl_contentPane.createSequentialGroup()
			            .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
			                .addGroup(gl_contentPane.createSequentialGroup()
			                    .addContainerGap()
			                    .addComponent(desktopPane_1, GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE))
			                .addGroup(gl_contentPane.createSequentialGroup()
			                    .addGap(36)
			                    .addComponent(desktopPane, GroupLayout.PREFERRED_SIZE, 351, GroupLayout.PREFERRED_SIZE)))
			            .addContainerGap())
			);
			gl_contentPane.setVerticalGroup(
			    gl_contentPane.createParallelGroup(Alignment.LEADING)
			        .addGroup(gl_contentPane.createSequentialGroup()
			            .addContainerGap()
			            .addComponent(desktopPane_1, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
			            .addGap(26)
			            .addComponent(desktopPane, GroupLayout.PREFERRED_SIZE, 600, GroupLayout.PREFERRED_SIZE)
			            .addContainerGap(37, Short.MAX_VALUE))
			);
		
		JButton btnNewButton = new JButton("Add Student");
		btnNewButton.setForeground(Color.BLACK);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Student studentDetails = new Student();
				studentDetails.setVisible(true);
				dispose();
				
			}
		});
		btnNewButton.setBounds(20, 20, 280, 47);
		desktopPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Remove Existing Student");
		btnNewButton_1.setForeground(Color.BLACK);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				RemoveStudent remove = new RemoveStudent();
				remove.setVisible(true);
				dispose();
				
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton_1.setBounds(20, 80, 280, 50);
		desktopPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("View Students");
		btnNewButton_2.setForeground(Color.BLACK);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ViewStudent viewStudent = new ViewStudent();
				viewStudent.setVisible(true);
				dispose();
				
			}
		});
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton_2.setBounds(20, 136, 280, 50);
		desktopPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Update Existing Student");
		btnNewButton_3.setForeground(Color.BLACK);
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				UpdateStudent updateStudent = new UpdateStudent();
				updateStudent.setVisible(true);
				dispose();
				
			}
		});
		btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton_3.setBounds(20, 190, 280, 50);
		desktopPane.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Add Lecturer");
		btnNewButton_4.setForeground(Color.BLACK);
		btnNewButton_4.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton_4.setBounds(20, 245, 280, 50);
		desktopPane.add(btnNewButton_4);
		btnNewButton_4.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        Lecturer lecturerDetails = new Lecturer();
		        lecturerDetails.setVisible(true);
		        dispose();
		    }
		});

		JButton btnNewButton_5 = new JButton("Add Course");
		btnNewButton_5.setForeground(Color.BLACK);
		btnNewButton_5.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton_5.setBounds(20, 300, 280, 50);
		desktopPane.add(btnNewButton_5);
		btnNewButton_5.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        Course courseDetails = new Course();
		        courseDetails.setVisible(true);
		        dispose();
		    }
		});

		JButton btnNewButton_6 = new JButton("Book Details");
		btnNewButton_6.setForeground(Color.BLACK);
		btnNewButton_6.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton_6.setBounds(20, 355, 280, 50);
		desktopPane.add(btnNewButton_6);
		btnNewButton_6.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        Book bookInformation = new Book();
		        bookInformation.setVisible(true);
		        dispose();
		    }
		});

		JButton btnNewButton_7 = new JButton("Course Search");
		btnNewButton_7.setForeground(Color.BLACK);
		btnNewButton_7.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton_7.setBounds(20, 410, 280, 50);
		desktopPane.add(btnNewButton_7);
		btnNewButton_7.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        CourseSearch courseSearch = new CourseSearch();
		        courseSearch.setVisible(true);
		        dispose();
		    }
		});

		JButton btnNewButton_8 = new JButton("Lecturer Report");
		btnNewButton_8.setForeground(Color.BLACK);
		btnNewButton_8.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton_8.setBounds(20, 465, 280, 50);
		desktopPane.add(btnNewButton_8);
		btnNewButton_8.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		       LecturerReport lecturerReport = new  LecturerReport();
		        lecturerReport.setVisible(true);
		        dispose();
		    }
		});

		
		JButton btnNewButton_9 = new JButton("Logout");
		btnNewButton_9.setForeground(Color.BLACK);
		btnNewButton_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Login logout = new Login();
				logout.setVisible(true);
				dispose();
				JOptionPane.showMessageDialog(null, "Successfully logged out :)");
			}
		});
		btnNewButton_9.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton_9.setBounds(15, 520, 280, 50);
		desktopPane.add(btnNewButton_9);
		
		
		
		JLabel lblNewLabel = new JLabel("What do you want ?");
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setBounds(93, 17, 220, 27);
		desktopPane_1.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 22));
		contentPane.setLayout(gl_contentPane);
	}
}