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
	 * Notification[] not=dc.checkNotification();
	        	
	        	for(int i=0;i<not.length;++i)
	        	{
	        		System.out.println(not[i].getEmployee().getName());
	        	}
	 * @return
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

}
