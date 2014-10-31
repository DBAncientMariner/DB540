package ncsu.dbms.ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import ncsu.dbms.core.Notification;
import ncsu.dbms.core.NotificationData;

public class NotificationView {

	private JPanel contentPane;
	final JPanel panel_Right = new JPanel();
	private JFrame frame;

	/**
	 * Create the frame.
	 */
	public NotificationView() {
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

		openNotification(panel_Right);
		frame.getContentPane().add(scrollpane);
		frame.setVisible(true);
	}
	
	public void openNotification(JPanel panel_right) {
		panel_right.setLayout(null);
		List<Notification> notificationList = NotificationData.getNotifications();
		if(notificationList != null && !notificationList.isEmpty()) {
			JLabel welcomeText = new JLabel("Notifications");
			welcomeText.setBounds(10, 10, 200, 35);
			panel_right.add(welcomeText);
			
			int i = 55;
			for (Notification notification : notificationList) {
				JLabel notificationText = new JLabel(notification.getNotificationTitle());
				notificationText.setBounds(10, i, 350, 35);
				panel_right.add(notificationText);
				i += 45;
			}
		} else {
			JLabel notificationText = new JLabel("No unread notifications");
			notificationText.setBounds(10, 55, 200, 35);
			panel_right.add(notificationText);
		}
	}
}
