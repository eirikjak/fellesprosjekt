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
	
	
	/**
	 * Object to recieve from client
	 * 
	 * 
	 * @return Object - the object recieved
	 */
	public Object recieveObject() 
	{
		try
		{
			
				input=new ObjectInputStream(client.getInputStream());
				
				Object o = input.readObject();
				
				return o;
				//return input.readObject();
			
		}
		catch(ClassNotFoundException e){} catch (IOException e) {
			e.printStackTrace();
			try {
				client.close();
				
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		
		return null;
	}
	
	/**Object to send to client
	 * 
	 * 
	 * @param send - object to send
	 * @return boolean - if send was successful or not
	 */	
	public boolean sendObject(Object send)
	{
		//System.out.println(((Wrapper)send).getContent().toString());
		try
		{
			output=new ObjectOutputStream(client.getOutputStream());
			output.writeObject(send);
			output.flush();
			return true;
		}
		catch(IOException e){
			
			e.printStackTrace();
		}
		return false;
	}
	

	public void run()
	{
		
		
			System.out.println("Client nr "+id+" running");
			while(!client.isClosed())
			{
				Object object= ObjectManagers.manage(recieveObject());
				System.out.println("recieved object");
				/*try {
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
				System.out.println(sendObject(object));
				System.out.println("object sent");
			}
		
		
		
		//check user before continue
		//while(!checkUser());
			
		/*System.out.println("User authenticated");
		System.out.println("Welcome "+person);
		//what to do when client connected and thread run
		String msg;*/
		
		/*try
		{
			while((msg=readFromClient())!=null)//print msg for all input from client
				System.out.println("msg"+msg);
		}
		catch(Exception e){}*/
		
		
	}
	

}
