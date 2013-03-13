package com.net;

import java.text.*;
import java.util.*;

/**
 * Server that accepts sockets and creates ClientHandlers for connecting clients.
 * @author Svenn
 * 
 */

public class Server {
	private ArrayList<ClientHandler> al;
	private SimpleDateFormat sdf;
	private int port;
	private boolean keepGoing;
	private Settings settings;
	

	public Server(int port) {
		this(port, null);
	}

}
