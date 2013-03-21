package xcal.client;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.SwingUtilities;

import xcal.gui.InviteMenu;
import xcal.model.Invite;

public class InviteListener {
	private Client client;
	public InviteListener(){
		client = Client.getClient();
		
		Timer timer = new Timer();
		//check once a a minute
		timer.schedule(new InviteTask(), 60*1000);
		
		new Timer().schedule(new InviteTask(), 100);
	}
	
	private class InviteTask extends TimerTask{

		@Override
		public void run() {
			Wrapper response  = client.sendObject(new Invite(client.getUser()), Status.GET_INVITES);
			if(response.getFlag() == Status.SUCCESS){
				//the user has a pending notification
				System.out.println("invite!" + response.getContent());
				
				ArrayList<Invite> invites = (ArrayList<Invite>) response.getContent();
				for(final Invite invite: invites){
					
					SwingUtilities.invokeLater(new Runnable() {
						
						@Override
						public void run() {
							new InviteMenu().setModel(invite);
							
						}
					});
				}
				
			}
			
		}
		
		
	}
}


