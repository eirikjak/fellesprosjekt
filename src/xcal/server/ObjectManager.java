package xcal.server;

import java.io.ObjectOutputStream;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import org.joda.time.DateTime;

import xcal.client.Wrapper;
import xcal.client.Status;


import xcal.server.managers.AppointmentManager;
import xcal.server.managers.AuthenticationManager;
import xcal.server.managers.EmployeeManager;
import xcal.server.managers.GroupManager;
import xcal.server.managers.InviteManager;
import xcal.server.managers.MeetingManager;
import xcal.server.query.*;
import xcal.model.*;

public class ObjectManager {
	
	
	public static Object manage(Object o){
		
		if(o == null)
			return new Wrapper(Status.ERROR, null);
		Wrapper w = (Wrapper) o;
		Object content = w.getContent();
		Status flag = w.getFlag();
	
		
		if(content instanceof Meeting){
			Meeting meeting = (Meeting)content;
			return MeetingManager.handle(meeting, flag);
			
		}
		else if(content instanceof Appointment){
			Appointment appointment = (Appointment)content;
			return AppointmentManager.handle(appointment, flag);
				
		}
		else if(content instanceof Employee){
			Employee employee = (Employee)content;
			return EmployeeManager.handle(employee, flag);
			
		}
		else if(content instanceof Authentication){
			Authentication auth=(Authentication)content;
			return AuthenticationManager.handle(auth, flag);
		}
		else if(content instanceof Group){
			Group group = (Group)content;
			return GroupManager.handle(group, flag);
		}
		else if(content instanceof Invite){
			Invite invite =(Invite)content;
			return InviteManager.handle(invite, flag);
		}
		
		return null;
		
	}
		
}
