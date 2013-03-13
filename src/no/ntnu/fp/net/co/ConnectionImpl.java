/*
 * Created on Oct 27, 2004
 */
package no.ntnu.fp.net.co;

import java.io.EOFException;
import java.io.IOException;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.SocketAddress;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import no.ntnu.fp.net.admin.Log;
import no.ntnu.fp.net.cl.ClException;
import no.ntnu.fp.net.cl.ClSocket;
import no.ntnu.fp.net.cl.KtnDatagram;
import no.ntnu.fp.net.cl.KtnDatagram.Flag;

/**
 * Implementation of the Connection-interface. <br>
 * <br>
 * This class implements the behaviour in the methods specified in the interface
 * {@link Connection} over the unreliable, connectionless network realised in
 * {@link ClSocket}. The base class, {@link AbstractConnection} implements some
 * of the functionality, leaving message passing and error handling to this
 * implementation.
 * 
 * @author Sebjørn Birkeland and Stein Jakob Nordbø
 * @see no.ntnu.fp.net.co.Connection
 * @see no.ntnu.fp.net.cl.ClSocket
 */
public class ConnectionImpl extends AbstractConnection {

    /** Keeps track of the used ports for each server port. */
    private static Map<Integer, Boolean> usedPorts = Collections.synchronizedMap(new HashMap<Integer, Boolean>());
//    private static ArrayList<KtnDatagram> rcvdPackets = new ArrayList();
    /**
     * Initialise initial sequence number and setup state machine.
     * 
     * @param myPort
     *            - the local port to associate with this connection
     */
    public ConnectionImpl(int myPort) {
    	super();
    	if(!usedPorts.containsKey(myPort)|| !usedPorts.get(myPort)){
       		this.myPort = myPort;
       		this.myAddress = getIPv4Address();
       		System.out.println(this.myAddress);
   		}
    }
    
    public static void main(String[] args) throws SocketTimeoutException, IOException {
    	ConnectionImpl c = (ConnectionImpl) new ConnectionImpl(23893).accept();
    	try{
    		for(int i=0; i<1000; i++){
    			System.out.println(c.receive());
    		}
    	}
    	catch(IOException e){
    		
    	}
 //   	System.out.println(rcvdPackets);
	
	}

