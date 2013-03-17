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

		//connect();
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
	
	/**
	 * Object to recieve from server
	 * 
	 * 
	 * @return Object - the object recieved
	 */
	public Object recieveObject() 
	{
		try
		{
			input=new ObjectInputStream(socket.getInputStream());
			return (Object) input.readObject();
			
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
