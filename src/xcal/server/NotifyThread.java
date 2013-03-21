/**
 * Notification checker
 * 
 * Runs on server, check every Settings.NOTIFICATION_INTERVAL if any notifications triggered
 * check if user that should recieve notification is logged on => send
 * 
 * 
 * DON'T NEED THIS. MOVED TO CLIENT
 */

package xcal.server;

import java.util.Vector;

import xcal.client.Status;
import xcal.client.Wrapper;
import xcal.core.Settings;
import xcal.model.Notification;
import xcal.server.query.DbConnection;
import xcal.server.query.NotificationQ;

public class NotifyThread extends Thread
{
	private NotificationQ notification_query;
	private DbConnection connection;
	private Vector<ClientThread> people_connected;
	
	/**
	 * @param c - copy of logged in users
	 */
	public NotifyThread(Vector<ClientThread> c)
	{
		connection=new DbConnection(Settings.db_host,Settings.db_user,Settings.db_pw);
		connection.connect();
		notification_query=new NotificationQ(connection);
		people_connected=c;
		
	}
	
	/**
	 * 
	 * check if notification can be send to user logged in
	 */
	private void checkNotificationCanSend()
	{
		 Notification[] not=notification_query.getNotifications();
		 
		 for(int i=0;i<not.length;++i)
			 for(int j=0;j<people_connected.size();++j)
				 if(not[i].getEmployee()==people_connected.get(j).getUser())
					 sendNotification(people_connected.get(j),not[i]);
		 
	}
	
	/**
	 * send the actually notification
	 * @param client - client to send notification to
	 * @param notification - notification to be sent
	 */
	private void sendNotification(ClientThread client,Notification notification)
	{
		//send notification to logged in user here
		//make sure to update db when successfully send notification
		
		if(client.sendObject(notification,Status.SEND_NOTIFICATION))
		{
			Wrapper response=client.recieveObject();
			if(response.getFlag()==ACCEPT){}
		}
		
		
		
	}
	
	
	
	
	
	//private void 
	
	
	public void run()
	{
		while(true)
		{
			try
			{
				
				
				if(notification_query.notificationReady())
				{
					checkNotificationCanSend();
					//notification_query.getNotifications();
				}
				
				
				Thread.sleep(Settings.NOTIFICATION_INTERVAL);
			}
			catch(InterruptedException e)
			{
				
			}
		}
		
		
	}
		 
	
	
	
}
