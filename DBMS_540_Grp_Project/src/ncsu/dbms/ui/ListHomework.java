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
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import ncsu.dbms.core.*;
/**
 * @author ravi
 *
 */
public class ListHomework extends JFrame {

	private JPanel contentPane;
	private JPanel panel_Right;
	JList jListHomework;
	OracleDataAdapter oracleDataAdapter = new OracleDataAdapter();
	DefaultListModel listModelHomework = new DefaultListModel();
	ArrayList<Exercise> listExercise=new ArrayList<Exercise>();
	/**
	 * Create the frame.
	 */
	public ListHomework() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.setBounds(100, 100, 900, 700);
		this.setLocationRelativeTo(null);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
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
		DisplayAllHomework();
	}
	public void DisplayAllHomework()
	{
		JLabel lbl_topics = new JLabel("Select Homework");
		lbl_topics.setBounds(10, 215, 100, 25);
		panel_Right.add(lbl_topics);

		GetAllTopics();
		jListHomework = new JList(listModelHomework);
		jListHomework.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				
			}
		});
		jListHomework
				.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		jListHomework.setBounds(10,10, 600, 600);
		panel_Right.add(jListHomework);

	}
	public void GetAllExerciseForCourse(int courseId)
	{
		ArrayList<Exercise> listExercise=new ArrayList<Exercise>();
		oracleDatabase
	}

}
