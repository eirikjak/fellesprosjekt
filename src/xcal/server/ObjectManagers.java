package xcal.server;

import java.io.ObjectOutputStream;
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
				return (Meeting)AppointmentsQ.createMeeting(m);
			
			case UPDATE:
				AppointmentsQ.updateMeeting(m);
				break;
				
			case DESTROY:
				AppointmentsQ.deleteEvent(m.getAppId());
				break;
				
			case SELECT:
				return (Meeting)AppointmentsQ.selectMeeting(m.getAppId());
			}
		}
		else if(content instanceof Appointment){
			System.out.println("Recieved Appointment");
			Appointment a = (Appointment)content;
			switch(flag){
			case CREATE:
				Appointment result = (Appointment)AppointmentsQ.createAppointment(a);
				if(result != null)
					return new Wrapper(Status.SUCCESS,null);
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
				return EmployeeQ.createPerson(e);
			case UPDATE:
				EmployeeQ.updatePerson(e);
				break;
			case DESTROY:
				EmployeeQ.deletePerson(e.getEmpId());
				break;
			case LOGIN:
				return EmployeeQ.checkPassword(e.getEmail());
				
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
					return (Room[])RoomQ.getAvailableRooms(new Timestamp(0),new Timestamp(0));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return null;
		
	}	
}
