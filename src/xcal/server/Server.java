/**
 * Socket Server code
 * 
 * Socket server, accepts client to be connected
 * makes a thread for every client connected, to accept
 * multiply clients. Holds every client in vector for easy access
 * 
 */

package xcal.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

import xcal.core.Settings;


public class Server 
{
	private ServerSocket socket;
	//private Socket clients;
	
	private Vector<ClientThread> clients;
	private int size;
	
	private PrintWriter print;
	
	public Server()
	{
		clients=new Vector<ClientThread>();
		size=0;//hold how many clients added
		//clients=null;
		try
		{
			socket=new ServerSocket(Settings.port);//start listening on port
		}
		catch(IOException e)//exit if not succeeded
		{
			
			System.out.println("Couldn't start listening on port"+e.getMessage());
			System.exit(-1);
		}
	}
	
	public int getSize(){return size;}
	
	public boolean acceptClient()//accept clients
	{
		//clients=null;
		
		try
		{
			
			Socket client=socket.accept();//wait for connection
			clients.add(new ClientThread(client,size));
			clients.get(size).start();
			++size;
			
			return true;
		}
		catch(IOException e)
		{
			System.out.println("Couldn't accept client");
			//System.exit(-1);
		}
		
		return false;
	}
	
	//write to client connected
	/*public void writeClient(String text,int index) 
	{
		clients.get(index).WriteToClient(text);
		
	}
	
	//get input from client connected
	public String readClient(int index)
	{
		return clients.get(index).readFromClient();
	}*/
	
	public void disconnect() throws IOException
	{
		//clients.close();
		print.close();
	}
	
	
	public static void main(String args []) throws IOException
	{
		//ObjectOutputStream test;
		
		Server server=new Server();
		
		
		while (true) 
		{
			
			if(server.acceptClient())
			{
				
				System.out.println("CLient connected");
				
				//server.writeClient("Welcome!",server.getSize()-1);
				//System.out.println(server.readClient(server.getSize()-1));
				//System.out.println("client accepted");
				
			}
			else
				System.out.println("client NOT accepted");
			
		
		}
	}
	

	
	
}
