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
	private JFrame frameRegistration;
	static Login window;
	private JTextField txt_UserFName;
	private JTextField txt_UserMName;
	private JTextField txt_UserLName;
	private JTextField txt_UserEmail;
	private JTextField txt_UserPassword;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new Login();
					//window.frameRegistration.setVisible(true);
					//window.frameStart.setVisible(true);
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
		//initializeFrameRegistration();
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
		txt_LoginName.setToolTipText("Login Name/Email");
		txt_LoginName.setBounds(293, 87, 116, 22);
		frame.getContentPane().add(txt_LoginName);
		txt_LoginName.setColumns(10);
		
		final JTextField txt_Password = new JTextField();
		txt_Password.setToolTipText("Password");
		txt_Password.setBounds(293, 135, 116, 22);
		frame.getContentPane().add(txt_Password);
		txt_Password.setColumns(10);
		
		final JLabel lbl_ErrorMessage = new JLabel("");
		lbl_ErrorMessage.setForeground(Color.RED);
		lbl_ErrorMessage.setBounds(52, 242, 506, 115);
		frame.getContentPane().add(lbl_ErrorMessage);
		
		JLabel lbl_LoginName = new JLabel("Login Name/Email");
		lbl_LoginName.setBounds(150, 80, 104, 36);
		frame.getContentPane().add(lbl_LoginName);
		
		JLabel lbl_Password = new JLabel("Password");
		lbl_Password.setBounds(150, 128, 104, 36);
		frame.getContentPane().add(lbl_Password);
		
		JButton btn_Login = new JButton("Login");
		btn_Login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lbl_ErrorMessage.setText("");
				//testing
				txt_Password.setText("sgarg5");
				txt_LoginName.setText("sgarg5");
				//testing
				if(!txt_Password.getText().trim().equalsIgnoreCase("") && !txt_LoginName.getText().trim().equalsIgnoreCase(""))
				{
					User user=new User();
					if(User.IsValidUser(txt_LoginName.getText().trim(), txt_Password.getText().trim()))
					{
						//user exists
						user=User.GetUser(txt_LoginName.getText().trim());
						LocalSession.SetCurrentUser(user);
						frame.setVisible(false);
						Home window = new Home();
						
					}
					else
					{
						//user doesn't exist
						lbl_ErrorMessage.setText("No such user exists.");
					}
				}
				else if(txt_LoginName.getText().trim().equalsIgnoreCase("") )
				{
					lbl_ErrorMessage.setText("Provide email id");
				}
				else if(txt_Password.getText().trim().equalsIgnoreCase("") )
				{
					lbl_ErrorMessage.setText("Provide password");
				}
				
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
				initializeFrameRegistration();
				window.frameRegistration.setVisible(true);
				window.frameStart.setVisible(false);
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
		frameStart.setVisible(true);
	}

	//this is main opening screen.
	private void initializeFrameRegistration()
	{
		frameRegistration=new JFrame();
		frameRegistration.setResizable(false);
		frameRegistration.setBounds(100,100,627,433);
		frameRegistration.setLocationRelativeTo(null); 
		frameRegistration.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameRegistration.getContentPane().setLayout(null);
		
		
		JLabel lbl_Welcome = new JLabel("Welcome CSC 505 - Registration");
		lbl_Welcome.setBounds(169, 13, 247, 30);
		frameRegistration.getContentPane().add(lbl_Welcome);
		
		JLabel lblUserFName = new JLabel("First Name *");
		lblUserFName.setBounds(169, 74, 112, 25);
		frameRegistration.getContentPane().add(lblUserFName);
		
		txt_UserFName = new JTextField();
		txt_UserFName.setToolTipText("First Name");
		txt_UserFName.setBounds(300, 75, 116, 22);
		frameRegistration.getContentPane().add(txt_UserFName);
		txt_UserFName.setColumns(10);
		
		JLabel lbl_UserMName = new JLabel("Middle Name ");
		lbl_UserMName.setBounds(169, 121, 112, 25);
		frameRegistration.getContentPane().add(lbl_UserMName);
		
		txt_UserMName = new JTextField();
		txt_UserMName.setToolTipText("Middle Name");
		txt_UserMName.setColumns(10);
		txt_UserMName.setBounds(300, 122, 116, 22);
		frameRegistration.getContentPane().add(txt_UserMName);
		
		JLabel lbl_UserLName = new JLabel("Last Name *");
		lbl_UserLName.setBounds(169, 170, 112, 25);
		frameRegistration.getContentPane().add(lbl_UserLName);
		
		txt_UserLName = new JTextField();
		txt_UserLName.setToolTipText("Last Name");
		txt_UserLName.setColumns(10);
		txt_UserLName.setBounds(300, 171, 116, 22);
		frameRegistration.getContentPane().add(txt_UserLName);
		
		JLabel lbl_UserEmail = new JLabel("Email *");
		lbl_UserEmail.setBounds(169, 217, 112, 25);
		frameRegistration.getContentPane().add(lbl_UserEmail);
		
		txt_UserEmail = new JTextField();
		txt_UserEmail.setToolTipText("Email");
		txt_UserEmail.setColumns(10);
		txt_UserEmail.setBounds(300, 218, 116, 22);
		frameRegistration.getContentPane().add(txt_UserEmail);
		
		JLabel lbl_UserPassword = new JLabel("Password *");
		lbl_UserPassword.setBounds(169, 263, 112, 25);
		frameRegistration.getContentPane().add(lbl_UserPassword);
		
		txt_UserPassword = new JTextField();
		txt_UserPassword.setToolTipText("Password");
		txt_UserPassword.setColumns(10);
		txt_UserPassword.setBounds(300, 266, 116, 22);
		frameRegistration.getContentPane().add(txt_UserPassword);
		
		final JLabel lbl_ErrorMessage = new JLabel("");
		lbl_ErrorMessage.setForeground(Color.RED);
		lbl_ErrorMessage.setBounds(24, 347, 567, 41);
		frameRegistration.getContentPane().add(lbl_ErrorMessage);
		
		JButton btn_UserSave = new JButton("Create User");
		btn_UserSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				User user=new User();
				user.UserFName=txt_UserFName.getText();
				user.UserMName=txt_UserMName.getText();
				user.UserLName=txt_UserLName.getText();
				user.UserEmail=txt_UserEmail.getText();
				user.UserPassword=txt_UserPassword.getText();
				user.UserIsActive=true;
				if(User.AddUser(user))
					lbl_ErrorMessage.setText("User created!");
				else
					lbl_ErrorMessage.setText("Error occured,Email id already exist in system! Try again!");
			}
		});
		btn_UserSave.setBounds(300, 314, 116, 25);
		frameRegistration.getContentPane().add(btn_UserSave);
		
		JButton btn_RegistrationBack = new JButton("Back");
		btn_RegistrationBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				initializeFrameStart();
				window.frameStart.setVisible(true);
				window.frameRegistration.setVisible(false);
			}
		});
		btn_RegistrationBack.setBounds(169, 314, 116, 25);
		frameRegistration.getContentPane().add(btn_RegistrationBack);
		
		
		
		
	}
}
