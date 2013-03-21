/**
 * simple socket client
 * 
 * simple gui just to test login check
 */
package xcal.client;


import java.awt.EventQueue;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Vector;

import javax.swing.SwingUtilities;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;



import xcal.core.Settings;
import xcal.gui.Login;
import xcal.gui.RootFrame;
import xcal.model.Employee;



public class Client 
{
	private Socket socket;
	
	private ObjectInputStream input;
	private ObjectOutputStream output;
	private static Client client;
	private static Employee user;
	
	private Vector<Employee> showCalendars;
	
	
	public Client()
	{

	
		System.out.println(new DateTime(0,1,1,0,0));
		connect();
		client = this;
		
		showCalendars=new Vector<Employee>();

		SwingUtilities.invokeLater(new Runnable() {
			
		
			
			@Override
			public void run() {
				RootFrame.init(1015, 720);
				RootFrame.addPanel(new Login());
				
			}
		});
		

	}
	
	private void connect()
	{
		try
		{
			socket=new Socket(Settings.server_ip,Settings.port);
		
		}
		catch(IOException e)
		{
			System.out.println("Not able to connect");
			System.exit(-1);
		}
	}
	
	
	/**Object to send to server
	 * 
	 * 
	 * @param send - object to send
	 * @return boolean - if send was successful or not
	 */
	
	public static Employee getUser(){
		return user;
	}
	public void setUser(Employee user){
		this.user = user;
		addCalendarUser(this.user);
	}
	
	
	public void addCalendarUser(Employee user)
	{
		boolean found=false;
		for(int i=0;i<showCalendars.size();++i)
		{
			if(showCalendars.get(i).getEmail()==user.getEmail())
				found=true;
		}
		
		if(!found)
			showCalendars.add(user);
	}
	
	public Vector<Employee> getCalendarUsers(){return showCalendars;}
	public void clearCalendarUsers(){showCalendars.clear();}
	
	
	public synchronized Wrapper sendObject(Object o, Status s){
		Wrapper sentObj = new Wrapper(s,o);
		try {
			output = new ObjectOutputStream(socket.getOutputStream());
			output.writeObject(sentObj);
			output.flush();
			try {
				input = new ObjectInputStream(socket.getInputStream());
				Wrapper response = (Wrapper) input.readObject();

				return response;
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {

			e.printStackTrace();
		}
		
		return null;
		
		
		
	}
	
	
	/**
	 * Object to recieve from server
	 * 
	 * 
	 * @return Object - the object recieved
	 */
	public static void main(String[] args)
	{
		
		new Client();
	}
	
	public void close()
	{
		try {
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Client getClient(){
		return client;
	}

}
