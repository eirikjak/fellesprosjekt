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
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

import xcal.client.Status;
import xcal.client.Wrapper;
import xcal.core.Settings;
import xcal.server.query.DbConnection;
import xcal.server.query.LocationQ;



public class Server
{
	private ServerSocket socket;
	//private Socket clients; 
	
	private Vector<ClientThread> clients;
	private int size;
	private DbConnection connection;
	
	private PrintWriter print;
	
	
	
	public Server()
	{
		clients=new Vector<ClientThread>();
		try
		{
			socket=new ServerSocket(Settings.port);//start listening on port
			connection=new DbConnection(Settings.db_host,Settings.db_user,Settings.db_pw);
			connection.connect();
		}
		catch(IOException e)//exit if not succeeded
		{
			
			System.out.println("Couldn't start listening on port"+e.getMessage());
			System.exit(-1);
		}
	}
	
	
	private int getSize(){
		return size;
	}
	
	public boolean acceptClient()//accept clients
	{

		try
		{
			Socket client=socket.accept();//wait for connection
			clients.add(size
					,new ClientThread(client,size));
			clients.get(size).start();
			//new Thread(new ClientThread(client,size)).start();
			
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

	public Object recieveObject(Wrapper o){
		ObjectManager om = new ObjectManager();
		Object obj = om.manage(o);
		
		
		return obj;
		
		
	}
	
	private int getClientsConnected()
	{
		int number=0;
		for(int i=0;i<clients.size();++i)
			if(clients.get(i).isRunning())
				number++;
		return number;
	}
	
	
	private void disconnect() throws IOException
	{
		//clients.close();
		print.close();
	}
	
	/*public void startListening(){
		Thread listeningThread = new Thread(){
			
			@Override
			public void run() {
				super.run();
				while (true) 
				{		
					if(acceptClient())
						System.out.println("client connected");
					else
						System.out.println("Failed acception client. Something may be very wrong.");
					
					System.out.println("number of clients connected: "+getClientsConnected());
				}
			}
		};
		listeningThread.start();
		
	}*/
	
	public void run()
	{
		while(true)
		{
			//System.out.println("number of clients connected: "+getClientsConnected());
			if(acceptClient())
			{
				System.out.println("client connected");
			}
			else
				System.out.println("couldn't connect");
			System.out.println("TEST");
		}
	}
	
	
	public static void main(String args [])
	{
		//ObjectOutputStream test;
		
		Server server=new Server();
		server.run();
		
		
		//server.startListening();
		
		
	}

	
	

	
	
}
