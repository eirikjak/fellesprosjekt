package xcal.gui;

import java.util.Timer;
import java.util.TimerTask;

import xcal.client.Client;
import xcal.client.Status;
import xcal.client.Wrapper;
import xcal.model.Invite;
import xcal.model.Notification;

public class NotificationListener {

	private Client client;
	public NotificationListener(){
		client = Client.getClient();
		
		Timer timer = new Timer();
		//check onca a minute
		timer.schedule(new NotificationTask(), 60*1000);
	}
	
	private class NotificationTask extends TimerTask{

		@Override
		public void run() {
			Wrapper response  = client.sendObject(new Notification(), Status.PENDING);
			if(response.getFlag() == Status.SUCCESS){
				//the user has a pending notification
				System.out.println("notify!" + response.getContent());
				
			}
			
		}
		
		
	}
}
