package xcal.server.managers;


import java.util.ArrayList;

import xcal.client.Status;
import xcal.client.Wrapper;
import xcal.model.Appointment;
import xcal.model.Invite;
import xcal.model.Location;
import xcal.model.Notification;
import xcal.server.query.AppointmentsQ;
import xcal.server.query.InviteQ;
import xcal.server.query.LocationQ;
import xcal.server.query.NotificationQ;

public class InviteManager {

	
	public static Wrapper handle(Invite invite, Status flag){
		
		switch(flag){
		case GET_INVITES:
			return getInvites(invite);
		case DESTROY:
			return destroy(invite);
		case UPDATE:
			return update(invite);
		
		default:
			return null;
		}
}

	private static Wrapper destroy(Invite invite) {
		
		return null;
	}
	
	private static Wrapper update(Invite invite){
		
		InviteQ.updateInvite(invite.getMeeting().getAppId(), invite.getEmployee().getEmail(), invite.getAnswer());
		return new Wrapper(Status.SUCCESS,  null);
	}

	private static Wrapper getInvites(Invite invite){
		
		ArrayList<Invite> invites = InviteQ.getInvitesForPerson(invite.getEmployee());
		if(invites != null){
			return new Wrapper(Status.SUCCESS, invites);
			
		}
		return new Wrapper(Status.ERROR, invites);
	
		
	}
	
}