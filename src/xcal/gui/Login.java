package xcal.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Panel;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

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
		
		setBounds(0,0,1000,700);
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmail.setBounds(289, 333, 111, 22);
		add(lblEmail);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPassword.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblPassword.setBounds(289, 360, 111, 28);
		add(lblPassword);
		
		textField = new JTextField();
		textField.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		textField.setBounds(411, 328, 203, 28);
		add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		passwordField.setBounds(411, 360, 203, 28);
		add(passwordField);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(409, 393, 117, 29);
		add(btnLogin);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Login.class.getResource("/images/xcal.png")));
		lblNewLabel.setBounds(318, 92, 300, 154);
		add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBackground(UIManager.getColor("InternalFrame.background"));
		panel.setBounds(299, 289, 369, 154);
		add(panel);
		btnLogin.addActionListener(new LoginButtonListener());
		
		
		
	}
	
	private class LoginButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			//to login stuff
			RootFrame.addPanel(new Mainpage());
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
