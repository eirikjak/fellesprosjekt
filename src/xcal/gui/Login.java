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
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Panel;
import java.awt.Color;
//import java.lang.ProcessBuilder.Redirect;

import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import org.jdesktop.swingx.JXBusyLabel;

import xcal.client.Client;
import xcal.client.NotificationThread;
import xcal.client.Status;
import xcal.client.Wrapper;
import xcal.model.Authentication;
import xcal.model.Employee;

public class Login extends JPanel {
	private JTextField textField;
	private JPasswordField passwordField;
	private JLabel errorLabel;
	private JXBusyLabel bussyLabel;
	private Client client = Client.getClient();

	/**
	 * Create the panel.
	 */
	public Login() {
		
		
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
		passwordField.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				login();
				
			}
		});
		add(passwordField);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(409, 393, 117, 29);
		add(btnLogin);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Login.class.getResource("/images/xcal.png")));
		lblNewLabel.setBounds(318, 92, 300, 154);
		add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBackground(UIManager.getColor("InternalFrame.background"));
		panel.setBounds(299, 289, 369, 154);
		add(panel);
		
		errorLabel = new JLabel("Wrong username/password");
		errorLabel.setHorizontalAlignment(SwingConstants.CENTER);
		errorLabel.setForeground(Color.RED);
		errorLabel.setFont(new Font("Dialog", Font.BOLD, 13));
		errorLabel.setBounds(299, 467, 369, 14);
		add(errorLabel);
		errorLabel.setVisible(false);
		btnLogin.addActionListener(new LoginButtonListener());
		
		
		
	}
	
	private void login(){
		
		if(bussyLabel != null)
			remove(bussyLabel);
		
		bussyLabel = new JXBusyLabel(new Dimension(40,40));
		add(bussyLabel);
		bussyLabel.setBounds(299 + 369/2 - 20 ,389 + 154/2 - 20, 40, 40);
		bussyLabel.setBusy(true);
		SwingWorker<Void , Void> worker = new SwingWorker<Void, Void>(){
			protected boolean success = false;
			protected Wrapper response;
			protected Void doInBackground() throws Exception {
				if(!textField.getText().isEmpty() && !passwordField.getText().isEmpty())
				{
					
					
					Authentication auth=new Authentication(textField.getText(),passwordField.getText());
					response = client.sendObject(auth, Status.LOGIN);
					if(response.getFlag() != Status.SUCCESS){
						System.out.println("Wrong username/password");
						errorLabel.setVisible(true);		
						errorLabel.setText("Wrong username/password");
					}
					else
					{
						success = true;
						
					}
					
				}else{
					errorLabel.setVisible(true);
					errorLabel.setText("Please enter email and password");
					
				}
				return null;
			}

			public void done(){
				bussyLabel.setBusy(false);
				bussyLabel.setVisible(false);
				System.out.println("hello");
				if(success){
					RootFrame.clearAll();
					client.setUser((Employee)response.getContent());
					
					RootFrame.addPanel(new Mainpage());
					System.out.println(response.getContent());
					System.out.println("Welcome" + ((Employee)response.getContent()).getName());
					
					NotificationThread notifyThread=new NotificationThread();
					
				}
				
			}
			
		};
		worker.execute();
		
		
		
	}
	private class LoginButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			login();
		}
		
	}
}
