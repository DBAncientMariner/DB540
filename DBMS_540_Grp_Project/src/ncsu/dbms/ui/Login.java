package ncsu.dbms.ui;

import ncsu.dbms.core.*;

import java.awt.EventQueue;
import java.awt.Window;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.BorderLayout;

import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JLayeredPane;

@SuppressWarnings("unused")
public class Login {

	private JFrame frame;
	private OracleDb Oracle;
	private JFrame frameStart;
	static Login window;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new Login();
					window.frameStart.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		//initialize();
		initializeFrameStart();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	//this is main login screen
	private void initialize() {
		Oracle=new OracleDb();
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 627, 433);
		frame.setLocationRelativeTo(null); 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
			
		JLabel lbl_Welcome = new JLabel("Welcome CSC 505");
		lbl_Welcome.setBounds(173, 13, 172, 50);
		frame.getContentPane().add(lbl_Welcome);
		
		final JTextField txt_LoginName = new JTextField();
		txt_LoginName.setToolTipText("Login Name");
		txt_LoginName.setBounds(293, 87, 116, 22);
		frame.getContentPane().add(txt_LoginName);
		txt_LoginName.setColumns(10);
		
		JTextField txt_Password = new JTextField();
		txt_Password.setToolTipText("Password");
		txt_Password.setBounds(293, 135, 116, 22);
		frame.getContentPane().add(txt_Password);
		txt_Password.setColumns(10);
		
		final JLabel lbl_ErrorMessage = new JLabel("");
		lbl_ErrorMessage.setForeground(Color.RED);
		lbl_ErrorMessage.setBounds(52, 242, 506, 115);
		frame.getContentPane().add(lbl_ErrorMessage);
		
		JLabel lbl_LoginName = new JLabel("Login Name");
		lbl_LoginName.setBounds(183, 80, 74, 36);
		frame.getContentPane().add(lbl_LoginName);
		
		JLabel lbl_Password = new JLabel("Password");
		lbl_Password.setBounds(185, 128, 86, 36);
		frame.getContentPane().add(lbl_Password);
		
		JButton btn_Login = new JButton("Login");
		btn_Login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lbl_ErrorMessage.setText("");
				User user=new User();
				user.GetUser(txt_LoginName.getText());
				if(user.UserId==0)
				{
					lbl_ErrorMessage.setText("No such user exists.");
				}
				else
					lbl_ErrorMessage.setText(user.UserFName);
			}
		});
		btn_Login.setBounds(293, 180, 97, 25);
		frame.getContentPane().add(btn_Login);
		
		//back button
		JButton btn_LoginBack = new JButton("Back");
		btn_LoginBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				initializeFrameStart();
				window.frameStart.setVisible(true);
				window.frame.setVisible(false);
			}
		});
		btn_LoginBack.setBounds(180, 180, 97, 25);
		frame.getContentPane().add(btn_LoginBack);
		//initializeFrameStart();
	}	

	//this is main opening screen.
	private void initializeFrameStart()
	{
		
		frameStart=new JFrame();
		frameStart.setResizable(false);
		frameStart.setBounds(100,100,627,433);
		frameStart.setLocationRelativeTo(null); 
		frameStart.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameStart.getContentPane().setLayout(null);
		
		
		JLabel lbl_Welcome = new JLabel("Welcome CSC 505");
		lbl_Welcome.setBounds(210, 13, 172, 50);
		frameStart.getContentPane().add(lbl_Welcome);
		
		//Login button
		JButton btn_Login = new JButton("Login");
		btn_Login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				initialize();
				window.frame.setVisible(true);
				window.frameStart.setVisible(false);
			}
		});
		btn_Login.setBounds(250, 100, 140, 25);
		frameStart.getContentPane().add(btn_Login);
		
		
		//Create User button
		JButton btn_CreateUser = new JButton("Create User");
		btn_CreateUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		btn_CreateUser.setBounds(250, 150, 140, 25);
		frameStart.getContentPane().add(btn_CreateUser);
		
		
		//Exit UI
		JButton btn_Exit = new JButton("Exit");
		btn_Exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btn_Exit.setBounds(250, 200, 140, 25);
		frameStart.getContentPane().add(btn_Exit);
	}
}