    private String getIPv4Address() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        }
        catch (UnknownHostException e) {
            return "127.0.0.1";
        }
    }

    /**
     * Establish a connection to a remote location.
     * 
     * @param remoteAddress
     *            - the remote IP-address to connect to
     * @param remotePort
     *            - the remote portnumber to connect to
     * @throws IOException
     *             If there's an I/O error.
     * @throws java.net.SocketTimeoutException
     *             If timeout expires before connection is completed.
     * @see Connection#connect(InetAddress, int)
     */
    public void connect(InetAddress remoteAddress, int remotePort) throws IOException,
            SocketTimeoutException {
    	
    	
    	
    	this.remoteAddress = remoteAddress.getHostAddress();
    	this.remotePort = remotePort;
    	KtnDatagram response = null;
       
        try {
			simplySendPacket( constructInternalPacket(Flag.SYN));
		} catch (ClException e) {
			e.printStackTrace();
		}
       
        
        while (response == null){
        	
        	response = receivePacket(true);
        }
        
        if(response.getFlag() == Flag.SYN_ACK){
        	
        	response = constructInternalPacket(Flag.ACK);
        	try {
				simplySendPacket(response);
			} catch (ClException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	
        }else{
        	
        }
        
        this.state = State.ESTABLISHED;
    	
        
    }

    /**
     * Listen for, and accept, incoming connections.
     * 
     * @return A new ConnectionImpl-object representing the new connection.
     * @see Connection#accept()
     */
    public Connection accept() throws IOException, SocketTimeoutException {

    	KtnDatagram p = null;
    	while (p== null){
    		p = receivePacket(true);
    	}
    	
    	if(p.getFlag()== Flag.SYN){
    		this.state = State.SYN_RCVD;
    		this.remoteAddress = p.getSrc_addr();
    		this.remotePort = p.getSrc_port();
    		p = constructInternalPacket(Flag.SYN_ACK);
    		System.out.println(remoteAddress);
    		try {
				simplySendPacket(p);
			} catch (ClException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	
    	p = null;
    	while (p== null){
    		p = receivePacket(true);
    	}
    	
    	if(p.getFlag() == Flag.ACK){
    		ConnectionImpl c = new ConnectionImpl(p.getSrc_port());
    		c.remoteAddress = p.getSrc_addr();
    		c.remotePort = p.getSrc_port();
    		c.state = State.ESTABLISHED;
    		c.lastValidPacketReceived = p;
    		c.lastDataPacketSent = p;
    		usedPorts.put(myPort, true);
    		c.state = State.ESTABLISHED;
    		return c;
    	}
    	else{
    		return null;
    	}
    	
    	
    }

    /**
     * Send a message from the application.
     * 
     * @param msg
     *            - the String to be sent.
     * @throws ConnectException
     *             If no connection exists.
     * @throws IOException
     *             If no ACK was received.
     * @see AbstractConnection#sendDataPacketWithRetransmit(KtnDatagram)
     * @see no.ntnu.fp.net.co.Connection#send(String)
     */
    public void send(String msg) throws ConnectException, IOException {
    
    	
    	KtnDatagram packet = constructDataPacket(msg);
    	KtnDatagram response = null;
    	packet.setFlag(Flag.NONE);
    	packet.setChecksum(packet.getChecksum() + Flag.NONE.ordinal());
    	boolean receivedAck = false;
    
    	
    	while(!receivedAck){
    		response = sendDataPacketWithRetransmit(packet);

         	if(response != null && response.getFlag() == Flag.ACK){
         		if(packet.getSeq_nr() == response.getAck())
         			receivedAck = true;
         		
         	}
         	
    	}
    	
    	
    	
    	
    	
    }

    /**
     * Wait for incoming data.
     * 
     * @return The received data's payload as a String.
     * @see Connection#receive()
     * @see AbstractConnection#receivePacket(boolean)
     * @see AbstractConnection#sendAck(KtnDatagram, boolean)
     */
    public String receive() throws ConnectException, IOException {
    	//int prevSeq = Math.max(lastValidPacketReceived.getSeq_nr(), lastDataPacketSent.getSeq_nr());
    	KtnDatagram p = null;
    	//System.out.println(prevSeq + "QQQQQQQQQQQQQQQQQQQ");
    	while(p == null){
    		try{
    			p = receivePacket(false);
    		}
    		catch (EOFException e){
    			internalClose();
    			throw new IOException("Connection Closed while recieving packets");
    		}
    		System.out.println("_________CALCULATE CHECKSUM______________" + p.calculateChecksum());
    		if(!isValid(p)){
    			if(lastValidPacketReceived.getSeq_nr() <= p.getSeq_nr()){
    				sendAck(lastValidPacketReceived,false);
    			}
    			p = null;
    		}
    		
    		
    		//if(!(p != null && p.getSeq_nr() == (prevSeq +1) && p.getChecksum() == p.calculateChecksum())){
    			//p = null;
    		//}
    	}
    	this.lastValidPacketReceived = p;
    	p.setDest_addr(p.getSrc_addr());
    	p.setSrc_addr(this.myAddress);
    	p.setDest_port(p.getSrc_port());
    	p.setSrc_port(this.myPort);
    	sendAck(p, false);
    	this.lastDataPacketSent = p;
    	return (String) p.getPayload();
    }

    /**
     * Close the connection.
     * 
     * @see Connection#close()
     */
    public void close() throws IOException {
        
    	System.out.println();
    	
    }

    /**
     * Test a packet for transmission errors. This function should only called
     * with data or ACK packets in the ESTABLISHED state.
     * 
     * @param packet
     *            Packet to test.
     * @return true if packet is free of errors, false otherwise.
     */
    protected boolean isValid(KtnDatagram p) {
        if(this.state == State.ESTABLISHED){
        	System.out.println("System.out.println(lastValidPacketReceived.getSeq_nr());" + lastValidPacketReceived.getSeq_nr());
        	System.out.println("System.out.println(lastDataPacketSent.getSeq_nr());" + lastDataPacketSent.getSeq_nr());
        	int prevSeq = Math.max(lastValidPacketReceived.getSeq_nr(), lastDataPacketSent.getSeq_nr());
        	System.out.println("___________PREV SEQ___________" + prevSeq);
        	if(!(p != null && p.getSeq_nr() == (prevSeq +1) && p.getChecksum() == p.calculateChecksum()+p.getFlag().ordinal())){
        		return false;
        	}
        	else{
        		return true;
        	}
        }
        return false;
        
    }
    
    private void internalClose() throws ConnectException, IOException{
    	// Number of tries to send FIN
    	int tries = 3;
    	
    	//Want to ACK the received FIN flag
    	sendAck(this.disconnectRequest, false);
    	System.out.println("+-+-+-++-+ SENDT ACK +-+-+-+-+-++-+-+");
    	this.state = State.CLOSE_WAIT;
    	
    	KtnDatagram fin = constructInternalPacket(Flag.FIN);
		KtnDatagram finack=null;

    	
    	//Want to send FIN flag
		while(!isValid(finack) ){
			try {
				simplySendPacket(fin);
				this.state = State.LAST_ACK;
			} catch (ClException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finack = receiveAck();
		}
		this.state = State.TIME_WAIT;
    	this.state = State.CLOSED;
    	this.disconnectRequest = null;
    	
    	System.out.println("+-+-+-++-+ SLUTTEN +-+-+-+-+-++-+-+");
    	
    }
}
