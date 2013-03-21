package xcal.server.managers;

import java.sql.SQLException;
import java.util.ArrayList;

import xcal.client.Status;
import xcal.client.Wrapper;
import xcal.model.Appointment;
import xcal.model.Employee;
import xcal.model.Invite;
import xcal.model.Location;
import xcal.model.Meeting;
import xcal.model.Notification;
import xcal.model.Room;
import xcal.server.query.AppointmentsQ;
import xcal.server.query.EmployeeQ;
import xcal.server.query.LocationQ;
import xcal.server.query.MeetingQ;
import xcal.server.query.NotificationQ;
import xcal.server.query.RoomQ;

public class MeetingManager {

	public static Wrapper handle(Meeting meeting, Status flag){
		switch(flag){
		case CREATE:
			try {
				return create(meeting);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		case UPDATE:
			return update(meeting);
		case DESTROY:
			destroy(meeting);
		case GET_AVAILABLE_ROOMS:
			return getAvailableRooms(meeting);
		case SELECT:
			return select(meeting);
		
		case GET_PARTICIPANTS:
			return getParticipants(meeting);

			
		}
			
		
		return null;
	}
	
/*
	private static Wrapper create(Meeting meeting){
		
		Location loc = LocationQ.createLocation(meeting.getLocationName());
		if(loc != null){
			Meeting m = (Meeting) AppointmentsQ.createAppointment(meeting, loc);
			if(m != null){
				Notification not = NotificationQ.createNotification(m);
				if(not != null){
					return new Wrapper(Status.SUCCESS, null);
				}else{
					AppointmentsQ.deleteAppointment(m);
				}
			}else{
				LocationQ.deleteLocation(loc);
			}
		}
		return new Wrapper(Status.ERROR,null);
		*/
	private static Wrapper create(Meeting meeting) throws SQLException{

		System.out.println("creating room");
		Room room = RoomQ.selectRoom(meeting.getRoom().getID());
		if(room == null){
			return new Wrapper(Status.ERROR, null);
		}
		System.out.println(meeting);
		
		boolean error = false;
		Meeting met  =  MeetingQ.createMeeting(meeting, room);
		ArrayList<String> notificationOwners = new ArrayList<String>();
		if(met != null){
			
			for(Employee emp: meeting.getParticipants()){
				if(!emp.getEmail().equals(meeting.getLeader().getEmail())){
					Employee dbEmp = EmployeeQ.selectPersonWithEmail(emp.getEmail());
					if(dbEmp == null){
						error = true;
						break;
					}
					Invite invited = NotificationQ.createInvite(met, dbEmp);
					if(invited == null){
						error = true;
						break;
					}
					Notification notEmp = NotificationQ.createNotification(met, dbEmp);
					if(notEmp == null){
						error = true;
						break;
					}
					notificationOwners.add(dbEmp.getEmail());
				}else{
					System.out.println("found leader!");
				}
			}
			System.out.println(met);
			Notification leaderNot = NotificationQ.createNotification(met);
			
			notificationOwners.add(met.getLeader().getEmail());
			
			if(error){
				for(String email :notificationOwners){
					NotificationQ.deleteNotification(email,met.getAppId());
				}
				return new Wrapper(Status.SUCCESS, null);
			}
			
				return new Wrapper(Status.SUCCESS, null);
			}
			
		return new Wrapper(Status.ERROR,null);
	
	}
	
	private static Wrapper update(Meeting meeting){
		return new Wrapper(Status.ERROR, null);
	}
	
	private static Wrapper destroy(Meeting meeting){
		
		return new Wrapper(Status.ERROR, null);
	}
	private static Wrapper getAvailableRooms(Meeting meeting){
		Room[] rooms = RoomQ.getAvailableRooms(meeting.getFromTime(), meeting.getToTime());
		if(rooms != null)
			return new Wrapper(Status.SUCCESS, rooms);
		else
			return new Wrapper(Status.ERROR, null);
	}
	
	private static Wrapper getParticipants(Meeting meeting){
		ArrayList[] partList = MeetingQ.getParticipants(meeting);
		if(partList != null){
			return new Wrapper(Status.SUCCESS, partList);
		}
		else{
			return new Wrapper(Status.SUCCESS, partList);
		}
	}
	
	private static Wrapper select(Meeting meeting){
		
		return new Wrapper (Status.ERROR, null);
	}

	
}
