package ncsu.dbms.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ncsu.dbms.core.LocalSession;
import ncsu.dbms.core.User;

public class Notification extends JFrame {

	private static final long serialVersionUID = 4620833118495352591L;
	private JPanel contentPane;
	final JPanel panel_Right = new JPanel();

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public Notification() {
		//Notification frame = new Notification();
		//frame.setVisible(true);
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
		
		JLabel lbl_UserName=new JLabel("");
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
		//add notification
		
		if(User.IsFaculty(LocalSession.GetCurrentUser()))
			GetNotificationFaculty();
		else
			GetNotificationStudent();
		
		
	}
	public void HideNotificationFrame(JFrame frame)
	{
		frame.setVisible(true);
		this.setVisible(false);
	}
	public void GetNotificationStudent()
	{
		JLabel welcomeText = new JLabel("Notifications");
		welcomeText.setBounds(12, 10, 187, 33);
		panel_Right.add(welcomeText);
		
		ArrayList<ncsu.dbms.core.Notification> listNotification=new ArrayList<ncsu.dbms.core.Notification>();
		listNotification=ncsu.dbms.core.Notification.GetDummyList();
		int rownumber=1;
		for(ncsu.dbms.core.Notification notification:listNotification)
		{
			JLabel notificationText = new JLabel(notification.NotificationTitle);
			notificationText.setBounds(12, 10+rownumber*30, 187, 33);
			panel_Right.add(notificationText);
			JLabel notificationDue=new JLabel(notification.NotificationDue);
			notificationDue.setBounds(200, 10+rownumber*30, 187, 33);
			panel_Right.add(notificationDue);
			rownumber++;
		}
	}
	public void GetNotificationFaculty()
	{
		JLabel welcomeText = new JLabel("Notifications");
		welcomeText.setBounds(12, 10, 187, 33);
		panel_Right.add(welcomeText);
		
		ArrayList<ncsu.dbms.core.Notification> listNotification=new ArrayList<ncsu.dbms.core.Notification>();
		listNotification=ncsu.dbms.core.Notification.GetDummyList();
		int rownumber=1;
		for(ncsu.dbms.core.Notification notification:listNotification)
		{
			JLabel notificationText = new JLabel(notification.NotificationTitle);
			notificationText.setBounds(12, 10+rownumber*30, 187, 33);
			panel_Right.add(notificationText);
			rownumber++;
		}
	}
	

}
