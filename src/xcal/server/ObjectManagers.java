package xcal.server;

import java.util.ArrayList;

import xcal.client.Wrapper;
import xcal.client.Status;


import xcal.server.query.*;
import xcal.model.*;

public class ObjectManagers {
	
	public Object manage(Object o){
		
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
				return (Appointment)AppointmentsQ.createAppointment(a);
			
			case UPDATE:
				AppointmentsQ.updateAppointment(a);
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
				Authentication auth=(Authentication)o;
				if(ServerLogic.login(auth))
					return new Employee();
			}
		}
		else if(content instanceof String){
			System.out.println("Recieved String");
			if(flag == Status.SELECT){
				return (ArrayList<Room>)RoomQ.findAvailableRooms();
			}
		}
		return null;
		
	}	
}
