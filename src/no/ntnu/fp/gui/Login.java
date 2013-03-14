package no.ntnu.fp.gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

public class Login extends JPanel {
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Create the panel.
	 */
	public Login() {
		setLayout(null);
		
		JLabel lblEmail = new JLabel("email:");
		lblEmail.setBounds(214, 267, 61, 16);
		add(lblEmail);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(190, 295, 61, 16);
		add(lblPassword);
		
		textField = new JTextField();
		textField.setBounds(280, 261, 134, 28);
		add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(280, 289, 134, 28);
		add(passwordField);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(280, 329, 117, 29);
		add(btnLogin);

	}
}
