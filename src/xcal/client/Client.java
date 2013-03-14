/**
 * simple socket client
 * 
 * simple gui just to test login check
 */
package xcal.client;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.*;


import xcal.core.Settings;
import xcal.gui.Login;
import xcal.gui.RootFrame;


public class Client 
{
	private JPanel panel1,panel2;
	private JButton login;
	private JTextField text_user,text_pw;
	private JLabel user,pw;
	
	private Socket socket;
	private PrintWriter writer;
	
	
	public Client()
	{
		/*connect();
		
		panel1=new JPanel();
		panel2=new JPanel(new FlowLayout());
		login=new JButton("Login");
		login.addActionListener(new LoginSender());
		text_user=new JTextField(20);
		text_pw=new JTextField(20);
		
		user=new JLabel("Username:");
		pw=new JLabel("Password:");
		
		panel2.add(user);
		panel2.add(text_user);
		panel2.add(pw);
		panel2.add(text_pw);
		
		panel1.add(login);
	
		JFrame frame=new JFrame("GUI CLIENT");
		
		frame.add(panel2);
		frame.add(panel1);
		frame.setLayout(new FlowLayout());
		frame.setSize(400,150);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);*/
		
		RootFrame.init(1000, 700);
		RootFrame.addPanel(new Login());
		
		
	}
	
	private void connect()
	{
		try
		{
			socket=new Socket(Settings.server_ip,Settings.port);
			writer=new PrintWriter(socket.getOutputStream());
		}
		catch(IOException e)
		{
			System.out.println("Not able to connect");
			System.exit(-1);
		}
	}
	
	
	private class LoginSender implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0) 
		{
			try
			{
				writer.println(text_user.getText());
				writer.println(text_pw.getText());
				writer.flush();
			}
			catch(Exception e){}
			
		}
		
	}
	
	
	public static void main(String[] args)
	{
		new Client();
	}
	

}
