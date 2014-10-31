package ncsu.dbms.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import ncsu.dbms.core.Course;
import ncsu.dbms.core.LocalSession;
import ncsu.dbms.core.OracleDataAdapter;
import ncsu.dbms.core.Topic;
import ncsu.dbms.core.User;

public class Home {

	private JPanel contentPane;
	private JFrame frame;
	private JLabel lbl_ErrorToken = new JLabel();
	String[] data = { "" };
	final JPanel panel_Right = new JPanel();
	JList listCourses;
	DefaultListModel listModel = new DefaultListModel();

	/**
	 * Launch the application.
	 */
	// public static void main(String[] args) {
	// EventQueue.invokeLater(new Runnable() {
	// public void run() {
	// try {
	// new Home();
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }
	// });
	// }

	/**
	 * Create the frame.
	 */
	public Home() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 1100, 800);
		frame.setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel_Center = new JPanel();
		panel_Center.setBounds(12, 141, 211, 280);
		contentPane.add(panel_Center);
		panel_Center.setLayout(null);

		JButton btn_ViewScore = new JButton("ViewScore");
		btn_ViewScore.setBounds(12, 47, 187, 33);
		btn_ViewScore.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				new ViewScore();
			}
		});
		panel_Center.add(btn_ViewScore);

		JButton btn_AttemptHomework = new JButton("Attempt Homework");
		btn_AttemptHomework.setBounds(12, 83, 187, 33);
		btn_AttemptHomework.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new AttemptExercise(0);
				frame.setVisible(false);
			}
		});
		panel_Center.add(btn_AttemptHomework);

		JButton btn_ViewSubmissions = new JButton("Past Submissions");
		btn_ViewSubmissions.setBounds(12, 125, 187, 33);
		btn_ViewSubmissions.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				new PastSubmissions();
			}
		});
		panel_Center.add(btn_ViewSubmissions);

		JButton btn_Notification = new JButton("Notification");
		btn_Notification.setBounds(12, 161, 187, 33);
		btn_Notification.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Notification notification = new Notification();
				notification.setVisible(true);
				frame.setVisible(false);
			}
		});
		panel_Center.add(btn_Notification);
		JButton btnAddHomework = new JButton("Add Homework");
		btnAddHomework.setBounds(12, 200, 187, 33);
		btnAddHomework.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (listCourses.getSelectedIndex() >= 0 && (User.IsFacultyOnCourse(LocalSession.GetCurrentUser(),LocalSession.getCurrentSelectedCourseObject())  || User.IsTAOnCourse(LocalSession.GetCurrentUser(),LocalSession.getCurrentSelectedCourseObject()))) {
					ListHomework listHomework = new ListHomework();
					listHomework.setVisible(true);
					frame.setVisible(false);
				}
			}
		});
		panel_Center.add(btnAddHomework);

		JButton btnReport = new JButton("Report");
		btnReport.setBounds(12, 240, 187, 33);
		btnReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (User.IsFacultyOnCourse(LocalSession.GetCurrentUser(),LocalSession.getCurrentSelectedCourseObject()) || User.IsTAOnCourse(LocalSession.GetCurrentUser(),LocalSession.getCurrentSelectedCourseObject())) {
					Report report = new Report();
					report.setVisible(true);
					frame.setVisible(false);
				}
				
			}
		});
		panel_Center.add(btnReport);

		JPanel panel_Bottom = new JPanel();
		panel_Bottom.setBounds(12, 390, 211, 224);
		contentPane.add(panel_Bottom);

		JPanel panel_Top = new JPanel();
		panel_Top.setBounds(12, 13, 211, 128);
		contentPane.add(panel_Top);
		panel_Top.setLayout(null);

		panel_Right.setBounds(230, 13, 840, 750);
		contentPane.add(panel_Right);
		panel_Right.setLayout(null);

		JLabel lbl_UserName = new JLabel("");
		lbl_UserName.setBounds(12, 10, 187, 33);
		panel_Top.add(lbl_UserName);
		panel_Right.setLayout(null);
		JButton btnHome = new JButton("Home");
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

			}
		});
		btnHome.setBounds(12, 46, 187, 33);
		panel_Top.add(btnHome);

		JButton btn_Logout = new JButton("Logout");
		btn_Logout.setBounds(12, 82, 187, 33);
		panel_Top.add(btn_Logout);
		btn_Logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Login();
				frame.setVisible(false);
				// LocalSession.SetCurrentUser(NULL);
			}
		});

		OpenHomePage(panel_Right);

		if (User.IsFaculty(LocalSession.GetCurrentUser())) {
			EditCourse(panel_Right);
		}
		frame.setVisible(true);
	}

	// handlers home button click
	public void OpenHomePage(JPanel homePanel) {
		homePanel.removeAll();

		// homePanel.setBackground(Color.gray);
		JLabel lbl_SelectCourse = new JLabel("Select a course");
		lbl_SelectCourse.setBounds(100, 10, 300, 30);
		homePanel.add(lbl_SelectCourse);

		// load all the courses
		listCourses = new JList(listModel);
		listCourses.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				LocalSession.SetCurrentSelectedCourse(listCourses
						.getSelectedIndex());
			}
		});
		listCourses.setBounds(100, 50, 300, 100);
		homePanel.add(listCourses);
		AddCourse();
		// Component data=new ArrayList<String>();

		JLabel lbl_AddCourse = new JLabel("Add a course");
		lbl_AddCourse.setBounds(100, 200, 100, 30);
		homePanel.add(lbl_AddCourse);

		JLabel lbl_AddToken = new JLabel("Provide a token");
		lbl_AddToken.setBounds(100, 250, 100, 30);
		homePanel.add(lbl_AddToken);

		lbl_ErrorToken = new JLabel("");
		lbl_ErrorToken.setBounds(100, 300, 500, 30);
		lbl_ErrorToken.setForeground(Color.red);
		homePanel.add(lbl_ErrorToken);

		final JTextField txt_Token = new JTextField();
		txt_Token.setBounds(250, 250, 100, 30);
		txt_Token.setToolTipText("Token");
		homePanel.add(txt_Token);

		JButton btn_AddCourse = new JButton("Add Course");
		btn_AddCourse.setBounds(370, 250, 100, 30);
		btn_AddCourse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AddCourse(txt_Token.getText());
			}
		});
		homePanel.add(btn_AddCourse);
	}

	private void AddCourse(String token) {
		OracleDataAdapter oracleDataAdapter = new OracleDataAdapter();
		{
			ArrayList<ncsu.dbms.core.Course> listCourse = oracleDataAdapter
					.GetCourseForToken(token);
			if (listCourse != null && listCourse.size() == 0) {
				lbl_ErrorToken.setText("Invalid course id");
			} else if (listCourse.get(0).CSC_COURSE_EndDate
					.compareTo(new Date()) <= 0) {
				lbl_ErrorToken.setText("Course over, cannot register.");
			} else if (listCourse.get(0).CSC_COURSE_Max_Enroll_No <= listCourse
					.get(0).CSC_COURSE_Number_Of_Students) {
				lbl_ErrorToken.setText("Course full, cannot register.");
			} else {
				if (oracleDataAdapter.AddStudentToCourse(token,
						LocalSession.GetCurrentUser())) {
					// print success
					lbl_ErrorToken.setText("Course Added");
					listModel = new DefaultListModel();
					AddCourse();
				} else {
					lbl_ErrorToken.setText("Error occured.");
					// print failure
				}

			}
		}

	}

	private void RefreshList(String data) {
		listModel.addElement(data);

	}

	private void AddCourse() {
		listModel.clear();
		OracleDataAdapter oracleDataAdapter = new OracleDataAdapter();
		ArrayList<Course> listCourse = oracleDataAdapter.GetAllCourseForUser(LocalSession.GetCurrentUser());
		for (ncsu.dbms.core.Course course : listCourse) {
			listModel.addElement(course.CSC_COURSE_Course_Name);
		}
		LocalSession.CourseListModel = listCourse;
	}

	private void EditCourse(JPanel panel) {
		JLabel lblCourseAdd = new JLabel("Add a course");
		lblCourseAdd.setBounds(100, 350, 500, 30);
		panel.add(lblCourseAdd);

		JLabel lbl_CourseName = new JLabel("Course Name");
		lbl_CourseName.setBounds(100, 390, 150, 30);
		panel.add(lbl_CourseName);

		final JTextField txt_CourseName = new JTextField();
		txt_CourseName.setBounds(250, 390, 150, 30);
		txt_CourseName.setToolTipText("Course Name");
		panel.add(txt_CourseName);

		JLabel lbl_CourseStartDate = new JLabel("Course Start Date");
		lbl_CourseStartDate.setBounds(400, 390, 150, 30);
		panel.add(lbl_CourseStartDate);

		final JTextField txt_CourseStartDate = new JTextField();
		txt_CourseStartDate.setBounds(550, 390, 150, 30);
		txt_CourseStartDate.setToolTipText("Course Start Date");
		panel.add(txt_CourseStartDate);

		JLabel lbl_CourseEndDate = new JLabel("Course End Date");
		lbl_CourseEndDate.setBounds(100, 440, 150, 30);
		panel.add(lbl_CourseEndDate);

		final JTextField txt_CourseEndDate = new JTextField();
		txt_CourseEndDate.setBounds(250, 440, 150, 30);
		txt_CourseEndDate.setToolTipText("Course End Date");
		panel.add(txt_CourseEndDate);

		JLabel lbl_MaxEnrollment = new JLabel("Max Enrollment");
		lbl_MaxEnrollment.setBounds(400, 440, 150, 30);
		panel.add(lbl_MaxEnrollment);

		final JTextField txt_MaxEnrollment = new JTextField();
		txt_MaxEnrollment.setBounds(550, 440, 150, 30);
		txt_MaxEnrollment.setToolTipText("Max Enrollment");
		panel.add(txt_MaxEnrollment);

		JLabel lbl_CourseToken = new JLabel("Course Token");
		lbl_CourseToken.setBounds(100, 480, 150, 30);
		panel.add(lbl_CourseToken);

		final JTextField txt_CourseToken = new JTextField();
		txt_CourseToken.setBounds(250, 480, 150, 30);
		txt_CourseToken.setToolTipText("Course Token");
		panel.add(txt_CourseToken);
		
		JLabel lbl_CourseLevel = new JLabel("Course Level");
		lbl_CourseLevel.setBounds(400, 480, 150, 30);
		panel.add(lbl_CourseLevel);

		final JTextField txt_CourseLevel = new JTextField();
		txt_CourseLevel.setBounds(550, 480, 150, 30);
		txt_CourseLevel.setToolTipText("1 for UG, 2 for Graduate");
		panel.add(txt_CourseLevel);
		
		final ArrayList<Topic> arrayListTopic=new ArrayList<Topic>();
		DefaultListModel listModel = new DefaultListModel();
		for(Topic topic :LoadTopics())
		{
			arrayListTopic.add(topic);
		}
		listModel.clear();
		for(Topic topic:arrayListTopic)
		{
			listModel.addElement(topic.TOPIC_KEYWORD);
		}
		final JList jListTopic = new JList(listModel);
		jListTopic.setBounds(250, 550, 200, 300);
		jListTopic.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
			}
		});
		jListTopic.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		
		//panel.add(new JScrollPane(jListTopic));
		panel.add(jListTopic);
		
		
		final JLabel lbl_CourseTokenError = new JLabel("");
		lbl_CourseTokenError.setBounds(100, 750, 450, 30);
		lbl_CourseTokenError.setForeground(Color.red);
		panel.add(lbl_CourseTokenError);

		JButton btn_CreateButton = new JButton("Create Course");
		btn_CreateButton.setBounds(250, 350, 150, 30);
		btn_CreateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
						"yyyy-MM-dd");
				Course course = new Course();
				course.CSC_COURSE_Course_Name = txt_CourseName.getText();
				try {
					course.CSC_COURSE_StartDate = simpleDateFormat
							.parse(txt_CourseStartDate.getText());
					course.CSC_COURSE_EndDate = simpleDateFormat
							.parse(txt_CourseEndDate.getText());
					course.CSC_COURSE_Max_Enroll_No = Integer
							.parseInt(txt_MaxEnrollment.getText());
					course.CSC_COURSE_Number_Of_Students = 0;
					course.CSC_COURSE_token = txt_CourseToken.getText();
					course.CSC_COURSE_LEVEL.CSC_COURSE_LEVEL_LEVEL_ID=Integer.parseInt(txt_CourseLevel.getText());
					for(int index:jListTopic.getSelectedIndices())
					{
						course.CourseTopic.add(arrayListTopic.get(index));
					}
					OracleDataAdapter oracleDataAdapter = new OracleDataAdapter();

					if (oracleDataAdapter.InsertCourse(course)) {
						txt_CourseName.setText("");
						txt_CourseStartDate.setText("");
						txt_CourseEndDate.setText("");
						txt_MaxEnrollment.setText("");
						txt_CourseToken.setText("");
						lbl_CourseTokenError.setText("Course Created!");
						//AddCourse(course.CSC_COURSE_token);
					} else {
						lbl_CourseTokenError.setText("Error");
					}
				} catch (Exception e) {

				}

				// create course
			}
		});
		panel.add(btn_CreateButton);

	}

	/**
	 * @param listTopic
	 */
	private ArrayList<Topic> LoadTopics() {
		// TODO Auto-generated method stub
		OracleDataAdapter oracleDataAdapter = new OracleDataAdapter();
		return oracleDataAdapter.GetTopic();
		
	}
}
