package xcal.server.query;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import xcal.model.Appointment;
import xcal.model.Employee;
import xcal.model.Meeting;

public class InviteQ 
{
	private static DbConnection connection;
	
	public InviteQ(DbConnection connection) 
	{
		this.connection=connection;
	}
	
	
	
	/**
	 * create invite. don't set answer, since null (not answered) is default
	 * 
	 * @param meeting - meeting sent invite from
	 * @param employee - employee invite sent too
	 */
	public static void createInvite(Appointment meeting,Employee employee)
	{
		synchronized (connection) 
		{
			String query = "insert into Invites (app_id,person) values('" + meeting.getAppId() + "'," + "'" + employee.getEmail()+ "')";
			
			
			try 
			{
				Statement stat = connection.getConnection().createStatement();
				stat.executeQuery(query);
				
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		
		}
	}
	
	
	/**
	 * create invite. don't set answer, since null (not answered) is default
	 * use int&string instead of objects
	 * 
	 * @param meeting - meeting sent invite from
	 * @param employee - employee invite sent too
	 */
	public static void createInvite(int app_id,String emp_email)
	{
		synchronized (connection) 
		{
			String query = "insert into Invites (app_id,person) values('" + app_id + "'," + "'" + emp_email+ "')";
			
			
			try 
			{
				Statement stat = connection.getConnection().createStatement();
				stat.executeQuery(query);
				
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		
		}
	}
	
	
	
	
	/**
	 * delete invite because employee answered, or don't need status anymore
	 * 
	 * @param meeting - meeting sent invite from
	 * @param employee - employee sent invite oto
	 */
	public static void delInvite(Appointment meeting,Employee employee)
	{
		synchronized (connection) 
		{
			String query = "delete from Invites where app_id='" + meeting.getAppId() + "' and person='" + employee.getEmail()+ "'";
			
			
			try 
			{
				Statement stat = connection.getConnection().createStatement();
				stat.executeQuery(query);
				
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		
		}
	}
	
	/**
	 * delete invite because employee answered, or don't need status anymore
	 * use int&string instead of objects
	 * 
	 * @param meeting - meeting sent invite from
	 * @param employee - employee sent invite oto
	 */
	public static void delInvite(int app_id,String emp_email)
	{
		synchronized (connection) 
		{
			String query = "delete from Invites where app_id='" + app_id + "' and person='" + emp_email+ "'";
			
			
			try 
			{
				Statement stat = connection.getConnection().createStatement();
				stat.executeQuery(query);
				
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		
		}
	}
	
	
	/**
	 * update invite, if employee answered invite
	 * 
	 * @param meeting - meeting sent invite from
	 * @param employee - employee sent invite oto
	 */
	public static void updateInvite(Appointment meeting,Employee employee,int answer)
	{
		synchronized (connection) 
		{
			String query = "update Invites set ans='"+answer+"' where app_id='"+meeting.getAppId()+"' and person='"+employee.getEmail()+"'";
			
			
			try 
			{
				Statement stat = connection.getConnection().createStatement();
				stat.executeQuery(query);
				
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		
		}
		
		
	}
	
	/**
	 * update invite, if employee answered invite
	 * use int&string instead of objects
	 * 
	 * @param meeting - meeting sent invite from
	 * @param employee - employee sent invite oto
	 */
	public static void updateInvite(int app_id,String emp_email,int answer)
	{
		synchronized (connection) 
		{
			String query = "update Invites set ans='"+answer+"' where app_id='"+app_id+"' and person='"+emp_email+"'";
			
			
			try 
			{
				Statement stat = connection.getConnection().createStatement();
				stat.executeQuery(query);
				
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		
		}
		
		
	}
	
	
	
}
