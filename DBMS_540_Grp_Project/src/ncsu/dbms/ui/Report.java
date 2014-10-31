/**
 * 
 */
package ncsu.dbms.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import ncsu.dbms.core.Course;
import ncsu.dbms.core.Exercise;
import ncsu.dbms.core.LocalSession;
import ncsu.dbms.core.OracleDataAdapter;
import ncsu.dbms.core.User;

/**
 * @author ravi
 *
 */
public class Report extends JFrame {

	private JPanel contentPane;
	final JPanel panel_Right = new JPanel();
	JTextArea txt_Report=new JTextArea();
	OracleDataAdapter oracleDataAdapter = new OracleDataAdapter();
	/**
	 * Create the frame.
	 */
	public Report() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 700);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
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
				Home home=new Home();
				home.OpenHomePage(contentPane);
				setVisible(false);
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
				setVisible(false);
				// LocalSession.SetCurrentUser(NULL);
			}
		});

		ShowReport(panel_Right);
	}

	void ShowReport(JPanel panel) {
		
	
		
		DefaultListModel listModelReport = new DefaultListModel();
		listModelReport.addElement("Report1");
		listModelReport.addElement("Report2");
		listModelReport.addElement("Report3");
		listModelReport.addElement("Report4");
		listModelReport.addElement("Report5");
		final JList jListReport = new JList(listModelReport);
		jListReport.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {

				switch (jListReport.getSelectedIndex()) {
				case 0:
					GetReport1();
					break;
				case 1:
					GetReport2();
					break;
				case 2:
					GetReport3();
					break;
				case 3:
					GetReport4();
					break;
				case 4:
					GetReport5();
					break;
				default:
					break;
				}
			}
		});
		jListReport.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jListReport.setBounds(250, 13, 199, 124);
		panel_Right.add(jListReport);
		
		txt_Report=new JTextArea();
		txt_Report.setBounds(250, 188, 594, 431);
		panel_Right.add(txt_Report);
	}

	public void GetReport1() {
		txt_Report.setText("");
		String outputString="";
		outputString=outputString+"---students who did not take homework 1----\n";
		outputString=outputString+"Last Name First Name \n";
		ArrayList<User> listUser=oracleDataAdapter.GetReport1();
		for(User user:listUser)
		{
			outputString=outputString+user.UserLName+"  "+user.UserFName +"\n";
		}
		txt_Report.setText(outputString);
	}

	public void GetReport2() {
		txt_Report.setText("");
		String outputString="";
		outputString=outputString+"--- students who scored the maximum score on the first attempt for homework 1----\n";
		outputString=outputString+"Last Name First Name \n";
		ArrayList<User> listUser=oracleDataAdapter.GetReport2();
		for(User user:listUser)
		{
			outputString=outputString+user.UserLName+"  "+user.UserFName +"\n";
		}
		txt_Report.setText(outputString);
	}

	public void GetReport3() {
		txt_Report.setText("");
		String outputString="";
		outputString=outputString+"---students who scored the maximum score on the first attempt for each homework----\n";
		outputString=outputString+"Last Name First Name \n";
		Course course = LocalSession.getCurrentSelectedCourseObject();
		ArrayList<User> listUser=oracleDataAdapter.GetReport3(course.CSC_COURSE_Course_ID);
		for(User user:listUser)
		{
			outputString=outputString+user.UserLName+" "+user.UserFName +"\n";
		}
		txt_Report.setText(outputString);
	}

	public void GetReport4() {
		txt_Report.setText("");
		String outputString="";
		outputString=outputString+"-------------Average Score across all homework--------\n";
		outputString=outputString+"Last Name First Name \t Average Score \n";
		Course course = LocalSession.getCurrentSelectedCourseObject();
		ArrayList<User> listUser=oracleDataAdapter.GetReport4Average(course.CSC_COURSE_Course_ID);
		for(User user:listUser)
		{
			outputString=outputString+user.UserLName+" "+user.UserFName +"\t"+user.AverageScore +"\n";
		}
		outputString=outputString+"-------------Total score for each homework--------\n";
		Course course1 = LocalSession.getCurrentSelectedCourseObject();
		listUser=oracleDataAdapter.GetReport4Total(course1.CSC_COURSE_Course_ID);
		outputString=outputString+"Last Name First Name \tExercise Name : Average Score \n";
		for(User user:listUser)
		{
			outputString=outputString+user.UserLName+" "+user.UserFName +"\t"+user.ExerciseName+" : "+user.AverageScore +"\n";
		}
		txt_Report.setText(outputString);
	}

	public void GetReport5() {
		txt_Report.setText("");
		String outputString="";
		outputString=outputString+"---For each homework,average number of attempts----\n";
		outputString=outputString+"Exercise Name \t Average Attempt \n";
		Course course = LocalSession.getCurrentSelectedCourseObject();
		ArrayList<Exercise> listExercise=oracleDataAdapter.GetReport5(course.CSC_COURSE_Course_ID);
		for(Exercise exercise:listExercise)
		{
			outputString=outputString+exercise.EXERCISE_NAME+"\t" +exercise.AverageAttempt + "\n";
		}
		txt_Report.setText(outputString);
	}
}
