/**
 * 
 */
package ncsu.dbms.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import ncsu.dbms.core.LocalSession;

/**
 * @author ravi
 *
 */
public class AddHomework {

	private JPanel contentPane;
	private JFrame frame;
	final JPanel panel_Right = new JPanel();
	JList listTopic;
	DefaultListModel listModelTopic= new DefaultListModel();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new AddHomework();
					//frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AddHomework() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 900, 700);
		frame.setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
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

		OpenHomeworkPage(panel_Right);

		frame.setVisible(true);
	}
	private void GetAllTopics()
	{
		
	}
	public void OpenHomeworkPage(JPanel homeworkPanel) {
		// homePanel.setBackground(Color.gray);
				JLabel lbl_SelectCourse = new JLabel("Select Topic");
				lbl_SelectCourse.setBounds(100, 10, 300, 30);
				homeworkPanel.add(lbl_SelectCourse);

				// load all the topic
				listTopic = new JList(listModelTopic);
				listTopic.addListSelectionListener(new ListSelectionListener() {
							public void valueChanged(ListSelectionEvent arg0) {
								LocalSession.SetCurrentSelectedCourse((String)listTopic.getSelectedValue());
							}
						});
				listTopic.setBounds(100, 50, 300, 100);
				homeworkPanel.add(listTopic);
				//Component data=new ArrayList<String>();
	}

}
