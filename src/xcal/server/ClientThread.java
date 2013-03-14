/**
 * client thread for each client connected
 * 
 * for now - check login info, and recv/send text to client
 */
package xcal.server;

import java.io.BufferedReader;
import java.io.DataInputStream;//to use with classes?
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

import xcal.model.Employee;

public class ClientThread extends Thread
{
	private Socket client;
	//private DataInputStream input;
	private BufferedReader input;
	private PrintStream print;
	private int id;
	
	private xcal.model.Employee person;
	
	public ClientThread(Socket client,int i)
	{
		this.client=client;
		person=new Employee(); 
		id=i;
		
		try 
		{
			//input=new DataInputStream(this.client.getInputStream());
			input=new BufferedReader(new InputStreamReader(this.client.getInputStream()));//open reader
			print=new PrintStream(this.client.getOutputStream());//and printer
		} 
		catch (IOException e)
		{
			System.out.println("error"+e.getMessage());
		}
	}
	
	public void WriteToClient(String msg)
	{
		print.println(msg);
	}
	
	public String readFromClient()
	{
		String msg;
		
		try 
		{
			msg=input.readLine();
			return msg;
		} 
		catch (IOException e) 
		{
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	public boolean checkUser()//authenticate user
	{
		String username=readFromClient();//get input from user
		String pw=readFromClient();
		
		System.out.println(username);
		System.out.println(pw);
		
		if(username.equals("jonas") && pw.equals("123"))
		{
			
			person.setName("Jonas");
			person.setDateOfBirth("150491");
			person.setEmail("jonas@jonastn.com");
			return true;
		}
		
		System.out.println("wrong username||password");
		return false;
	}
	
	public void run()
	{
		//check user before continue
		while(!checkUser());
			
		System.out.println("User authenticated");
		System.out.println("Welcome "+person);
		//what to do when client connected and thread run
		String msg;
		
		/*try
		{
			while((msg=readFromClient())!=null)//print msg for all input from client
				System.out.println("msg"+msg);
		}
		catch(Exception e){}*/
		
		
	}
	

}
