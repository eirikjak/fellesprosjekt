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
import java.net.SocketTimeoutException;


import xcal.client.Status;
import xcal.client.Wrapper;
import xcal.model.Employee;
import xcal.server.query.DbConnection;

public class ClientThread extends Thread implements Runnable
{

	private Socket client;
	private ObjectInputStream input;
	private ObjectOutputStream output;
	private int id;
	private xcal.model.Employee person;	
	private boolean running;
	
	public ClientThread(Socket client,int i)
	{
		this.client=client;
		person=new Employee(); 
		id=i;
		running=false;
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
			try//check if possible to recieve on socket
			{
				output=new ObjectOutputStream(client.getOutputStream());
			}
			catch(SocketException e){disconnect();return false;}
			
			output.writeObject(send);
			output.flush();
			return true;
		}
		catch(IOException e){e.printStackTrace();}
		
		return false;
	}
	
	/**
	 * wrapper to send to client
	 * 
	 * @param send - wrapper to send
	 * @return - true if send was successful
	 */
	public boolean sendObject(Wrapper send)
	{
		//Wrapper send=new Wrapper(status,object);
		try
		{
			try//check if possible to recieve on socket
			{
			output=new ObjectOutputStream(client.getOutputStream());
			}
			catch(SocketException e){disconnect();return false;}
			
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
			
			try//check if possible to recieve on socket
			{
			input=new ObjectInputStream(client.getInputStream());
			}
			catch(SocketException e){disconnect();return null;}
	
	
			Wrapper recieve = (Wrapper) input.readObject();
			return recieve;
		}
		catch( IOException | ClassNotFoundException e)
		{
			e.printStackTrace();
			this.

			running=false;
		}
		
		return null;
	}
	
	public boolean isRunning(){return running;}
	
	private void disconnect()
	{
		try {
			client.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		running=false;
	}
	

	public void run()
	{
		
			running=true;
			System.out.println("Client nr "+id+" running");
			while(!client.isClosed() && running)
			{
				
	
				Wrapper object= ObjectManager.manage(recieveObject());
				sendObject(object);
				

				
				
				//System.out.println("FLAG"+object.getFlag());
				

				//System.out.println("recieved object");
				//System.out.println("object sent");
			}
		
		
		
		
		
	}
	

}
