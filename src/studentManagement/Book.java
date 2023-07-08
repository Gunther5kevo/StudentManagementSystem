package studentManagement;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JDesktopPane;

public class Book extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField sisbn;
	private JTextField sbookTitle;
	private JTextField scopiesAvailable;
	private JTextField scopiesBorrowed;
	
	Connection con = null;
	PreparedStatement pst = null;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Book frame = new Book();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public Book() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 588, 620);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel bookInformation = new JLabel("Book Information");
		bookInformation.setForeground(Color.BLACK);
		bookInformation.setFont(new Font("Perpetua Titling MT", Font.BOLD, 22));
		
		JLabel isbn = new JLabel("Isbn");
		isbn.setForeground(Color.BLACK);
		isbn.setFont(new Font("Perpetua Titling MT", Font.BOLD, 14));
		
		JLabel bookTitle = new JLabel("Book Title");
		bookTitle.setForeground(Color.BLACK);
		bookTitle.setFont(new Font("Perpetua Titling MT", Font.BOLD, 14));
		
		JLabel copiesAvailable = new JLabel("Copies Available");
		copiesAvailable.setForeground(Color.BLACK);
		copiesAvailable.setFont(new Font("Perpetua Titling MT", Font.BOLD, 14));
		
		JLabel copiesBorrowed = new JLabel("Copies Borrowed");
		copiesBorrowed.setForeground(Color.BLACK);
		copiesBorrowed.setFont(new Font("Perpetua Titling MT", Font.BOLD, 14));
	
		sisbn = new JTextField();
		sisbn.setColumns(10);
		
		sbookTitle = new JTextField();
		sbookTitle.setColumns(10);
		
		scopiesAvailable = new JTextField();
		scopiesAvailable.setColumns(10);
		
		scopiesBorrowed = new JTextField();
		scopiesBorrowed.setColumns(10);
			
		JButton submit = new JButton("Submit");
		submit.setForeground(Color.BLACK);
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				    String query = "INSERT INTO `books`(`isbn`, `bookTitle`, `copiesAvailable`, `copiesBorrowed`) VALUES (?, ?, ?, ?)";
				    con = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\ADMIN\\eclipse-workspace\\studentdb.db");
				    pst = con.prepareStatement(query);
				    pst.setString(1, sisbn.getText());
				    pst.setString(2, sbookTitle.getText());
				    pst.setInt(3, Integer.parseInt(scopiesAvailable.getText()));
				    pst.setInt(4, Integer.parseInt(scopiesBorrowed.getText()));
				    if(sisbn.getText().equals("") || sbookTitle.getText().equals("") || scopiesAvailable.getText().equals("") || scopiesBorrowed.getText().equals("")) {
				        JOptionPane.showMessageDialog(null, "Fill all the details :(");
				    }
				    else {
				        pst.executeUpdate();
				        JOptionPane.showMessageDialog(null, "Book added successfully :)");
				        dispose();
				        Menu menu = new Menu();
				        menu.setVisible(true);
				    }
				}
				catch(Exception ex) {
				    JOptionPane.showMessageDialog(null, ex);
				}
			}
		});
		submit.setFont(new Font("Tahoma", Font.BOLD, 14));
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBackground(Color.GRAY);
		
		JButton btnNewButton = new JButton("Cancel");
		btnNewButton.setForeground(Color.BLACK);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Menu menu = new Menu();
				menu.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JDesktopPane desktopPane_1 = new JDesktopPane();
		desktopPane_1.setBackground(Color.GRAY);
		
		JDesktopPane desktopPane_2 = new JDesktopPane();
		desktopPane_2.setBackground(Color.GRAY);
		
		JDesktopPane desktopPane_3 = new JDesktopPane();
		desktopPane_3.setBackground(Color.GRAY);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(desktopPane, GroupLayout.DEFAULT_SIZE, 573, Short.MAX_VALUE)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(desktopPane_1, GroupLayout.PREFERRED_SIZE, 563, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(desktopPane_2, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
								.addGap(43)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addComponent(isbn)
									.addComponent(bookTitle, GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)
									.addComponent(copiesAvailable)
									.addComponent(copiesBorrowed))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addComponent(sisbn, 242, 242, 242)
									.addComponent(sbookTitle, 247, 247, 247)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
										.addComponent(scopiesAvailable)
										.addComponent(scopiesBorrowed, GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)))
								.addGap(34))
							.addGroup(gl_contentPane.createSequentialGroup()
								.addPreferredGap(ComponentPlacement.RELATED, 125, Short.MAX_VALUE)
								.addComponent(bookInformation)
								.addGap(137))
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGap(119)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
									.addComponent(btnNewButton, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(submit, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE))
								.addGap(128)))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(desktopPane_3, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
						.addGap(18))
			);
			gl_contentPane.setVerticalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_contentPane.createSequentialGroup()
						.addComponent(desktopPane, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
						.addGap(11)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(bookInformation)
								.addGap(18)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
									.addComponent(isbn, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
									.addComponent(sisbn, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
								.addGap(28)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
									.addComponent(sbookTitle, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
									.addComponent(bookTitle))
								.addGap(41)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
									.addComponent(scopiesAvailable, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
									.addComponent(copiesAvailable))
								.addGap(37)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
									.addComponent(copiesBorrowed)
									.addComponent(scopiesBorrowed, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
								.addGap(41)
								.addComponent(submit, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_contentPane.createSequentialGroup()
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(desktopPane_2, GroupLayout.PREFERRED_SIZE, 506, GroupLayout.PREFERRED_SIZE))
							.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(desktopPane_3, GroupLayout.PREFERRED_SIZE, 506, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)))
						.addGap(13)
						.addComponent(desktopPane_1, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addGap(6))
			);
			contentPane.setLayout(gl_contentPane);
		}

	}