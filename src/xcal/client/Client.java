/**
 * simple socket client
 * 
 * simple gui just to test login check
 */
package xcal.client;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;



import xcal.core.Settings;
import xcal.gui.Login;
import xcal.gui.RootFrame;



public class Client 
{
	private Socket socket;
	
	private ObjectInputStream input;
	private ObjectOutputStream output;
	
	
	public Client()
	{

		connect();
		RootFrame.init(1015, 720);
		RootFrame.addPanel(new Login(this));

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
	
	public Wrapper sendObject(Object o, Status s){
		Wrapper sentObj = new Wrapper(s,o);
	
		try {
			output = new ObjectOutputStream(socket.getOutputStream());
			output.writeObject(sentObj);
			output.flush();
			input = new ObjectInputStream(socket.getInputStream());
			try {
				Wrapper response = (Wrapper) input.readObject();
				return response;
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
		
		
	}
	/*
	public Object sendObject(Object send)
	{
		try
		{
			output=new ObjectOutputStream(socket.getOutputStream());
			input = new ObjectInputStream(socket.getInputStream());
			output.writeObject(send);
			output.flush();
			//output.close();
			try {
				Object response = input.readObject();
				return response;
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
		catch(IOException e){}
		return null;
	}
	*/
	
	/**
	 * Object to recieve from server
	 * 
	 * 
	 * @return Object - the object recieved
	 */
	public Wrapper recieveObject() 
	{
		try
		{
			input=new ObjectInputStream(socket.getInputStream());
			return (Wrapper) input.readObject();
			
		}
		catch(ClassNotFoundException e){} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	

	
	
	public static void main(String[] args)
	{
		
		
		
		new Client();
	}
	

}
