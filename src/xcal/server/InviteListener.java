package xcal.server;

import java.util.Timer;
import java.util.TimerTask;

import xcal.client.Client;
import xcal.client.Status;
import xcal.client.Wrapper;
import xcal.model.Invite;

public class InviteListener {
	private Client client;
	public InviteListener(){
		client = Client.getClient();
		
		Timer timer = new Timer();
		//check once a a minute
		timer.schedule(new InviteTask(), 60*1000);
	}
	
	private class InviteTask extends TimerTask{

		@Override
		public void run() {
			Wrapper response  = client.sendObject(new Invite(), Status.PENDING);
			if(response.getFlag() == Status.SUCCESS){
				//the user has a pending notification
				System.out.println("notify!" + response.getContent());
				
			}
			
		}
		
		
	}
}


