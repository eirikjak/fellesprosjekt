package xcal.server;

import java.io.ObjectOutputStream;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import org.joda.time.DateTime;

import xcal.client.Wrapper;
import xcal.client.Status;


import xcal.server.query.*;
import xcal.model.*;

public class ObjectManagers {
	
	
	public static Object manage(Object o){
		
		Wrapper w = (Wrapper) o;
		Object content = w.getContent();
		Status flag = w.getFlag();
	
		
		if(content instanceof Meeting){
			System.out.println("Recieved Meeting");
			
			Meeting m = (Meeting)content;
			switch(flag){
			case CREATE:
				//return (Meeting)AppointmentsQ.createMeeting(m);
			
			case UPDATE:
				//AppointmentsQ.updateMeeting(m);
				break;
				
			case DESTROY:
				AppointmentsQ.deleteEvent(m.getAppId());
				break;
				
			case GET_AVAILABLE_ROOMS:
				Room[] rooms = RoomQ.getAvailableRooms(m.getFromTime(), m.getToTime());
				if(rooms != null)
					return new Wrapper(Status.SUCCESS, rooms);
				else
					return new Wrapper(Status.ERROR, null);
			case SELECT:
				return (Meeting)AppointmentsQ.selectMeeting(m.getAppId());
			}
		}
		else if(content instanceof Appointment){
			System.out.println("Recieved Appointment");
			Appointment a = (Appointment)content;
			switch(flag){
			case CREATE:
				
				Location loc = LocationQ.createLocation(a.getLocationName());
				if(loc != null){
					Appointment app = AppointmentsQ.createAppointment(a, loc);
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
			case UPDATE:
				int app_id = a.getAppId();
				DateTime start = a.getFromTime();
				DateTime end = a.getToTime();
				String descr = a.getDescription();
				String email = a.getLeader().getEmail();
				int place = a.getLocationID();
				break;
				
			case DESTROY:
				AppointmentsQ.deleteEvent(a.getAppId());
				break;
				
			case SELECT:
				return (Appointment)AppointmentsQ.selectAppointment(a.getAppId());
				
			case TD_APP:
				//System.out.println(a);
				//System.out.println("EMAIL : " + a.getLeader().getEmail());

				return new Wrapper(Status.IGNORE, AppointmentsQ.selectAppointmentsForPersonFromDate (a.getFromTime(), a.getToTime(), a.getLeader().getEmail()));
			}
				
		}
		else if(content instanceof Employee){
			System.out.println("Received Employee");
			Employee e = (Employee)content;
			switch(flag){
			case SELECT:
				if(e.getEmpId() != 0){
					return EmployeeQ.selectPerson(e.getEmpId());
				}
				else if(e.getEmail() != null){
					return EmployeeQ.selectPersonWithEmail(e.getEmail());
				}
			case CREATE:
				
					Employee result = EmployeeQ.createPerson(e);
					if(result != null)
						return new Wrapper(Status.SUCCESS, result);
					return new Wrapper(Status.ERROR,null);
				
			case UPDATE:
					EmployeeQ.updatePerson(e);
				break;
			case DESTROY:
				EmployeeQ.deletePerson(e.getEmpId());
				break;
			case GET_ALL:
				return new Wrapper(Status.IGNORE, EmployeeQ.getAllEmployees());
			/*case LOGIN:
				
				return EmployeeQ.checkPassword(e.getEmail());*/
				
			}
		}
		else if(content instanceof Authentication){
			if(flag == Status.LOGIN){
				Authentication auth=(Authentication)content;
				if(ServerLogic.login(auth)){
					//System.out.println("SUCCESS " +EmployeeQ.selectPersonWithEmail(auth.getUser()));
					
					return new Wrapper(Status.SUCCESS, EmployeeQ.selectPersonWithEmail(auth.getUser()));
				}
				else{
					return new Wrapper(Status.ERROR,new Employee());
				}
			}
		}
		else if(content instanceof String){
			System.out.println("Recieved String");
			if(flag == Status.SELECT){
				try {
				//	return (Room[])RoomQ.getAvailableRooms(new Timestamp(0),new Timestamp(0));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return null;
		
	}	
}
