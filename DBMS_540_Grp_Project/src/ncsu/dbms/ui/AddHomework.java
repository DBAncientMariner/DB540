package ncsu.dbms.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import ncsu.dbms.core.*;

public class AddHomework extends JFrame {

	public int ExerciseId = 0;
	private static final long serialVersionUID = 4620833118495352591L;
	OracleDataAdapter oracleDataAdapter = new OracleDataAdapter();
	private JPanel contentPane;
	final JPanel panel_Right = new JPanel();
	JList jListTopic;
	JList jListQuestion;
	JList jListSelectedQuestion;
	DefaultListModel listModelTopic = new DefaultListModel();
	DefaultListModel listModelQuestion = new DefaultListModel();
	DefaultListModel listModelSelectedQuestion = new DefaultListModel();
	ArrayList<Topic> selectedListTopics = new ArrayList<Topic>();
	ArrayList<QuestionBank> questionBank = new ArrayList<QuestionBank>();
	ArrayList<QuestionBank> selectedQuestionBank = new ArrayList<QuestionBank>();

	JTextField txt_DifficultyRange1;
	JTextField txt_DifficultyRange2;
	/**
	 * Launch the application.
	 */
	// public static void main(String[] args) {
	// EventQueue.invokeLater(new Runnable() {
	// public void run() {
	// try {
	//
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }
	// });
	// }

	/**
	 * Create the frame.
	 */
	public AddHomework() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 900, 700);
		this.setLocationRelativeTo(null);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.contentPane.setLayout(new BorderLayout(0, 0));
		this.setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel_Bottom = new JPanel();
		panel_Bottom.setBounds(12, 390, 211, 224);
		contentPane.add(panel_Bottom);

		JPanel panel_Top = new JPanel();
		panel_Top.setBounds(12, 13, 211, 128);
		contentPane.add(panel_Top);
		panel_Top.setLayout(null);

		panel_Right.setBounds(230, 13, 640, 627);
		contentPane.add(panel_Right);
		panel_Right.setLayout(null);

		JPanel panel_Center = new JPanel();
		panel_Center.setBounds(12, 141, 211, 236);
		contentPane.add(panel_Center);
		panel_Center.setLayout(null);
		JButton btn_ViewScore = new JButton("ViewScore");
		btn_ViewScore.setBounds(12, 47, 187, 33);
		panel_Center.add(btn_ViewScore);

		JButton btn_AttemptHomework = new JButton("Attempt Homework");
		btn_AttemptHomework.setBounds(12, 83, 187, 33);
		btn_AttemptHomework.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new AttemptExercise(0);
				setVisible(false);
			}
		});
		panel_Center.add(btn_AttemptHomework);

		JButton btn_ViewSubmissions = new JButton("Past Submissions");
		btn_ViewSubmissions.setBounds(12, 125, 187, 33);
		panel_Center.add(btn_ViewSubmissions);
		JLabel lbl_UserName = new JLabel("");
		lbl_UserName.setBounds(12, 10, 187, 33);
		panel_Top.add(lbl_UserName);
		panel_Right.setLayout(null);
		JButton btnHome = new JButton("Home");
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Home();
				setVisible(false);
			}
		});
		btnHome.setBounds(12, 46, 187, 33);
		panel_Top.add(btnHome);

		JButton btn_Logout = new JButton("Logout");
		btn_Logout.setBounds(12, 82, 187, 33);
		btn_Logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Login();
				setVisible(false);
			}
		});
		panel_Top.add(btn_Logout);
		// add notification
		JButton btn_Notification = new JButton("Notification");
		btn_Notification.setBounds(12, 161, 187, 33);
		btn_Notification.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Notification notification = new Notification();
				notification.setVisible(true);
				setVisible(false);
			}
		});
		panel_Center.add(btn_Notification);
		DisplayAllTopic();

		// if(User.IsFaculty(LocalSession.GetCurrentUser()))
		// GetNotificationFaculty();
		// else
		// GetNotificationStudent();
		//

	}

	public void HideNotificationFrame(JFrame frame) {
		frame.setVisible(true);
		this.setVisible(false);
	}

	public void DisplayAllTopic() {
		final Course selectedCourse = LocalSession.CourseListModel
				.get(LocalSession.GetCurrentSelectedCourse());

		JLabel lbl_CourseName = new JLabel("CourseName:"
				+ selectedCourse.CSC_COURSE_Course_Name);
		lbl_CourseName.setBounds(10, 5, 450, 25);
		panel_Right.add(lbl_CourseName);

		JLabel exerciseName = new JLabel("ExerciseName");
		exerciseName.setBounds(10, 45, 100, 25);
		panel_Right.add(exerciseName);

		final JTextField txt_ExerciseName = new JTextField();
		txt_ExerciseName.setToolTipText("Exercise Name");
		txt_ExerciseName.setBounds(120, 45, 100, 25);
		panel_Right.add(txt_ExerciseName);

		JLabel lbl_StartDate = new JLabel("Start Date");
		lbl_StartDate.setBounds(10, 80, 100, 25);
		panel_Right.add(lbl_StartDate);

		final JTextField txt_StartDate = new JTextField();
		txt_StartDate.setToolTipText("Start Date (yyyy-MM-dd");
		txt_StartDate.setBounds(120, 80, 100, 25);
		panel_Right.add(txt_StartDate);

		JLabel lbl_EndDate = new JLabel("End Date");
		lbl_EndDate.setBounds(230, 80, 100, 25);
		panel_Right.add(lbl_EndDate);

		final JTextField txt_EndDate = new JTextField();
		txt_EndDate.setToolTipText("End Date (yyyy-MM-dd");
		txt_EndDate.setBounds(340, 80, 100, 25);
		panel_Right.add(txt_EndDate);

		JLabel lbl_DifficultyRange1 = new JLabel("Difficulty Range 1");
		lbl_DifficultyRange1.setBounds(10, 115, 100, 25);
		panel_Right.add(lbl_DifficultyRange1);

		txt_DifficultyRange1 = new JTextField("1");
		txt_DifficultyRange1.setToolTipText("Select Difficulty Range");
		txt_DifficultyRange1.setBounds(120, 115, 100, 25);
		panel_Right.add(txt_DifficultyRange1);

		JLabel lbl_DifficultyRange3 = new JLabel("Difficulty Range 2");
		lbl_DifficultyRange3.setBounds(230, 115, 100, 25);
		panel_Right.add(lbl_DifficultyRange3);

		txt_DifficultyRange2 = new JTextField("6");
		txt_DifficultyRange2.setToolTipText("Select Difficulty Range 2");
		txt_DifficultyRange2.setBounds(340, 115, 100, 25);
		panel_Right.add(txt_DifficultyRange2);

		JLabel lbl_RetryLimit = new JLabel("Retry Limit");
		lbl_RetryLimit.setBounds(10, 150, 100, 25);
		panel_Right.add(lbl_RetryLimit);

		final JTextField txt_RetryLimit = new JTextField();
		txt_RetryLimit.setToolTipText("Retry Limit");
		txt_RetryLimit.setBounds(120, 150, 100, 25);
		panel_Right.add(txt_RetryLimit);

		JLabel lbl_CorrectPoint = new JLabel("Correct Point");
		lbl_CorrectPoint.setBounds(230, 150, 100, 25);
		panel_Right.add(lbl_CorrectPoint);

		final JTextField txt_CorrectPoint = new JTextField();
		txt_CorrectPoint.setToolTipText("Correct Point");
		txt_CorrectPoint.setBounds(340, 150, 100, 25);
		panel_Right.add(txt_CorrectPoint);
		// /
		JLabel lbl_PenaltyPoint = new JLabel("Penalty Point");
		lbl_PenaltyPoint.setBounds(10, 185, 100, 25);
		panel_Right.add(lbl_PenaltyPoint);

		final JTextField txt_PenaltyPoint = new JTextField();
		txt_PenaltyPoint.setToolTipText("Penalty Point");
		txt_PenaltyPoint.setBounds(120, 185, 100, 25);
		panel_Right.add(txt_PenaltyPoint);

		JLabel lbl_ScoringType = new JLabel("Scoring Type");
		lbl_ScoringType.setBounds(230, 185, 100, 25);
		panel_Right.add(lbl_ScoringType);

		final JTextField txt_ScoringType = new JTextField();
		txt_ScoringType.setToolTipText("Scoring Type");
		txt_ScoringType.setBounds(340, 185, 100, 25);
		panel_Right.add(txt_ScoringType);

		JButton btn_ScoringType = new JButton("Save");
		btn_ScoringType.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
						"yyyy-MM-dd");
				Exercise exercise = new Exercise();
				exercise.EXERCISE_NAME = txt_ExerciseName.getText();
				exercise.EXERCISE_COURSE = selectedCourse.CSC_COURSE_Course_ID;
				exercise.EXERCISE_DIFFICULTY_RANGE1 = Integer
						.parseInt(txt_DifficultyRange1.getText());
				exercise.EXERCISE_DIFFICULTY_RANGE2 = Integer
						.parseInt(txt_DifficultyRange2.getText());
				exercise.EXERCISE_CORRECTPT = Integer.parseInt(txt_CorrectPoint
						.getText());
				exercise.EXERCISE_PENALTYPT = Integer.parseInt(txt_PenaltyPoint
						.getText());
				exercise.EXERCISE_SCORINGTYPE = Integer
						.parseInt(txt_ScoringType.getText());
				exercise.EXERCISE_CREATEDBY = LocalSession.GetCurrentUser().UserId;
				try {
					exercise.EXERCISE_STARTDATE = simpleDateFormat
							.parse(txt_StartDate.getText());
					exercise.EXERCISE_ENDDATE = simpleDateFormat
							.parse(txt_EndDate.getText());
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				exercise.EXERCISE_RETRYLIMIT = Integer.parseInt(txt_RetryLimit
						.getText());

				SaveExercise(exercise);

			}
		});
		btn_ScoringType.setBounds(450, 185, 100, 25);
		panel_Right.add(btn_ScoringType);
		// /

		JLabel lbl_topics = new JLabel("Select Topics");
		lbl_topics.setBounds(10, 215, 100, 25);
		panel_Right.add(lbl_topics);

		GetAllTopics();
		jListTopic = new JList(listModelTopic);
		jListTopic.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				GetAllQuestions();
			}
		});
		jListTopic
				.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		jListTopic.setBounds(10, 240, 400, 200);
		panel_Right.add(jListTopic);
		// button to select questions
		JButton btn_SelectQuestion = new JButton(">>");
		btn_SelectQuestion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UpdateSelectedQuestions();
			}
		});
		btn_SelectQuestion.setBounds(310, 450, 50, 25);
		panel_Right.add(btn_SelectQuestion);
		
		JButton btn_RemoveQuestion = new JButton("<<");
		btn_RemoveQuestion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RemoveSelectedQuestions();
			}
		});
		btn_RemoveQuestion.setBounds(310, 500, 50, 25);
		panel_Right.add(btn_RemoveQuestion);
		//
		jListQuestion = new JList(listModelQuestion);
		jListQuestion.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
			}
		});
		jListQuestion.setBounds(10, 450, 300, 100);
		jListQuestion
				.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		panel_Right.add(jListQuestion);

		jListSelectedQuestion = new JList(listModelSelectedQuestion);
		jListSelectedQuestion
				.addListSelectionListener(new ListSelectionListener() {
					public void valueChanged(ListSelectionEvent arg0) {
						// LocalSession.SetCurrentSelectedCourse(listQuestion.getSelectedIndex());
					}
				});
		jListSelectedQuestion.setBounds(400, 450, 300, 100);
		jListSelectedQuestion
				.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		panel_Right.add(jListSelectedQuestion);

	}

	private void GetAllTopics() {
		Course selectedCourse = LocalSession.CourseListModel.get(LocalSession
				.GetCurrentSelectedCourse());
		ArrayList<Topic> listTopics = oracleDataAdapter
				.GetTopicByCourseId(selectedCourse);
		listModelTopic = new DefaultListModel();
		for (Topic topic : listTopics) {
			listModelTopic.addElement(topic.TOPIC_KEYWORD);
		}
		selectedListTopics = listTopics;
	}

	private void GetAllQuestions() {
		// listModelQuestion = new DefaultListModel();
		listModelQuestion.clear();
		Topic topic = new Topic();
		questionBank = new ArrayList<QuestionBank>();
		for (int selectedIndex : jListTopic.getSelectedIndices()) {

			ArrayList<QuestionBank> listQuestionBank = oracleDataAdapter
					.GetQuestionByTopic(selectedListTopics.get(selectedIndex));
			for (QuestionBank temp_questionBank : listQuestionBank) {
				if(txt_DifficultyRange1.getText().equalsIgnoreCase(""))
				{
					txt_DifficultyRange1.setText("1");
				}
				if(txt_DifficultyRange2.getText().equalsIgnoreCase(""))
				{
					txt_DifficultyRange2.setText("6");
				}
				if(temp_questionBank.QUESTIONBANK_DIFFICULTYLEVEL>=Integer.parseInt(txt_DifficultyRange1.getText())
						&& temp_questionBank.QUESTIONBANK_DIFFICULTYLEVEL<=Integer.parseInt(txt_DifficultyRange2.getText()))
				{
				listModelQuestion.addElement(temp_questionBank.QUESTIONBANK_TEXT);
				questionBank.add(temp_questionBank);
				}
			}

		}
	}
	private void GetSelectedQuestions() {
		// listModelQuestion = new DefaultListModel();
		listModelSelectedQuestion.clear();
		
	}
	private void UpdateSelectedQuestions()
	{
		listModelSelectedQuestion.clear();		
		for (int selectedIndex : jListQuestion.getSelectedIndices()) {
			selectedQuestionBank.add(questionBank.get(selectedIndex));
			questionBank.remove(selectedIndex);
		}
		for(QuestionBank question:selectedQuestionBank)
		{
			listModelSelectedQuestion.addElement(question.QUESTIONBANK_TEXT);
		}
		listModelQuestion.clear();
		for(QuestionBank question:questionBank)
		{
			listModelQuestion.addElement(question.QUESTIONBANK_TEXT);
		}
	}
	private void RemoveSelectedQuestions()
	{
		listModelQuestion.clear();		
		for (int selectedIndex : jListSelectedQuestion.getSelectedIndices()) {
			questionBank.add(selectedQuestionBank.get(selectedIndex));
			selectedQuestionBank.remove(selectedIndex);
		}
		for(QuestionBank question:questionBank)
		{
			listModelQuestion.addElement(question.QUESTIONBANK_TEXT);
		}
		listModelSelectedQuestion.clear();
		for(QuestionBank question:selectedQuestionBank)
		{
			listModelSelectedQuestion.addElement(question.QUESTIONBANK_TEXT);
		}
	}
	public void GetAllSelectedQuestions() {
		if (ExerciseId != 0) {
			listModelSelectedQuestion = new DefaultListModel();
			ArrayList<QuestionBank> listQuestionBank = oracleDataAdapter
					.GetQuestionBankForExerciseId(ExerciseId);
			for (QuestionBank questionBank : listQuestionBank) {
				listModelSelectedQuestion
						.addElement(questionBank.QUESTIONBANK_TEXT);
			}
			selectedQuestionBank = listQuestionBank;
		}
	}

	private void SaveExercise(Exercise exercise) {
		// insert/update exercise details
		if (ExerciseId != 0) {
			// updated
		} else {
			// insert
			exercise.EXERCISE_ID=oracleDataAdapter.InsertExerciseDetails(exercise);
			ExerciseId=exercise.EXERCISE_ID;
			//
			if(ExerciseId!=0)
				oracleDataAdapter.InsertExerciseQuestion(exercise,selectedQuestionBank);
		}
	}

}
