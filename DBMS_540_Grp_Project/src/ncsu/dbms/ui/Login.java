package ncsu.dbms.ui;

import ncsu.dbms.core.*;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.BorderLayout;

import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login {

	private JFrame frame;
	private JLabel lbl_Welcome;
	private JTextField txt_LoginName;
	private JTextField txt_Password;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frame.setVisible(true);
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
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		OracleDb oracle=new OracleDb();
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 627, 433);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		lbl_Welcome = new JLabel("Welcome CSC 505");
		lbl_Welcome.setBounds(173, 13, 172, 50);
		frame.getContentPane().add(lbl_Welcome);
		
		txt_LoginName = new JTextField();
		txt_LoginName.setToolTipText("Login Name");
		txt_LoginName.setBounds(293, 87, 116, 22);
		frame.getContentPane().add(txt_LoginName);
		txt_LoginName.setColumns(10);
		
		txt_Password = new JTextField();
		txt_Password.setToolTipText("Password");
		txt_Password.setBounds(293, 135, 116, 22);
		frame.getContentPane().add(txt_Password);
		txt_Password.setColumns(10);
		
		JLabel lbl_LoginName = new JLabel("Login Name");
		lbl_LoginName.setBounds(183, 80, 74, 36);
		frame.getContentPane().add(lbl_LoginName);
		
		JLabel lbl_Password = new JLabel("Password");
		lbl_Password.setBounds(185, 128, 86, 36);
		frame.getContentPane().add(lbl_Password);
		
		JButton btn_Login = new JButton("Login");
		btn_Login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btn_Login.setBounds(293, 180, 97, 25);
		frame.getContentPane().add(btn_Login);
		
		JLabel lbl_ErrorMessage = new JLabel("");
		lbl_ErrorMessage.setForeground(Color.RED);
		lbl_ErrorMessage.setBounds(52, 242, 506, 115);
		frame.getContentPane().add(lbl_ErrorMessage);
	}
}
