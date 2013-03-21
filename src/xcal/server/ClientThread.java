/**
 * client thread for each client connected
 * 
 * for now - check login info, and recv/send text to client
 */
package xcal.server;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;


import xcal.client.Status;
import xcal.client.Wrapper;
import xcal.core.ObjectCheck;
import xcal.model.Employee;

public class ClientThread extends Thread
{
	private Socket client;
	private ObjectInputStream input;
	private ObjectOutputStream output;
	private int id;
	private xcal.model.Employee person;	
	public ClientThread(Socket client,int i)
	{
		this.client=client;
		person=new Employee(); 
		id=i;
	}
	
	public Employee getUser(){return person;}//use for notifications, get which user is running thread to check if sendmsg is nec.
	public int getID(){return id;}
	
	


	
	/**Object to send to client
	 * 
	 * 
	 * @param object - object to send
	 * @param status - status object has
	 * @return boolean - if send was successful or not
	 */	
	public boolean sendObject(Object object, Status status)
	{
		Wrapper send=new Wrapper(status,object);
		try
		{
			output=new ObjectOutputStream(client.getOutputStream());
			output.writeObject(send);
			output.flush();
			return true;
		}
		catch(IOException e){e.printStackTrace();}
		
		return false;
	}
	
	
	/**
	 * Object to recieve from client
	 * 
	 * 
	 * @return Wrapper - the wrapper object recieved
	 */
	public Wrapper recieveObject()
	{
		try
		{
			input=new ObjectInputStream(client.getInputStream());
			Wrapper recieve=(Wrapper) input.readObject();
			
			return recieve;
		}
		catch(ClassNotFoundException | IOException e){e.printStackTrace();}
		
		return null;
	}
	

	public void run()
	{
		
		
			System.out.println("Client nr "+id+" running");
			while(!client.isClosed())
			{
				Object object= ObjectManager.manage(recieveObject());
				System.out.println("recieved object");
				//System.out.println(sendObject(object));
				System.out.println("object sent");
			}
		
		
		
		
		
	}
	

}
