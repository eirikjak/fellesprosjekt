package xcal.server.query;

import java.net.ConnectException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import xcal.model.Appointment;
import xcal.model.Employee;
import xcal.model.Invite;
import xcal.model.Meeting;
import xcal.model.Notification;
 
public class NotificationQ
{
	private AppointmentsQ appointments_query;
	private EmployeeQ employee_query;
	private static DbConnection connection;
	
	
	
	public NotificationQ(DbConnection connection) 
	{
		this.connection=connection;
	}

	
	public static Notification createNotification(Appointment app){
		
		synchronized (connection) {
			try {
			DateTimeFormatter format = DateTimeFormat.forPattern("Y-M-d H:m:s");
			String query = "INSERT INTO Notification (app_id,person,notifiedAt) VALUES('" + app.getAppId() + "'," + "'" + app.getLeader().getEmail()+  "'," + "'" + format.print(app.getNotification().getNotificationTime()) + "')";
			Statement stat = connection.getConnection().createStatement();
			stat.execute(query);
			
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
				
			}
			
		}
		return new Notification(app, app.getLeader());
		
	}
	public static Invite deleteInvite (Meeting meeting, Employee employee){
		synchronized (connection) {
			String query = "DELETE FROM Invites WHERE person ='" +employee.getEmail() +"' + AND app_id = '" +meeting.getAppId() + "'";
			Statement stat = null;
			try {
				stat = connection.getConnection().createStatement();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				stat.execute(query);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return null;
			
		}
	}
	public static Invite createInvite (Meeting meeting, Employee employee){
		synchronized (connection) {
			String query = "INSERT INTO Invites (app_id,person) VALUES('" + meeting.getAppId() + "'," + "'" + employee.getEmail()+ "')";
			Statement stat = null;
			try {
				stat = connection.getConnection().createStatement();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				stat.execute(query);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return null;
			
		}
	}
	
	public static Notification createNotification(Meeting meeting, Employee employee){
		synchronized (connection) {
			try {
			DateTimeFormatter format = DateTimeFormat.forPattern("Y-M-d H:m:s");
			String query = "INSERT INTO Notification (app_id,person,notifiedAt) VALUES('" + meeting.getAppId() + "'," + "'" + employee.getEmail()+  "'," + "'" + format.print(meeting.getNotification().getNotificationTime()) + "')";
			Statement stat = connection.getConnection().createStatement();
			stat.execute(query);
			
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
				
			}
			
		}
		return new Notification(meeting, employee);
	}
	
	public static void deleteNotification(String email, int appId){
		synchronized (connection) {
			try {
				String query = "DELETE FROM Notification WHERE person ='" + email +"' + AND app_id = '" +appId + "'";
				Statement stat = connection.getConnection().createStatement();
				stat.execute(query);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	public static void deleteNotification(Employee employee, Appointment appointment)
	{
		synchronized (connection) 
		{
			try 
			{
				String query = "DELETE FROM Notification WHERE person ='" + employee.getEmail() +"' AND app_id = '" +appointment.getAppId() + "'";
				Statement stat = connection.getConnection().createStatement();
				stat.execute(query);
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
			
		}
	}
	
	
	
	/**
	 * updates notification time for given notification
	 * 
	 * @param notification - notification to change notificationtime for
	 */
	public static void updateNotification(Notification notification)
	{
		synchronized(connection)
		{
			try
			{
				String query="update Notification set notifiedAt='" +notification.getNotificationTime()+
							"' where app_id='"+notification.getAppointment().getAppId()+"' and person='"+notification.getEmployee().getEmail()+"' ";
				Statement stat = connection.getConnection().createStatement();
				stat.execute(query);
			}
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
	}
	
	
	
	
	
	
	/**
	 * check if any notifications need to be triggered
	 * 
	 * @return - true if found notifications - false otherwise
	 */
	public boolean notificationReady()
	{
		synchronized (connection) {
		
		String query="select * from Notification where NOW() >=  notifiedAt";
		
		 
		try 
		{
			Statement stat = connection.getConnection().createStatement();
			ResultSet result=stat.executeQuery(query);
			
			result.last();
			if(result.getRow()>0)
				return true;
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
		}
		 
	}
	
	
	/**
	 * get notifications that need to be triggered
	 * 
	 * @return notifications that need to be sent
	 */
	public Notification[] getNotifications()
    {
		synchronized (connection) {
 	   String query="select * from Notification where NOW() >=  notifiedAt";
 	   
 	   try 
 	   {
 		   
 		   Statement stat=connection.getConnection().createStatement();
 		   ResultSet result=stat.executeQuery(query);
 		   
 		   result.last();
 		   Notification[] notification=new Notification[result.getRow()];
 		   result.beforeFirst();
 		   
 		   int size=0;
 		   
 		   while(result.next())
 		   {


 			   notification[size]=new Notification
 			   			(appointments_query.selectAppointment(result.getInt("app_id")),
 			   				employee_query.selectPersonWithEmail(result.getString(("person"))));
 			 
 			 ++size;
 		   }
 			   
 	   
 		   return notification;
			
 	   } 
 	   catch (SQLException e) 
 	   {
			//couldn't get from db
			e.printStackTrace();
 	   }
		}
 	   return null;
    }
	
	
	/**
	 * Get notification for specified employee
	 * 
	 * selects notifications that should be triggered for
	 * specified person. 
	 * 
	 * @param e - employee object to get notifications for
	 * @return - notification array for specified employee e
	 */
	public static Notification[] getNotificationPerson(Employee emp)
	{
		synchronized (connection)
		{
			String query="select * from Notification where NOW() >=  notifiedAt and person='"+emp.getEmail()+"'";
			
			try
			{
				Statement stat=connection.getConnection().createStatement();
				ResultSet result=stat.executeQuery(query);
				
				result.last();
				Notification[] notification=new Notification[result.getRow()];
				result.beforeFirst();
				
				int size=0;
				
				while(result.next())
				{
					notification[size]=new Notification(AppointmentsQ.selectAppointment(result.getInt("app_id")),emp);
					System.out.println("Title:" + notification[size].getAppointment().getTitle());
					++size;
					
				}
				
				return notification;
			}
			catch (SQLException e) 
		 	{
				//couldn't get from db
				e.printStackTrace();
		 	}
		}
		
		return null;
	}

}
