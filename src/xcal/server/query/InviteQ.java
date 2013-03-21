package xcal.server.query;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.joda.time.DateTime;

import xcal.model.Appointment;
import xcal.model.Employee;
import xcal.model.Invite;
import xcal.model.Meeting;
import xcal.model.Room;

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
			System.out.println(query);
			
			try 
			{
				Statement stat = connection.getConnection().createStatement();
				stat.executeUpdate(query);
				
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		
		}
		
		
	}
	/**
	 * get answer for specified user for specified appointment
	 * must check if invite exist before using this function
	 * 
	 * @param app_id - appointment id
	 * @param emp_email - email for user
	 * @return - 1:accepted 0:rejected -1:haven't answered
	 */
	public static int checkAnswer(int app_id,String emp_email)
	{
		synchronized (connection) 
		{
			String query="select * from Invites where app_id='"+app_id+"' and person='"+emp_email+"'";
			
			try 
			{
				Statement stat = connection.getConnection().createStatement();
				ResultSet result=stat.executeQuery(query);
				
				result.next();
				
				return result.getInt("ans");
			
				
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
			
			return -1;
		}
	}
	
	
	public static ArrayList<Invite> getInvitesForPerson(Employee emp){
		
		synchronized (connection) 
		{
			String query =    
					   "select  A.title, A.start_date as start, A.end_date as end,  A.description  , P.name, P.email, R.name as loc, R.id as r_id, R.capacity as r_capacity, A.id " + 
					  " from  Appointment A, Room R, Person P " +        
					    "where P.email = A.leader AND  R.id = A.room AND A.id in( " +
					   "select I.app_id as id from Invites I, Person P where   "+      
					   "	I.person = '" + emp.getEmail() + "' AND I.ans = -1      "+
					    ")";
			
			try 
			{
				Statement stat = connection.getConnection().createStatement();
				ResultSet result = stat.executeQuery(query);
				ArrayList<Invite> invites = new ArrayList<Invite>();
				while(result.next()){
					String title = result.getString("title");
					DateTime start = new DateTime(result.getTimestamp("start"));
					DateTime end = new DateTime(result.getTimestamp("end"));
					String description = result.getString("description");
					String leader = result.getString("email");
					String leaderName = result.getString("name");
					String roomName = result.getString("loc");
					int id = result.getInt("id");
					int r_id = result.getInt("r_id");
					int cap = result.getInt("r_capacity");
					Meeting meeting = new Meeting(start, end, title, description, new Employee(leaderName, leader, ""), new Room(r_id, roomName, cap));
					meeting.setAppId(id);
					Invite invite = new Invite(meeting, meeting.getLeader());
					invites.add(invite);
					System.out.println(invite);
				}
				return invites;
				
				
			}catch(SQLException e){
				e.printStackTrace();
				return null;
			}
			
		}
	


	}
	
	
}
