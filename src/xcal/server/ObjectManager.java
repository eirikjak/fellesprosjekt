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
import xcal.server.managers.NotificationManager;
import xcal.server.query.*;
import xcal.model.*;

public class ObjectManager {
	
	
	public static Wrapper manage(Object o){
		
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
			//return InviteManager.handle(invite, flag);
		}
		
		else if(content instanceof Notification)
		{
			Notification notification=(Notification)content;
			return NotificationManager.handle(notification, flag);
		}
		else if(content instanceof Object []){
			Meeting meet = (Meeting)((Object[])content)[0];
			Employee emp = (Employee)((Object[])content)[1];
			int i =0;
			if(flag == Status.INVITE_ACCEPTED){
				i = 1;
			}
			else if(flag == Status.INVITE_DECLINED){
				i = 0;
			}
			else if(flag == Status.INVITE_NOANS){
				i = -1;
			}
			MeetingQ.updateStatus(meet, i, emp);
			return new Wrapper(Status.SUCCESS, null);
		}
		
		return null;
		
	}
		
}
