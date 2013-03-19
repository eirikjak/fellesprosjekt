package xcal.server.query;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import xcal.model.Notification;
 
public class NotificationQ
{
	private AppointmentsQ appointments_query;
	private EmployeeQ employee_query;
	private DbConnection connection;
	
	
	
	public NotificationQ(DbConnection connection) 
	{
		this.connection=connection;
		appointments_query=new AppointmentsQ(this.connection);
		employee_query=new EmployeeQ(this.connection);

	}

	
	
	/**
	 * check if any notifications need to be triggered
	 * 
	 * @return - true if found notifications - false otherwise
	 */
	public boolean notificationReady()
	{
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
	
	
	/**
	 * get notifications that need to be triggered
	 * 
	 * @return notifications that need to be sent
	 */
	public Notification[] getNotifications()
    {

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
 	   
 	   return null;
    }

}
