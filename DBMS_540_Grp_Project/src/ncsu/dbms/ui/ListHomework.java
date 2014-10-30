/**
 * 
 */
package ncsu.dbms.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;

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
	final JPanel panel_Right = new JPanel();
	JList jListHomework;
	OracleDataAdapter oracleDataAdapter = new OracleDataAdapter();
	DefaultListModel listModelHomework = new DefaultListModel();
	ArrayList<Exercise> listExercise = new ArrayList<Exercise>();

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
		
		DisplayAllHomework();
	}

	public void DisplayAllHomework() {
		JLabel lbl_topics = new JLabel("Select Homework");
		lbl_topics.setBounds(10, 10, 100, 25);
		panel_Right.add(lbl_topics);

		GetAllExerciseForCurrentCourse();
		jListHomework = new JList(listModelHomework);
		jListHomework.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {

			}
		});
		jListHomework.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jListHomework.setBounds(10, 40, 500, 400);
		panel_Right.add(jListHomework);

		JButton btn_EditButton = new JButton("Edit");
		btn_EditButton.setBounds(550, 50, 80, 33);
		btn_EditButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					if(jListHomework.getSelectedIndex()!=-1)
					{
						int exerciseId = listExercise.get(jListHomework
								.getSelectedIndex()).EXERCISE_ID;
						if (User.IsFaculty(LocalSession.GetCurrentUser())) {
							AddHomework addHomework = new AddHomework(exerciseId,
									true);
							addHomework.setVisible(true);
							setVisible(false);
						} else if (User.IsTA(LocalSession.GetCurrentUser())) {
							AddHomework addHomework = new AddHomework(exerciseId,
									false);
							addHomework.setVisible(true);
							setVisible(false);
						}
					}
			}
		});
		panel_Right.add(btn_EditButton);
		JButton btn_AddButton = new JButton("Add");
		btn_AddButton.setBounds(550, 100, 80, 33);
		btn_AddButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
						if (User.IsFaculty(LocalSession.GetCurrentUser())) {
							AddHomework addHomework = new AddHomework(0,
									true);
							addHomework.setVisible(true);
							setVisible(false);
						} else if (User.IsTA(LocalSession.GetCurrentUser())) {
							AddHomework addHomework = new AddHomework(0,
									false);
							addHomework.setVisible(true);
							setVisible(false);
						}
			}
		});
		panel_Right.add(btn_AddButton);

	}

	public void GetAllExerciseForCurrentCourse() {

		listExercise = oracleDataAdapter.GetExerciseForCourseId(LocalSession
				.getCurrentSelectedCourseObject().CSC_COURSE_Course_ID);
		for (Exercise exercise : listExercise) {
			listModelHomework.addElement(exercise.EXERCISE_NAME);
		}
	}

}
