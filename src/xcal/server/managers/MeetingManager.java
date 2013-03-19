package xcal.server.managers;

import xcal.client.Status;
import xcal.client.Wrapper;
import xcal.model.Meeting;
import xcal.model.Room;
import xcal.server.query.AppointmentsQ;
import xcal.server.query.RoomQ;

public class MeetingManager {

	public static Wrapper handle(Meeting meeting, Status flag){
		switch(flag){
		case CREATE:
			return create(meeting);
		case UPDATE:
			return update(meeting);
		case DESTROY:
			destroy(meeting);
		case GET_AVAILABLE_ROOMS:
			return getAvailableRooms(meeting);
		case SELECT:
			return select(meeting);
		}
		
		return null;
	}
	
	private static Wrapper create(Meeting meeting){
		return new Wrapper(Status.ERROR, null);
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
	
	private static Wrapper select(Meeting meeting){
		
		return new Wrapper (Status.ERROR, null);
	}
	
}
