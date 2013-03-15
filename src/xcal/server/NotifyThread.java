package xcal.server;

import xcal.core.Settings;
import xcal.server.query.DbConnection;
import xcal.server.query.NotificationQ;

public class NotifyThread extends Thread
{
	private NotificationQ notification_query;
	private DbConnection connection;
	
	public NotifyThread()
	{
		connection=new DbConnection(Settings.db_host,Settings.db_user,Settings.db_pw);
		notification_query=new NotificationQ(url,user,password);
	}
	
	
	
}
