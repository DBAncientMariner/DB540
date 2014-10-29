package ncsu.dbms.ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import ncsu.dbms.core.Answer;
import ncsu.dbms.core.CorrectAnswer;
import ncsu.dbms.core.UserAttempt;
import ncsu.dbms.db.PastSubmissionData;

public class ViewPastSubmissions {

	private JPanel contentPane;
	private JFrame frame;
	private UserAttempt userAttempt;
	private boolean isActive;

	/**
	 * Create the frame.
	 */
	public ViewPastSubmissions(UserAttempt userAttempt, boolean isActive) {
		this.userAttempt = userAttempt;
		this.isActive = isActive;
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
		
		JButton btn_Back = new JButton("Back");
		btn_Back.setBounds(12, 118, 187, 33);
		panel_Center.add(btn_Back);
		btn_Back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				new PastSubmissions();
			}
		});

		openExercise(panel_Right);
		frame.getContentPane().add(scrollpane);
		frame.setVisible(true);
	}

	private void openExercise(JPanel panel_Right) {
		List<Answer> exercise = PastSubmissionData.getExerciseWithAnswer(userAttemptId, exerciseId, isActive);

		panel_Right.setLayout(new GridLayout(exercise.size() * 7, 1));

		for (Answer answer : exercise) {
			JLabel questionText = new JLabel(answer.getQuestion());
			panel_Right.add(questionText);

			boolean isCorrect = true;
			String explanation = "";
			List<Integer> correctAnswer = new LinkedList<Integer>();
			int i = 1;
			if (!answer.isMultipleChoice()) {
				ButtonGroup group = new ButtonGroup();
				for (CorrectAnswer option : answer.getOptions()) {
					JRadioButton optionbutton = new JRadioButton(option.getOption());
					if(option.isMarked()) {
						optionbutton.setSelected(true);
					}
					if(option.isCorrect()) {
						correctAnswer.add(i);
					}
					if(option.isCorrect() != option.isMarked()) {
						isCorrect = false;
						if(option.isCorrect()) {
							if(isActive && option.isCorrect()) {
								explanation = option.getShortExplanation();
							} else {
								explanation = option.getDetailExplanation();
							}
						}
					}
					i++;
					group.add(optionbutton);
					panel_Right.add(optionbutton);
				}
				
			} else {
				for (CorrectAnswer option : answer.getOptions()) {
					JCheckBox optionbutton = new JCheckBox(option.getOption());
					if(option.isMarked()) {
						optionbutton.setSelected(true);
					}
					if(option.isCorrect()) {
						correctAnswer.add(i);
					}
					if(option.isCorrect() != option.isMarked()) {
						isCorrect = false;
						if(option.isCorrect()) {
							if(isActive && option.isCorrect()) {
								explanation = option.getShortExplanation();
							} else {
								explanation = option.getDetailExplanation();
							}
						}
					}
					i++;
					panel_Right.add(optionbutton);
				}
			}
			if(isActive) {
				if(isCorrect) {
					JLabel correct = new JLabel("Correct Answer");
					panel_Right.add(correct);
				} else {
					JLabel correct = new JLabel("Incorrect Answer");
					panel_Right.add(correct);
				}
			} else {
				if(isCorrect) {
					JLabel correct = new JLabel("Correct Answer");
					panel_Right.add(correct);
				} else {
					String cString = "" + correctAnswer.get(0);
					for(int j = 1; j < correctAnswer.size(); j++) {
						cString = cString + ", " + correctAnswer.get(j);
					}
					JLabel correct = new JLabel("Correct answer is " + cString);
					panel_Right.add(correct);
				}
			}
			JLabel explanationLabel = new JLabel(explanation);
			panel_Right.add(explanationLabel);
		}
	}
}
