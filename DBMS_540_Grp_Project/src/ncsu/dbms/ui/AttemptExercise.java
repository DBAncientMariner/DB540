package ncsu.dbms.ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import ncsu.dbms.core.Exercise;
import ncsu.dbms.core.Options;
import ncsu.dbms.core.Question;
import ncsu.dbms.db.ExerciseData;

public class AttemptExercise {

	private JPanel contentPane;
	private JFrame frame;
	private JComboBox<Object> selectAssignment;
	private List<Question> exerciseQuestions;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new AttemptExercise(1);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AttemptExercise(final int index) {
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
		panel_Bottom.setLayout(null);

		JPanel panel_Top = new JPanel();
		panel_Top.setBounds(12, 13, 211, 128);
		contentPane.add(panel_Top);
		panel_Top.setLayout(null);

		final JPanel panel_Right = new JPanel();
		final JScrollPane scrollpane = new JScrollPane(panel_Right);
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
				frame.setVisible(false);
				new Login();
			}
		});
		
		JLabel selectAssignmentText = new JLabel("Select Assignment");
		selectAssignmentText.setBounds(12, 50, 187, 33);
		panel_Center.add(selectAssignmentText);
		
		final List<Exercise> exerciseList = ExerciseData.getExerciseList();
		List<String> list = ExerciseData.getExerciseName(exerciseList);
		
		selectAssignment = new JComboBox<>(list.toArray());
		selectAssignment.setBounds(12, 90, 187, 33);
		selectAssignment.setSelectedIndex(index);
		panel_Center.add(selectAssignment);
		selectAssignment.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new AttemptExercise(selectAssignment.getSelectedIndex());
				frame.setVisible(false);
			}
		});

		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
				ExerciseData.saveExercise(exerciseQuestions, exerciseList.get(index));
				new Home();
			}
		});
		btnSave.setBounds(12, 107, 187, 33);
		panel_Bottom.add(btnSave);

		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
				ExerciseData.submitExercise(exerciseQuestions, exerciseList.get(index));
				new Home();
			}
		});
		btnSubmit.setBounds(12, 143, 187, 33);
		panel_Bottom.add(btnSubmit);

		openExercise(scrollpane, panel_Right, exerciseList.get(index));
		frame.getContentPane().add(scrollpane);
		frame.setVisible(true);
	}

	private void openExercise(JScrollPane scrollpane, JPanel panel_Right, Exercise exercise) {
		exerciseQuestions = ExerciseData.getExerciseQuestions(exercise.EXERCISE_ID);
		panel_Right.removeAll();
		panel_Right.setLayout(new GridLayout(exerciseQuestions.size() * 5, 1));

		for (Question question : exerciseQuestions) {
			JLabel questionText = new JLabel(question.getQuestion());
			panel_Right.add(questionText);

			if (!question.isMultipleChoice()) {
				ButtonGroup group = new ButtonGroup();
				for (final Options option : question.getOptions()) {
					final JRadioButton optionbutton = new JRadioButton(option.getAnswer());
					optionbutton.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							if (optionbutton.isSelected()) {
								option.setMarked(true);
							} else {
								option.setMarked(false);
							}
						}
					});
					group.add(optionbutton);
					panel_Right.add(optionbutton);
				}
			} else {
				for (Options option : question.getOptions()) {
					JCheckBox optionbutton = new JCheckBox(option.getAnswer());
					panel_Right.add(optionbutton);
				}
			}
		}
	}
}
