package xcal.server.query;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import xcal.model.*;

public class AppointmentsQ
{
	private DbConnection connection;
	/*
	 * CREATE / CHANGE / DELETE / UPDATE APPOINTMENTS
	 */
	
	public AppointmentsQ(DbConnection connection) {
		this.connection=connection;
		// TODO Auto-generated constructor stub
	}

	public void createAppointment(int EmployeeId){
	
	}
	
	
	public Appointment selectAppointment(int AppointmentId)
	{
		String query="select * from Appointment where id='"+AppointmentId+"'";
 	   
 	   try 
 	   {
 		   Statement stat = connection.getConnection().createStatement();
 		   ResultSet result=stat.executeQuery(query);
 		   result.next();

 		   
 		   if(result.getString("room").isEmpty())//appointment doesn't contain room
 		   {
 			   Appointment app=new Appointment();
 			  //app.setLocation(result.getString("Location"));
 			   app.setDescription(result.getString("description"));
 			   //app.setName(result.getString("name"));
 			   app.setFromTime(result.getTimestamp("start_date"));
 			   app.setToTime(result.getTimestamp("end_date"));
 			   return app;
 		   }
 		   
 		   Appointment meeting=new Meeting();
 		   meeting.setDescription(result.getString("description"));
 		   meeting.setFromTime(result.getTimestamp("start_date"));
 		   meeting.setToTime(result.getTimestamp("end_date"));
 		   
 		   return meeting;   		   
 		   

 	   } 
 	   catch (SQLException e) 
 	   {
			//couldn't get from db
			e.printStackTrace();
 	   }
 	   
 	   
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
	
	private void sendInvites(ArrayList<Employee> participants, int app_id){
		
	}
	
	public boolean getAnswer(int app_id, int EmployeeId){
		return false;
	}
	
}
