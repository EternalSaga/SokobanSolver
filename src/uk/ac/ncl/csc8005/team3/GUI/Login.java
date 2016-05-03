package uk.ac.ncl.csc8005.team3.GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Scanner;
import java.awt.event.ActionEvent;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	
	private HashMap<String,String> info;
	private Scanner keyboard;
	private String user;
	private String password;
	private boolean success;

	

	/**
	 * Create the frame.
	 */
	public Login() {
		info = new HashMap<String,String>();
		keyboard = new Scanner(System.in);	
		success = false;
		
		
	}
	
	public void start(){
		initialize();
	}
	
	public void initialize(){
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUser = new JLabel("User");
		lblUser.setBounds(45, 80, 69, 20);
		contentPane.add(lblUser);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(45, 142, 69, 20);
		contentPane.add(lblPassword);
		
		textField = new JTextField();
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				user = keyboard.nextLine();
			}
		});
		textField.setBounds(156, 77, 146, 26);
		contentPane.add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				password = keyboard.nextLine();
			}
		});
		passwordField.setBounds(156, 139, 146, 23);
		contentPane.add(passwordField);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame mini = new JFrame();
				if (info.containsKey(password)){
					success = true;
					JLabel minlb = new JLabel("Login succeed");
					mini.getContentPane().add(minlb,BorderLayout.CENTER);
					mini.pack();
					mini.setVisible(true);
					
				} else{
				JLabel minlb = new JLabel("Please register first");
				mini.getContentPane().add(minlb,BorderLayout.CENTER);
				mini.pack();
				mini.setVisible(true);}
				
			}
		});
		btnLogin.setBounds(34, 186, 115, 29);
		contentPane.add(btnLogin);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				info.put(password,user);
				
			}
		});
		btnRegister.setBounds(226, 186, 115, 29);
		contentPane.add(btnRegister);
		
		JLabel lblNewLabel = new JLabel("Login Window");
		lblNewLabel.setBounds(105, 16, 197, 33);
		contentPane.add(lblNewLabel);
		setVisible(true);
		
	}
	
	public boolean getSuccess(){
		return success;
	}
	
	public HashMap<String,String> getHM(){
		return info;
	}
	
	public String getKey(){
		return password;
	}
}
