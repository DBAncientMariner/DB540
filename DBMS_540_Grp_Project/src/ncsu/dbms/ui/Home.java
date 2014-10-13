package ncsu.dbms.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JInternalFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JEditorPane;
import javax.swing.JTextPane;

public class Home {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home window = new Home();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Home() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 604, 485);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel main = new JPanel();
		main.setBounds(183, 0, 415, 450);
		frame.getContentPane().add(main);
		main.setLayout(null);
		
		JInternalFrame internalFrame = new JInternalFrame("New JInternalFrame");
		internalFrame.setBounds(0, 0, 415, 450);
		main.add(internalFrame);
		internalFrame.setVisible(true);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 0, 185, 57);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(0, 56, 185, 152);
		frame.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		JButton btnHome = new JButton("Home");
		btnHome.setBounds(53, 42, 89, 23);
		panel_2.add(btnHome);
		
		JButton btnAssignments = new JButton("Assignments");
		btnAssignments.setBounds(41, 76, 123, 23);
		panel_2.add(btnAssignments);
		
		JButton btnNewButton = new JButton("Past Assignments");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton.setBounds(41, 110, 118, 23);
		panel_2.add(btnNewButton);
		
		JTextPane txtpnStudent = new JTextPane();
		txtpnStudent.setText("Student");
		txtpnStudent.setBounds(58, 11, 84, 20);
		panel_2.add(txtpnStudent);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(0, 205, 185, 143);
		frame.getContentPane().add(panel_3);
		panel_3.setLayout(null);
		
		JButton btnCreateAssignment = new JButton("Create Assignment");
		btnCreateAssignment.setBounds(36, 41, 128, 23);
		panel_3.add(btnCreateAssignment);
		
		JButton btnViewAssignments = new JButton("View Assignments");
		btnViewAssignments.setBounds(36, 75, 128, 23);
		panel_3.add(btnViewAssignments);
		
		JButton btnViewQuestionBank = new JButton("View Question Bank");
		btnViewQuestionBank.setBounds(36, 109, 128, 23);
		panel_3.add(btnViewQuestionBank);
		
		JTextPane txtpnFaculty = new JTextPane();
		txtpnFaculty.setText("Faculty");
		txtpnFaculty.setBounds(36, 11, 113, 20);
		panel_3.add(txtpnFaculty);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBounds(0, 348, 185, 102);
		frame.getContentPane().add(panel_4);
		panel_4.setLayout(null);
		
		JButton btnAddUser = new JButton("Manage Assignments");
		btnAddUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAddUser.setBounds(21, 34, 138, 23);
		panel_4.add(btnAddUser);
		
		JButton btnManageCourses = new JButton("Manage Courses");
		btnManageCourses.setBounds(30, 68, 113, 23);
		panel_4.add(btnManageCourses);
	}
}
