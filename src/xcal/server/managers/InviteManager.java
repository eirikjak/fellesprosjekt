package xcal.server.managers;

import java.sql.Wrapper;
import xcal.client.Status;
import xcal.model.Appointment;
import xcal.model.Invite;
import xcal.model.Location;
import xcal.model.Notification;
import xcal.server.query.AppointmentsQ;
import xcal.server.query.LocationQ;
import xcal.server.query.NotificationQ;

public class InviteManager {

	
	public static Wrapper handle(Invite invite, Status flag){
		
		switch(flag){
		case CREATE:
			return create(invite);
		case DESTROY:
			return destroy(invite);
		default:
			return null;
		}
}
/*private static Wrapper create(Appointment appointment){
		Location loc = LocationQ.createLocation(appointment.getLocationName());
		if(loc != null){
			Appointment app = AppointmentsQ.createAppointment(appointment, loc);
			if(app != null){
				Notification not = NotificationQ.createNotification(app);
				if(not != null){
					return new Wrapper(Status.SUCCESS, null);
				}else{
					AppointmentsQ.deleteAppointment(app);
				}
			}else{
				LocationQ.deleteLocation(loc);
			}
		}
		return new Wrapper(Status.ERROR,null);
		
	}*/
	private static Wrapper destroy(Invite invite) {
		
		return null;
	}

	private static Wrapper create(Invite invite) {
		Invite invited = NotificationQ.createInvite(meeting, employee);
		
		return new Wrapper (Status.SUCCESS, null);
	}
	
}