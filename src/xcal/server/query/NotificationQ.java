package xcal.server.query;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import xcal.model.Notification;
 
public class NotificationQ extends DbConnection
{
	private AppointmentsQ appointments_query;
	private EmployeeQ employee_query;
	
	
	
	public NotificationQ(String url, String user, String password) 
	{
		super(url, user, password);
		appointments_query=new AppointmentsQ(url, user, password);
		employee_query=new EmployeeQ(url, user, password);

	}

	
	
	/**
	 * Notification[] not=dc.checkNotification();
	        	
	        	for(int i=0;i<not.length;++i)
	        	{
	        		System.out.println(not[i].getEmployee().getName());
	        	}
	 * @return
	 */
	
	public Notification[] checkNotification()
    {

 	   String query="select * from Notification where NOW() >=  notifiedAt";
 	   
 	   try 
 	   {
 		   
 		   Statement stat=connection.createStatement();
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
