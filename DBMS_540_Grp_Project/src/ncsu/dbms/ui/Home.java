package ncsu.dbms.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import ncsu.dbms.core.LocalSession;
import ncsu.dbms.core.OracleDataAdapter;

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
				ListHomework listHomework=new ListHomework();
				listHomework.setVisible(true);
				//AddHomework addHomework = new AddHomework();
				//addHomework.setVisible(true);
				frame.setVisible(false);
			}
		});
		panel_Center.add(btnAddHomework);

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
		lbl_AddCourse.setBounds(100, 300, 100, 30);
		homePanel.add(lbl_AddCourse);

		JLabel lbl_AddToken = new JLabel("Provide a token");
		lbl_AddToken.setBounds(100, 340, 100, 30);
		homePanel.add(lbl_AddToken);

		lbl_ErrorToken = new JLabel("");
		lbl_ErrorToken.setBounds(100, 390, 500, 30);
		lbl_ErrorToken.setForeground(Color.red);
		homePanel.add(lbl_ErrorToken);

		final JTextField txt_Token = new JTextField();
		txt_Token.setBounds(250, 340, 100, 30);
		txt_Token.setToolTipText("Token");
		homePanel.add(txt_Token);

		JButton btn_AddCourse = new JButton("Add Course");
		btn_AddCourse.setBounds(370, 340, 100, 30);
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
		OracleDataAdapter oracleDataAdapter = new OracleDataAdapter();
		ArrayList<ncsu.dbms.core.Course> listCourse = oracleDataAdapter
				.GetCourseForStudent(LocalSession.GetCurrentUser());
		for (ncsu.dbms.core.Course course : listCourse) {
			// listModel= new DefaultListModel();
			listModel.addElement(course.CSC_COURSE_Course_Name);
		}
		LocalSession.CourseListModel = listCourse;
	}
}
