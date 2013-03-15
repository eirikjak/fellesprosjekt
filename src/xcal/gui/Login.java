package xcal.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

import xcal.client.Client;
import xcal.model.Authentication;

public class Login extends JPanel {
	private JTextField textField;
	private JPasswordField passwordField;
	
	private Client client;

	/**
	 * Create the panel.
	 */
	public Login(Client client) {
		
		this.client=client;
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
		btnLogin.addActionListener(new LoginButtonListener());
		
		
	}
	
	private class LoginButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			//to login stuff
			
			if(!textField.getText().isEmpty() && !passwordField.getText().isEmpty())
			{
				Authentication auth=new Authentication(textField.getText(),passwordField.getText());
				client.sendObject(auth);
				Object check=client.recieveObject();
				
				
				if(check==null)
					System.out.println("Wrong username/password");
				else
				{
					RootFrame.clearAll();
					RootFrame.addPanel(new Mainpage());
					System.out.println("Welcome" + check);
				}
				
			}
			
			
			

		}
		
	}
}
