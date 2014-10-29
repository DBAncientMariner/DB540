package ncsu.dbms.ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import ncsu.dbms.core.UserAttempt;
import ncsu.dbms.db.PastSubmissionData;

public class PastSubmissions {

	private JPanel contentPane;
	private JFrame frame;

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
		List<UserAttempt> inActiveExercise = PastSubmissionData.getExercise(false);
		List<UserAttempt> activeExercise = PastSubmissionData.getExercise(true);
		
		panel_Right.setLayout(null);
		
		int i = 10;
		if(inActiveExercise != null && inActiveExercise.size() > 0) {
			JLabel inactive = new JLabel("Inactive Exercise(s)");
			inactive.setBounds(10, i, 150, 35);
			panel_Right.add(inactive);
			i +=45;
		}
		for (final UserAttempt userAttempt : inActiveExercise) {
			JLabel exercise = new JLabel(PastSubmissionData.getExerciseName(userAttempt.getUA_EXERCISE_ID()));
			exercise.setBounds(10, i, 110, 35);
			panel_Right.add(exercise);
			JLabel attempt = new JLabel("Attempt" + userAttempt.getATTEMP_ID());
			attempt.setBounds(130, i, 110, 35);
			panel_Right.add(attempt);
			JButton view = new JButton("View");
			view.setBounds(250, i, 70, 30);
			view.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					frame.setVisible(false);
					new ViewPastSubmissions(userAttempt, false);
				}
			});
			panel_Right.add(view);
			i += 45;
		}
		
		if(activeExercise != null && activeExercise.size() > 0) {
			JLabel inactive = new JLabel("Active Exercise(s)");
			inactive.setBounds(10, i, 150, 35);
			panel_Right.add(inactive);
			i +=45;
		}
		
		for (final UserAttempt userAttempt : activeExercise) {
			JLabel exercise = new JLabel(PastSubmissionData.getExerciseName(userAttempt.getUA_EXERCISE_ID()));
			exercise.setBounds(10, i, 110, 35);
			panel_Right.add(exercise);
			JLabel attempt = new JLabel("Attempt" + userAttempt.getATTEMP_ID());
			attempt.setBounds(130, i, 110, 35);
			panel_Right.add(attempt);
			JButton view = new JButton("View");
			view.setBounds(250, i, 70, 30);
			view.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					frame.setVisible(false);
					new ViewPastSubmissions(userAttempt, true);
				}
			});
			panel_Right.add(view);
			i += 45;
		}
	}
}
