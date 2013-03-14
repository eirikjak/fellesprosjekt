package com.net;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**Thread for handeling connectionas from Client Socket.
 *
 * 
 * @author Svenn
 *
 */
public class ClientHandler implements Runnable {

	private Socket socket;
	private ObjectInputStream streamInput;
	private ObjectOutputStream streamOutput;
	private Request request;
	private Server server;
	private String username;
	
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	

}
