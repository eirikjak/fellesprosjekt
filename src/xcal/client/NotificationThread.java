package xcal.client;

import xcal.core.Settings;
import xcal.gui.NotificationPage;
import xcal.gui.RootFrame;
import xcal.model.Employee;
import xcal.model.Notification;

public class NotificationThread extends Thread
{
	private Client client = Client.getClient();
	private Employee person=Client.getUser();
	
	private Wrapper response;
	
	public void run()
	{
		while(true)
		{
			System.out.println("notification search started");
			try
			{
				response=client.sendObject(person, Status.CHECK_NOTIFICATION);
				if(response.getFlag()==Status.GET_ALL)
				{
					Notification[] notifications=(Notification[])response.getContent();

					for(int i=0;i<notifications.length;++i)//loop thru every notification
					{
						new NotificationPage(notifications[i]);//to popup notification
					}
						
				}
				

				Thread.sleep(Settings.NOTIFICATION_INTERVAL);
			}
			catch(InterruptedException e)
			{
				
			}
		}
		
		
	}
	

}
