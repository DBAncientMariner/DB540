package ncsu.dbms.ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import ncsu.dbms.db.PastSubmissionData;

public class PastSubmissions {

	private JPanel contentPane;
	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new PastSubmissions();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PastSubmissions() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 900, 700);
		frame.setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel_Center = new JPanel();
		panel_Center.setBounds(12, 141, 211, 236);
		contentPane.add(panel_Center);
		panel_Center.setLayout(null);

		JPanel panel_Bottom = new JPanel();
		panel_Bottom.setBounds(12, 390, 211, 224);
		contentPane.add(panel_Bottom);

		JPanel panel_Top = new JPanel();
		panel_Top.setBounds(12, 13, 211, 128);
		contentPane.add(panel_Top);
		panel_Top.setLayout(null);

		final JPanel panel_Right = new JPanel();
		JScrollPane scrollpane = new JScrollPane(panel_Right);
		scrollpane.setBounds(230, 13, 640, 627);
		scrollpane
				.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollpane
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollpane.setBackground(Color.WHITE);
		// contentPane.add(panel_Right);

		JLabel lbl_UserName = new JLabel("");
		lbl_UserName.setBounds(12, 10, 187, 33);
		panel_Top.add(lbl_UserName);
		JButton btnHome = new JButton("Home");
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
				new Home();
			}
		});
		btnHome.setBounds(12, 46, 187, 33);
		panel_Top.add(btnHome);

		JButton btn_Logout = new JButton("Logout");
		btn_Logout.setBounds(12, 82, 187, 33);
		panel_Top.add(btn_Logout);
		btn_Logout.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new Login();
				frame.setVisible(false);
			}
		});

		openExercise(panel_Right);
		frame.getContentPane().add(scrollpane);
		frame.setVisible(true);
	}

	private void openExercise(JPanel panel_Right) {
		List<String> allAssignments = PastSubmissionData.getAllAssignments();
		
		panel_Right.setLayout(new GridLayout(allAssignments.size() * 2, 1));

		for (String assignment : allAssignments) {
			JLabel assignmentText = new JLabel(assignment);
			panel_Right.add(assignmentText);
			List<String> attempts = PastSubmissionData.getExercisesAttempted(assignment);
			JPanel assignmentPanel = new JPanel(new GridLayout(attempts.size(), 2));
			for (String attempt : attempts) {
				JLabel attemptText = new JLabel(attempt);
				JButton attemptButton = new JButton("View");
				
				assignmentPanel.add(attemptText);
				assignmentPanel.add(attemptButton);
			}
			panel_Right.add(assignmentPanel);
		}
	}

}
