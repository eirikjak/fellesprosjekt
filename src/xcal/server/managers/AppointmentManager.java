package xcal.server.managers;

import org.joda.time.DateTime;

import xcal.client.Status;
import xcal.client.Wrapper;
import xcal.model.Appointment;
import xcal.model.Location;
import xcal.model.Notification;
import xcal.server.query.AppointmentsQ;
import xcal.server.query.LocationQ;
import xcal.server.query.NotificationQ;

public class AppointmentManager {


	public static Wrapper handle(Appointment appointment, Status flag){
		
		switch(flag){
		case CREATE:
			return create(appointment);
		case UPDATE:
			return update(appointment);
		case DESTROY:
			return destroy(appointment);
		case SELECT:
			return select(appointment);
		case TD_APP:	
			return selectAppointmetsForPersonFromDate(appointment);
	}
	
	return null;
	
	}
	
	private static Wrapper create(Appointment appointment){
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
		
	}
	
	private static Wrapper update(Appointment appointment){
		
		int app_id = appointment.getAppId();
		DateTime start = appointment.getFromTime();
		DateTime end = appointment.getToTime();
		String descr = appointment.getDescription();
		String email = appointment.getLeader().getEmail();
		int place = appointment.getLocationID();
		
		return new Wrapper(Status.ERROR, null);
		
	}
	
	private static Wrapper destroy(Appointment appointment){
		AppointmentsQ.deleteEvent(appointment.getAppId());
		return new Wrapper(Status.ERROR ,null);
	}
	
	private static Wrapper select(Appointment appointment){
		return new Wrapper(Status.ERROR,(Appointment)AppointmentsQ.selectAppointment(appointment.getAppId()));
	}
	
	private static Wrapper selectAppointmetsForPersonFromDate(Appointment appointment){
		
		return new Wrapper(Status.SUCCESS, AppointmentsQ.selectAppointmentsForPersonFromDate (appointment.getFromTime(), appointment.getToTime(), appointment.getLeader().getEmail()));
	}
}
