package xcal.server.query;
import java.util.ArrayList;

import xcal.model.*;

public class AppointmentsQ {
	
	/*
	 * CREATE / CHANGE / DELETE / UPDATE APPOINTMENTS
	 */
	
	public void createAppointment(int EmployeeId){
	
	}
	
	public Appointment selectAppointment(int AppointmentId){
		return null;
	}
	
	public void updateAppointment(int AppointmentId){
		
	}
	
	public void createMeeting(int EmployeeId){
		
	}
	
	public void selectMeeting(int MeetingId){
		
	}
	
	public void updateMeeting(int MeetingId){
		
	}
	
	public void deleteEvent(int id){
		
	}
	
	public boolean isMeeting(int id){
		return false;
	}
	
	public void removePersonFromMeetign (int app_id, int EmployeeId){
		
	}
	
	private void sendInvites(ArrayList<Person> participants, int app_id){
		
	}
	
	public boolean getAnswer(int app_id, int EmployeeId){
		return false;
	}
	
}
